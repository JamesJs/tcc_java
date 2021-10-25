package com.ufsj.projetovaca.animal.apresentationLayer.controllers.producaoLeite;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ufsj.projetovaca.animal.applicationLayer.applicationService.CadastroProducaoLeite;
import com.ufsj.projetovaca.animal.applicationLayer.applicationService.EncontrarProducaoDeLeiteEntreDatas;
import com.ufsj.projetovaca.animal.apresentationLayer.DTO.ProducaoLeiteInput;
import com.ufsj.projetovaca.animal.apresentationLayer.DTO.ProducaoLeiteOutput;
import com.ufsj.projetovaca.animal.apresentationLayer.DTO.ProducaoLeiteTotalOutputResponse;
import com.ufsj.projetovaca.animal.domainLayer.models.ProducaoLeite;
import com.ufsj.projetovaca.animal.apresentationLayer.controllers.ACrudController;
import com.ufsj.projetovaca.animal.applicationLayer.exceptions.NotFoundWithId;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@RestController()
@Api(tags = {"Produção de leite"},description = "Endpoints relacionados à Produção de leite")
@RequestMapping("/producaoLeite")
public class ProducaoLeiteController 
						extends ACrudController<ProducaoLeiteInput, ProducaoLeiteOutput, ProducaoLeite> {
	
	@Autowired
	CadastroProducaoLeite cadastroProducaoLeite;
	
	@Autowired
	EncontrarProducaoDeLeiteEntreDatas encontrarProducaoDeLeiteEntreDatas;
	@GetMapping(produces = "application/json")
	@Override
	@ApiOperation(value = "Lista todas as realizações de produção de leite cadastradas no sistema.",tags = {"Produção de leite"})
	@ApiResponses(value = {
	        @ApiResponse(code=200, message = "Retorna todas as produções de leite e o total em litros.", response = ProducaoLeiteTotalOutputResponse.class),
	        @ApiResponse(code=500, message = "Retorna uma mensagem de erro do servidor")
	        
	 })
	public ResponseEntity<?> listar() {
		try {
			
			HashMap<String, Object> retorno = cadastroProducaoLeite.listar();
			
			return ResponseEntity.status(HttpStatus.OK).body(retorno);
			
		}catch(Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			
		}
		
	}
	
	@PostMapping
	@Override
	@ApiResponses(value = {
	        @ApiResponse(code=200, message = "Retorna a nova produção de leite cadastrada.", response = ProducaoLeiteOutput.class),
	        @ApiResponse(code=500, message = "Retorna uma mensagem de erro do servidor"),
	        @ApiResponse(code=404, message = "Retorna uma mensagem de erro de parâmetro")
	        
	 })
	@ApiOperation(value = "Cadastra uma nova produção de leite no sistema.",tags = {"Produção de leite"})
	public ResponseEntity<?> criar(@RequestBody ProducaoLeiteInput CadastroProducaoLeiteInput) {
		
		try {
			
			ProducaoLeiteOutput producaoLeiteOutput = cadastroProducaoLeite.criar(CadastroProducaoLeiteInput);
			
			return respostaStatus(HttpStatus.OK, producaoLeiteOutput);
			
		}catch(NotFoundWithId e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
		
		catch(Exception e) {
			
			return erroServidor(e);
			
		}
	}

	@Override
	@DeleteMapping("/{id}")
	@ApiResponses(value = {
	        @ApiResponse(code=200, message = "Retorna a produção de leite deletada.", response = ProducaoLeiteOutput.class),
	        @ApiResponse(code=404, message = "Retorna uma mensagem de não encontrado."),
	        @ApiResponse(code=500, message = "Retorna uma mensagem de erro do servidor")
	        
	 })
	@ApiOperation(value = "Deleta uma produção de leite do sistema.",tags = {"Produção de leite"})
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		
		try {
			
			ProducaoLeiteOutput producaoLeiteOutput = cadastroProducaoLeite.deletar(id);
			
			return ResponseEntity.status(HttpStatus.OK).body(producaoLeiteOutput);
			
		}catch(NotFoundWithId e) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			
		}catch(Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			
		}
	}

	@PutMapping("/{id}")
	@ApiResponses(value = {
	        @ApiResponse(code=200, message = "Retorna a produção de leite atualizada.", response = ProducaoLeiteOutput.class),
	        @ApiResponse(code=404, message = "Retorna uma mensagem de não encontrado."),
	        @ApiResponse(code=500, message = "Retorna uma mensagem de erro do servidor.")
	        
	 })
	@ApiOperation(value = "Atualiza uma produção de leite do sistema.",tags = {"Produção de leite"})
	public ResponseEntity<?> atualizar(@RequestBody ProducaoLeiteInput producaoLeiteInput,@PathVariable Long id) {
		try {
			
			ProducaoLeiteOutput producaoLeiteOutput = cadastroProducaoLeite.atualizar(id,producaoLeiteInput);
			
			return ResponseEntity.status(HttpStatus.OK).body(producaoLeiteOutput);
			
			
		}catch(NotFoundWithId e) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HashMap<String,String>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				put("err",e.getMessage());
			}});
			
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HashMap<String,Object>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
				put("err",e);
			}});
		}
	}
	@ApiResponses(value = {
	        @ApiResponse(code=200, message = "Array de produções de leite realizadas durante o período especificado.", response = ProducaoLeiteOutput.class),
	        @ApiResponse(code=500, message = "Retorna uma mensagem de erro do servidor")
	        
	 })
	@ApiOperation(value = "Lista as produções de leite relizadas entre as datas informadas. Devem ser informadas as duas datas",tags = {"Produção de leite"})
	@GetMapping(params = {"dataInicial","dataFinal"},produces = "application/json")
	public ResponseEntity<?> 
		listarEntreDatas(@RequestParam String dataInicial,@RequestParam String dataFinal){
		
		try {
			
			Date dataInicialConvert = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(dataInicial);
			
			Date dataFinalConvert = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(dataFinal);			
			
			HashMap<String, Object> retorno = 
					encontrarProducaoDeLeiteEntreDatas.executar(dataInicialConvert,dataFinalConvert);
			
			return respostaStatus(HttpStatus.OK, retorno);
			
		}catch(Exception e) {
			
			return erroServidor(e);
			
		}	
		
	}

}
