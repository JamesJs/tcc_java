package com.ufsj.projetovaca.animal.apresentationLayer.controllers.animal.lactacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufsj.projetovaca.animal.applicationLayer.applicationService.DeclararAnimalLactante;
import com.ufsj.projetovaca.animal.apresentationLayer.DTO.AnimalOutput;
import com.ufsj.projetovaca.fazenda.applicationLayer.exceptions.NotFoundWithId;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/animal")
@Api(tags = {"Animal"},description = "Endpoints relacionados aos animais")
public class LactacaoController {
	
	@Autowired
	DeclararAnimalLactante declararAnimalLactante;
	
	@PatchMapping(path="/{id}/lactacao",produces = "application/json")
	@ApiOperation(value = "Altera o estado do animal para em lactação.",tags = {"Animal"})
	@ApiResponses(value = {
	        @ApiResponse(code=200, message = "Retorna os animal com o campo lactacao alterado.", response = AnimalOutput.class),
	        @ApiResponse(code=500, message = "Retorna uma mensagem de erro do servidor"),
	        @ApiResponse(code=404, message = "Retorna uma mensagem de não encontrado")
	        
	 })
	public ResponseEntity<?> declararAnimalLactante(@PathVariable Long id){
		try {
			
			AnimalOutput animalOutput = declararAnimalLactante.executar(id);
			
			return ResponseEntity.status(HttpStatus.OK).body(animalOutput);
			
		}catch(NotFoundWithId e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			
		}
		
	}
	
}
