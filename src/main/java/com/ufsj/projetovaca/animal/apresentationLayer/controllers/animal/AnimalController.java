package com.ufsj.projetovaca.animal.apresentationLayer.controllers.animal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ufsj.projetovaca.animal.applicationLayer.applicationService.AcresentarIdaAoCochoAnimal;
import com.ufsj.projetovaca.animal.applicationLayer.applicationService.CadastroAnimal;
import com.ufsj.projetovaca.animal.applicationLayer.exceptions.NotFoundCochoType;
import com.ufsj.projetovaca.animal.apresentationLayer.DTO.AnimalInput;
import com.ufsj.projetovaca.animal.apresentationLayer.DTO.AnimalOutput;
import com.ufsj.projetovaca.animal.domainLayer.models.Animal;
import com.ufsj.projetovaca.comercial.apresentationLayer.controllers.ACrudController;
import com.ufsj.projetovaca.fazenda.applicationLayer.exceptions.NotFoundWithId;
@RestController
@RequestMapping("/animal")
public class AnimalController extends ACrudController<AnimalInput, AnimalOutput, Animal> {
	
	
	@Autowired
	CadastroAnimal cadastroAnimal;
	
	@Autowired
	AcresentarIdaAoCochoAnimal acresentarIdaAoCocho;
	
	@GetMapping
	@Override
	public ResponseEntity<?> listar() {
		try {
			
			List<AnimalOutput> animaisOutput = cadastroAnimal.listar();
			
			return respostaStatus(HttpStatus.OK, animaisOutput);		
		
		}catch(Exception e) {
			
			return erroServidor(e);
			
		}

		
	}
	@PostMapping
	@Override
	public ResponseEntity<?> criar(@RequestBody AnimalInput AnimalInput) {
		try {
			
			AnimalOutput animalOutput = cadastroAnimal.cadastrar(AnimalInput);
			
			return respostaStatus(HttpStatus.OK,animalOutput);
			
		}catch(Exception e) {

			return erroServidor(e);
			
		}
	}

	@Override
	public ResponseEntity<?> deletar(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@PatchMapping("/{id}/vendido")
	public ResponseEntity<?> vender(@PathVariable Long id){
		
		try {
			
			AnimalOutput animalOutput = cadastroAnimal.vender(id);
			
			return ResponseEntity.status(HttpStatus.OK).body(animalOutput);
			
		}catch(NotFoundWithId e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			
		}catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			
		}	
		
	}
	
	@PatchMapping(path = "/{id}/cocho",params = {"tipo"})
	public ResponseEntity<?> idaAoCocho(@RequestParam String tipo,@PathVariable long id) {
		
		try {
			
			AnimalOutput animalOutput = acresentarIdaAoCocho.executar(id, tipo);
			
			return ResponseEntity.status(HttpStatus.OK).body(animalOutput);
			
		}catch(NotFoundWithId e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			
		}catch(NotFoundCochoType e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			
		}catch(Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			
		}
		
		
		
		
		
	}
	
	@Override
	public ResponseEntity<?> atualizar(AnimalInput Input, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
