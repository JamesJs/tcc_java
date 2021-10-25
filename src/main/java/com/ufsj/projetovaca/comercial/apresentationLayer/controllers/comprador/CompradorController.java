package com.ufsj.projetovaca.comercial.apresentationLayer.controllers.comprador;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufsj.projetovaca.animal.applicationLayer.exceptions.CompradorHasCompras;
import com.ufsj.projetovaca.comercial.applicationLayer.applicationService.CadastroComprador;
import com.ufsj.projetovaca.comercial.applicationLayer.exceptions.InvalidTipoDeComprador;
import com.ufsj.projetovaca.comercial.apresentationLayer.DTO.CompradorInput;
import com.ufsj.projetovaca.comercial.apresentationLayer.DTO.CompradorOutput;
import com.ufsj.projetovaca.comercial.apresentationLayer.controllers.ACrudController;
import com.ufsj.projetovaca.comercial.domainLayer.models.Comprador;
import com.ufsj.projetovaca.comercial.applicationLayer.exceptions.NotFoundWithId;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@Api(tags = {"Comprador"},description =  "Endpoints relacionados à compradores")
@RequestMapping(path ="/comprador",produces = "application/json")
@RestController
public class CompradorController extends ACrudController<CompradorInput, CompradorOutput, Comprador> {
	
	@Autowired
	CadastroComprador cadastroComprador;
	
	
	@GetMapping
	@Override
	@ApiOperation(value = "Lista todos os compradores cadastrados no sistema.")
	@ApiResponses(value = {
	        @ApiResponse(code=200, message = "Retorna os animais cadastrados. É um array.", response = CompradorOutput.class),
	        @ApiResponse(code=500, message = "Retorna uma mensagem de erro do servidor")
	        
	 })
	public ResponseEntity<?> listar() {	
		
		try {
			
			List<CompradorOutput> compradoresOutput = cadastroComprador.listar();
			
			return respostaStatus(HttpStatus.OK, compradoresOutput);
			
		}catch(Exception e) {
			
			return erroServidor(e); 
			
		}
	}

	@PostMapping
	@Override
	@ApiOperation(value = "Cadastra um novo comprador no sistema.")
	@ApiResponses(value = {
	        @ApiResponse(code=200, message = "Retorna o comprador criado", response = CompradorOutput.class),
	        @ApiResponse(code=500, message = "Retorna uma mensagem de erro do servidor")
	        
	 })
	public ResponseEntity<?> criar(@RequestBody CompradorInput compradorInput) {
		
		try {
			
			CompradorOutput compradorOutput = cadastroComprador.criar(compradorInput);
			
			return respostaStatus(HttpStatus.OK, compradorOutput);
			
		}catch(Exception e) {
			
			return erroServidor(e);
			
		}
		
	}

	@PatchMapping("/{id}/ativado")
	@ApiResponses(value = {
	        @ApiResponse(code=200, message = "Retorna o comprador desativado", response = CompradorOutput.class),
	        @ApiResponse(code=404, message = "Retorna uma mensagem de não encontrado"),
	        @ApiResponse(code=500, message = "Retorna uma mensagem de erro do servidor")
	        
	 })
	@ApiOperation(value = "Desativa um comprador do sistema.")
	public ResponseEntity<?> desativar(@PathVariable Long id) {
		
		try {
			
			CompradorOutput compradorOutput = cadastroComprador.desativar(id);
			
			return respostaStatus(HttpStatus.OK, compradorOutput);		
			
		}catch(NotFoundWithId e){
			
			return erroUsuarioNaoEncontrado(e.getMessage());
			
		}catch(Exception e) {
			
			return erroServidor(e);
			
		}
		
	}

	@PutMapping("/{id}")
	@Override
	@ApiResponses(value = {
	        @ApiResponse(code=200, message = "Retorna o comprador atualizado.", response = CompradorOutput.class),
	        @ApiResponse(code=404, message = "Retorna uma mensagem de não encontrado"),
	        @ApiResponse(code=500, message = "Retorna uma mensagem de erro do servidor")
	        
	 })
	@ApiOperation(value = "Realiza atualização a todos os parâmetros de um comprador.")
	public ResponseEntity<?> atualizar(@RequestBody CompradorInput CompradorInput,@PathVariable Long id) {
		
		try {
			
			CompradorOutput compradorOutput = cadastroComprador.atualizar(CompradorInput, id);
			
			return respostaStatus(HttpStatus.OK, compradorOutput);
			
		}catch(NotFoundWithId e) {
			
			return erroUsuarioNaoEncontrado(e.getMessage());
			
		}catch(InvalidTipoDeComprador e) {
			
			return erroUsuarioNaoEncontrado(e.getMessage());
			
		}catch(Exception e) {
			
			return erroServidor(e);
			
		}
		
	}

	@Override
	@DeleteMapping("/{id}")
	@ApiResponses(value = {
	        @ApiResponse(code=200, message = "Retorna o comprador deletado.", response = CompradorOutput.class),
	        @ApiResponse(code=404, message = "Retorna uma mensagem de não encontrado"),
	        @ApiResponse(code=400, message = "Retorna uma mensagem de erro de parâmetro"),
	        @ApiResponse(code=500, message = "Retorna uma mensagem de erro do servidor")
	        
	 })
	@ApiOperation(value = "Realiza a remoção de um comprador.")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		try {
			
			CompradorOutput compradorOutput = cadastroComprador.deletar(id);
			
			return ResponseEntity.status(HttpStatus.OK).body(compradorOutput);
			
			
		}catch(NotFoundWithId e) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HashMap<String, String>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				put("err",e.getMessage());
			}});
			
		}catch(CompradorHasCompras e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				put("err",e.getMessage());
			}});
			
		}
	}

}
