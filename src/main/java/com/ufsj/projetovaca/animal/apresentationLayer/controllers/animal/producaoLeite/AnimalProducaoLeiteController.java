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
@RestController
@RequestMapping("/animal")
public class AnimalProducaoLeiteController {
	
	
	@Autowired
	EncontrarProducaoLeitePorAnimal encontrarProducaoLeitePorAnimal;
	
	
	@GetMapping("/{idAnimal}/producaoLeite")
	public ResponseEntity<?> producaoLeitePorAnimal(@PathVariable long idAnimal){
		try {
			
			HashMap<String, Object> retorno = encontrarProducaoLeitePorAnimal.executar(idAnimal);
			
			return ResponseEntity.status(HttpStatus.OK).body(retorno);
			
		}catch(Exception e) {
		
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			
		}
		
	}
	
	
}
