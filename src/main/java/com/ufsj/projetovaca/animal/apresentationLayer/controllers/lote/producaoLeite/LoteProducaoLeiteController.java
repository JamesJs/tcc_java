package com.ufsj.projetovaca.animal.apresentationLayer.controllers.lote.producaoLeite;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufsj.projetovaca.animal.applicationLayer.applicationService.EncontrarProducaoLeitePorLote;
import com.ufsj.projetovaca.animal.apresentationLayer.DTO.AnimalOutput;
import com.ufsj.projetovaca.animal.apresentationLayer.DTO.ProducaoLeiteTotalOutputResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/lote")
@Api(tags = {"Lote"},description = "Endpoints relacionados aos lotes")
public class LoteProducaoLeiteController {
	
	
	@Autowired
	EncontrarProducaoLeitePorLote encontraProducaoLeitePorLote;
	@ApiOperation(value = "Lista a produção de leite por lote.",tags = {"Lote"})
	@ApiResponses(value = {
	        @ApiResponse(code=200, message = "Retorna todas as produções de leite do lote com o total calculado.", response = ProducaoLeiteTotalOutputResponse.class),
	        @ApiResponse(code=500, message = "Retorna uma mensagem de erro do servidor"),
	        @ApiResponse(code=404, message = "Retorna uma mensagem de não encontrado"),
	        
	 })
	@GetMapping("/{idLote}/producaoLeite")
	public ResponseEntity<?> producaoLeitePorLote(@PathVariable Long idLote){
		
		HashMap<String, Object> retorno = encontraProducaoLeitePorLote.executar(idLote);
		
		return ResponseEntity.status(HttpStatus.OK).body(retorno);
		
	}
	
	
}
