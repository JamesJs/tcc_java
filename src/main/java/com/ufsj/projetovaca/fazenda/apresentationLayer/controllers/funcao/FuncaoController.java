package com.ufsj.projetovaca.fazenda.apresentationLayer.controllers.funcao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufsj.projetovaca.fazenda.applicationLayer.applicationService.CadastroFuncao;
import com.ufsj.projetovaca.fazenda.apresentationLayer.DTO.FuncaoInput;
import com.ufsj.projetovaca.fazenda.apresentationLayer.DTO.FuncaoOutput;
import com.ufsj.projetovaca.fazenda.apresentationLayer.controllers.interfaces.ICrudController;
import com.ufsj.projetovaca.fazenda.apresentationLayer.utils.AssemblerAdapter;
import com.ufsj.projetovaca.fazenda.apresentationLayer.utils.Conversores;
import com.ufsj.projetovaca.fazenda.domainLayer.models.Funcao;
import com.ufsj.projetovaca.fazenda.domainLayer.repositories.FuncaoRepository;

@RestController
@RequestMapping("/funcao")
public class FuncaoController extends ICrudController<FuncaoInput,FuncaoOutput,Funcao> {
	
	
	
	
	public FuncaoController() {
		super();
		super.criarConversores(FuncaoOutput.class);
	}

	@Autowired
	private FuncaoRepository funcaoRepository;
	
	@Autowired
	private CadastroFuncao cadastroFuncao;
	
	
	
	Conversores<FuncaoInput, FuncaoOutput, Funcao> conversores = 
			new Conversores<FuncaoInput, FuncaoOutput, Funcao>();
	
	AssemblerAdapter<FuncaoOutput, Funcao> conversorOutput = 
			conversores.criarConversorOutput(FuncaoOutput.class);
	
	AssemblerAdapter<Funcao, FuncaoInput> conversorEntidade = conversores.criarConversorEntidade(Funcao.class);
	
	@GetMapping
	public ResponseEntity<List<FuncaoOutput>> listar(){
		try {
			
			List<Funcao> funcoes = funcaoRepository.findAll();
					
			List<FuncaoOutput> funcoesOutput = conversorOutput.converterColecao(funcoes);
			
			
			return ResponseEntity.status(HttpStatus.OK).body(funcoesOutput);
		
		}catch(Exception e){
			System.out.println(e);
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}


	@Override
	@PostMapping
	public ResponseEntity<FuncaoOutput> criar(@RequestBody FuncaoInput funcaoInput ) {
		try {
			
			FuncaoOutput funcaoOutput = cadastroFuncao.salvar(funcaoInput);
			
			return ResponseEntity.status(HttpStatus.OK).body(funcaoOutput);
			
		}catch (Exception e){
			
			System.out.println(e);
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<FuncaoOutput> deletar(@PathVariable Long id) {
		try {
			
			FuncaoOutput funcaoOutput = cadastroFuncao.deletar(id);
			
			return ResponseEntity.status(HttpStatus.OK).body(funcaoOutput);
		}catch(Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}

	
	@Override
	@PutMapping("/{id}")
	public ResponseEntity<FuncaoOutput> atualizar(@RequestBody FuncaoInput funcaoInput,@PathVariable Long id) {
		try {
			
			FuncaoOutput funcaoOutput = cadastroFuncao.atualizar(id, funcaoInput);
			
			return ResponseEntity.status(HttpStatus.OK).body(funcaoOutput);
		
		}catch(Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}  
}
