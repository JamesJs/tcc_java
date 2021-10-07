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

@RestController
@RequestMapping("/animal")
public class LactacaoController {
	
	@Autowired
	DeclararAnimalLactante declararAnimalLactante;
	
	@PatchMapping("/{id}/lactacao")
	public ResponseEntity<?> declararAnimalLactante(@PathVariable Long id){
		try {
			
			AnimalOutput animalOutput = declararAnimalLactante.executar(id);
			
			return ResponseEntity.status(HttpStatus.OK).body(animalOutput);
			
		}catch(NotFoundWithId e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			
		}
		
	}
	
}
