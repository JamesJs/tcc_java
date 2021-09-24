package com.ufsj.projetovaca.comercial.apresentationLayer.controllers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ufsj.projetovaca.comercial.infraLayer.assembler.AssemblerAdapter;
import com.ufsj.projetovaca.comercial.infraLayer.assembler.Conversores;

public abstract class ACrudController<Input,Output,Entidade> {
	
	
	Conversores<Input,Output,Entidade> conversores = new Conversores<Input,Output,Entidade>();
	
	Class<Output> Output;
    	
	public AssemblerAdapter<Output, Entidade> conversorOutput;
	
	public void criarConversores(Class<Output> Output) {
		this.Output = Output;
		this.conversorOutput = conversores.criarConversorOutput(Output);
		
	}
	
	public ResponseEntity<?> listarTodas(JpaRepository<Entidade, Long> repository){
		
		List<Entidade> todas = repository.findAll();
	
		
		List<Output> output =  conversorOutput.converterColecao(todas);
		
		
		
		return respostaStatus(HttpStatus.OK, output);
	
	}
	
	
	public ResponseEntity<?> atualizar(JpaRepository<Entidade, Long> repository,Long id, Input input,String campoId) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
		Optional<Entidade> opEntidade = repository.findById(id);
		
		if(opEntidade.isEmpty()) {
			
			return erroUsuarioNaoEncontrado();
		
		}
		
		Entidade entidade = opEntidade.get();
		
		copiarAtributos(entidade, input, campoId);
		
		Method m;
		try {
			m = entidade.getClass().getMethod("setId",Long.class);
			m.invoke(entidade, id);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			return erroServidor(e);
			
		}
		
		Entidade novaEntidade = repository.save(entidade);
		
		Output output = conversorOutput.converterUnitario(novaEntidade);
		
		return respostaStatus(HttpStatus.OK, output);
	}
	
	
	public ResponseEntity<?> erroUsuarioNaoEncontrado(){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
	}
	
	
	public ResponseEntity<?> erroServidor(Exception  e) {
		
		System.err.println(e);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
	}
	
	public <G,K> void copiarAtributos(G destino,K origem,String id) {
		BeanUtils.copyProperties(origem, destino, id);
	}
	
	public <G> ResponseEntity<G> respostaStatus(HttpStatus ok, G funcionarioOutput) {
		return ResponseEntity.status(HttpStatus.OK).body(funcionarioOutput);
	}

	public abstract ResponseEntity<?> listar();
	public abstract ResponseEntity<?> criar(Input Input);
	public abstract ResponseEntity<?> deletar(Long id);
	public abstract ResponseEntity<?> atualizar(Input Input,Long id);
	
}

