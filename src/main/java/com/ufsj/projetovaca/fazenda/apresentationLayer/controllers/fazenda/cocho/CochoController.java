package com.ufsj.projetovaca.fazenda.apresentationLayer.controllers.fazenda.cocho;


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
import org.springframework.web.bind.annotation.RestController;

import com.ufsj.projetovaca.fazenda.applicationLayer.applicationService.CadastroCocho;
import com.ufsj.projetovaca.fazenda.applicationLayer.applicationService.CochosDeUmaFazenda;
import com.ufsj.projetovaca.fazenda.applicationLayer.exceptions.CochoNaMesmaLocalizacao;
import com.ufsj.projetovaca.fazenda.applicationLayer.exceptions.NotFoundCochoType;
import com.ufsj.projetovaca.fazenda.applicationLayer.exceptions.NotFoundWithId;
import com.ufsj.projetovaca.fazenda.apresentationLayer.DTO.CochoInput;
import com.ufsj.projetovaca.fazenda.apresentationLayer.DTO.CochoOutput;
import com.ufsj.projetovaca.fazenda.apresentationLayer.controllers.interfaces.ICrudController;
import com.ufsj.projetovaca.fazenda.domainLayer.models.Cocho;
import com.ufsj.projetovaca.fazenda.domainLayer.repositories.FazendaRepository;
@RestController

public class CochoController extends ICrudController<CochoInput, CochoOutput, Cocho> {

	
	Class<CochoOutput> Output = CochoOutput.class;
	
	
	
	public CochoController() {
		super();
		criarConversores(Output);;
	}
	
	
	
	@Autowired
	FazendaRepository fazendaRepository;
	
	@Autowired
	CochosDeUmaFazenda cochosDeUmaFazenda;
	
	@Autowired
	CadastroCocho cadastroCocho;

	
	@GetMapping("fazenda/{idFazenda}/cocho")
	public ResponseEntity<?> listarPorFazenda(@PathVariable long idFazenda) {
		try {
			
			List<CochoOutput> cochoOutput = cadastroCocho.listarCochosPorFazenda(idFazenda);
			
			return RespostaStatus(HttpStatus.OK, cochoOutput);
		
		}catch(Exception e) {
			
			return erroServidor(e);
		
		}
	}

	
	@PostMapping("fazenda/{idFazenda}/cocho")
	public ResponseEntity<?> criar(@RequestBody CochoInput cochoInput,@PathVariable long idFazenda) {
		
		try {			
			
			CochoOutput cochoOutput = cadastroCocho.salvar(idFazenda, cochoInput);
			
			return RespostaStatus(HttpStatus.OK, cochoOutput);
			
		}catch(NotFoundWithId e) {
			
			return erroUsuarioNaoEncontrado();
			
		}catch(CochoNaMesmaLocalizacao e) {
			
			//Criar um erro específico
			return erroUsuarioNaoEncontrado();
			
		}catch(NotFoundCochoType e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			
		}catch(Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			
		}
	}
	
	
	@DeleteMapping("fazenda/{idFazenda}/cocho/{idCocho}")
	public ResponseEntity<?> removerCochoDeUmaFazenda(@PathVariable Long idFazenda, @PathVariable long idCocho){
		try {
			CochoOutput cochoOutput = cadastroCocho.removerCocho(idFazenda, idCocho);
			return RespostaStatus(HttpStatus.OK, cochoOutput);
			
		}catch(NotFoundWithId e) {
			System.out.println(e);
			return erroUsuarioNaoEncontrado();
		}
		
	}
	
	@PutMapping("fazenda/{idFazenda}/cocho/{idCocho}")
	public ResponseEntity<?> atualizarCochoDeUmaFazenda(@PathVariable long idFazenda,@PathVariable long idCocho ,@RequestBody Cocho Cocho){
		try {
			CochoOutput cochoOutput = cadastroCocho.atualizarCocho(idFazenda,idCocho ,Cocho);
			return RespostaStatus(HttpStatus.OK, cochoOutput);
			
		}catch(NotFoundWithId e) {
			System.out.println(e);
			return erroUsuarioNaoEncontrado();
		}
	}
	@Override
	public ResponseEntity<?> deletar(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> atualizar(CochoInput t, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> listar() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ResponseEntity<?> criar(CochoInput t) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
