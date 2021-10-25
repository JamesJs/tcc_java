package com.ufsj.projetovaca.fazenda.apresentationLayer.controllers.fazenda.cocho;


import java.util.HashMap;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@RestController()
@Api(tags = {"Cocho"},description = "Endpoints relacionados aos cochos")
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

	
	@GetMapping(path ="fazenda/{idFazenda}/cocho",produces = "application/json")
	@ApiOperation(value = "Lista todos os cochos de cada fazenda.")
	@ApiResponses(value = {
	        @ApiResponse(code=200, message = "Retorna os cochos cadastrados relacionados à uma fazenda. É um array.", response = CochoOutput.class),
	        @ApiResponse(code=500, message = "Retorna uma mensagem de erro do servidor"),
	        @ApiResponse(code=404, message = "Retorna uma mensagem de não encontrado")
	        
	 })
	public ResponseEntity<?> listarPorFazenda(@PathVariable long idFazenda) {
		try {
			
			List<CochoOutput> cochoOutput = cadastroCocho.listarCochosPorFazenda(idFazenda);
			
			return RespostaStatus(HttpStatus.OK, cochoOutput);
		
		}catch(NotFoundWithId e){
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, Object>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				put("err",e.getMessage());
			}});
		}
		
		catch(Exception e) {
			
			return erroServidor(e);
		
		}
	}

	@ApiOperation(value = "Cadastra um novo cocho no sistema.")
	@ApiResponses(value = {
	        @ApiResponse(code=201, message = "Retorna o cocho criado", response = CochoOutput.class),
	        @ApiResponse(code=500, message = "Retorna uma mensagem de erro do servidor"),
	        @ApiResponse(code=404, message = "Retorna uma mensagem de não encontrado")
	        
	 })
	@PostMapping(path = "fazenda/{idFazenda}/cocho",produces = "application/json")
	public ResponseEntity<?> criar(@RequestBody CochoInput cochoInput,@PathVariable long idFazenda) {
		
		try {			
			
			CochoOutput cochoOutput = cadastroCocho.salvar(idFazenda, cochoInput);
			
			return RespostaStatus(HttpStatus.CREATED, cochoOutput);
			
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
	
	
	@DeleteMapping(path = "fazenda/{idFazenda}/cocho/{idCocho}",produces = "application/json")
	@ApiResponses(value = {
	        @ApiResponse(code=200, message = "Retorna o cocho deletado", response = CochoOutput.class),
	        @ApiResponse(code=404, message = "Retorna uma mensagem de não encontrado"),
	        @ApiResponse(code=500, message = "Retorna uma mensagem de erro do servidor")
	        
	 })
	@ApiOperation(value = "Deleta animal do sistema.")
	public ResponseEntity<?> removerCochoDeUmaFazenda(@PathVariable Long idFazenda, @PathVariable long idCocho){
		try {
			CochoOutput cochoOutput = cadastroCocho.removerCocho(idFazenda, idCocho);
			return RespostaStatus(HttpStatus.OK, cochoOutput);
			
		}catch(NotFoundWithId e) {
			System.out.println(e);
			return erroUsuarioNaoEncontrado();
		}
		
	}
	
	@PutMapping(path = "fazenda/{idFazenda}/cocho/{idCocho}",produces = "application/json")
	@ApiResponses(value = {
	        @ApiResponse(code=200, message = "Retorna o cocho atualizado.", response = CochoOutput.class),
	        @ApiResponse(code=404, message = "Retorna uma mensagem de não encontrado"),
	        @ApiResponse(code=500, message = "Retorna uma mensagem de erro do servidor")
	        
	 })
	@ApiOperation(value = "Realiza atualização a todos os parâmetros de um cocho.")
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
