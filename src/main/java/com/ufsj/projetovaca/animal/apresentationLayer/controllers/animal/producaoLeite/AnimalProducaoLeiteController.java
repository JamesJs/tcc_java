package com.ufsj.projetovaca.animal.apresentationLayer.controllers.animal.producaoLeite;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufsj.projetovaca.animal.applicationLayer.applicationService.EncontrarProducaoLeitePorAnimal;
import com.ufsj.projetovaca.animal.apresentationLayer.DTO.AnimalOutput;
import com.ufsj.projetovaca.animal.apresentationLayer.DTO.ProducaoLeiteTotalOutputResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@RestController
@RequestMapping("/animal")
@Api(tags = {"Animal"},description = "Endpoints relacionados aos animais")
public class AnimalProducaoLeiteController {
	
	
	@Autowired
	EncontrarProducaoLeitePorAnimal encontrarProducaoLeitePorAnimal;
	@ApiOperation(value = "Obtem a produção de leite por animal e o total de litros produzidos.",tags = {"Animal"})
	@ApiResponses(value = {
	        @ApiResponse(code=200, message = "Retorna todas as produções de leite realizadas e o total de litros..", response = ProducaoLeiteTotalOutputResponse.class),
	        @ApiResponse(code=500, message = "Retorna uma mensagem de erro do servidor"),
	        @ApiResponse(code=404, message = "Retorna uma mensagem de não encontrado")
	        
	 })
	
	@GetMapping(path="/{idAnimal}/producaoLeite",produces = "application/json")
	public ResponseEntity<?> producaoLeitePorAnimal(@PathVariable long idAnimal){
		try {
			
			HashMap<String, Object> retorno = encontrarProducaoLeitePorAnimal.executar(idAnimal);
			
			return ResponseEntity.status(HttpStatus.OK).body(retorno);
			
		}catch(Exception e) {
		
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			
		}
		
	}
	
	
}
