package com.ufsj.projetovaca.animal.apresentationLayer.controllers.lote;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufsj.projetovaca.animal.applicationLayer.applicationService.CadastroLote;
import com.ufsj.projetovaca.animal.applicationLayer.exceptions.ExisteAnimalPertencenteAoLote;
import com.ufsj.projetovaca.animal.apresentationLayer.DTO.AnimalOutput;
import com.ufsj.projetovaca.animal.apresentationLayer.DTO.LoteInput;
import com.ufsj.projetovaca.animal.apresentationLayer.DTO.LoteOutput;
import com.ufsj.projetovaca.animal.domainLayer.models.Lote;
import com.ufsj.projetovaca.comercial.apresentationLayer.controllers.ACrudController;
import com.ufsj.projetovaca.fazenda.applicationLayer.exceptions.NotFoundWithId;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@RestController
@RequestMapping(path = "/lote",produces = "application/json")
@Api(tags = {"Lote"},description = "Endpoints relacionados aos lotes")
public class LoteController extends ACrudController<LoteInput, LoteOutput, Lote> {
	
	@Autowired
	CadastroLote cadastroLote;
	@GetMapping(produces = "application/json")
	@Override
	@ApiOperation(value = "Lista todos os lotes cadastrados no sistema.",tags = {"Lote"})
	@ApiResponses(value = {
	        @ApiResponse(code=200, message = "Retorna os lotes cadastrados. É um array.", response = LoteOutput.class),
	        @ApiResponse(code=500, message = "Retorna uma mensagem de erro do servidor")
	        
	 })
	public ResponseEntity<?> listar() {
		
		try {
			
			List<LoteOutput> lotesOutput = cadastroLote.listar();
			
			return ResponseEntity.status(HttpStatus.OK).body(lotesOutput);
			
		}catch(Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
			
		}
	}
	@PostMapping
	@Override
	@ApiOperation(value = "Cadastra um novo lote no sistema.",tags = {"Lote"})
	@ApiResponses(value = {
	        @ApiResponse(code=200, message = "Retorna o lote cadastrado.", response = AnimalOutput.class),
	        @ApiResponse(code=500, message = "Retorna uma mensagem de erro do servidor")
	        
	 })
	public ResponseEntity<?> criar(@RequestBody LoteInput LoteInput) {
		
		try {
			
			LoteOutput loteOutput = cadastroLote.cadastrar(LoteInput);
			
			return respostaStatus(HttpStatus.OK, loteOutput);
			
		}catch(Exception e) {
			
			return erroServidor(e);
			
		}
		
		
		
	}

	@Override
	@ApiResponses(value = {
	        @ApiResponse(code=200, message = "Retorna o lote deletado", response = AnimalOutput.class),
	        @ApiResponse(code=404, message = "Retorna uma mensagem de não encontrado"),
	        @ApiResponse(code=404, message = "Retorna uma mensagem de erro de parâmetro"),
	        @ApiResponse(code=500, message = "Retorna uma mensagem de erro do servidor")
	        
	 })
	@ApiOperation(value = "Deleta um lote do sistema.",tags = {"Lote"})
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		try {
			
			LoteOutput loteOutput = cadastroLote.deletar(id);
			
			return respostaStatus(HttpStatus.OK, loteOutput);
			
		}catch(ExisteAnimalPertencenteAoLote e) {
			
			return respostaStatus(HttpStatus.BAD_REQUEST, new HashMap<String,Object>() {/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				put("err",e.getMessage());
			}});
			
		}catch(NotFoundWithId e) {
			
			return respostaStatus(HttpStatus.NOT_FOUND, new HashMap<String,Object>() {/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				put("err",e.getMessage());
			}});
			
		}
		
		
	}
	@ApiResponses(value = {
	        @ApiResponse(code=200, message = "Retorna o lote atualizado.", response = AnimalOutput.class),
	        @ApiResponse(code=404, message = "Retorna uma mensagem de não encontrado"),
	        @ApiResponse(code=500, message = "Retorna uma mensagem de erro do servidor")
	        
	 })
	@ApiOperation(value = "Realiza atualização a todos os parâmetros de um animal.",tags = {"Animal"})
	@PutMapping("/{id}")
	@Override
	public ResponseEntity<?> atualizar(@RequestBody LoteInput loteInput,@PathVariable Long id) {
		try {
			
			LoteOutput loteOutput = cadastroLote.atualizar(id,loteInput);
			
			return respostaStatus(HttpStatus.OK, loteOutput);
			
		}catch(NotFoundWithId e) {
			
			return respostaStatus(HttpStatus.NOT_FOUND, new HashMap<String,Object>() {/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				put("err",e.getMessage());
			}});
			
		}catch(Exception e) {
			
			return respostaStatus(HttpStatus.INTERNAL_SERVER_ERROR, new HashMap<String,Object>() {/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				put("err",e);
			}});
			
			
		}
	}

}
