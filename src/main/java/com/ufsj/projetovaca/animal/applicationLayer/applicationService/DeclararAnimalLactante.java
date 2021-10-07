package com.ufsj.projetovaca.animal.applicationLayer.applicationService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.animal.apresentationLayer.DTO.AnimalOutput;
import com.ufsj.projetovaca.animal.apresentationLayer.assemblers.AnimalAssembler;
import com.ufsj.projetovaca.animal.domainLayer.models.Animal;
import com.ufsj.projetovaca.animal.domainLayer.repositories.AnimalRepository;
import com.ufsj.projetovaca.fazenda.applicationLayer.exceptions.NotFoundWithId;
@Service
public class DeclararAnimalLactante {
	
	@Autowired
	AnimalRepository animalRepository;
	
	@Autowired
	AnimalAssembler animalAssembler;
	
	public AnimalOutput executar(Long idAnimal) throws NotFoundWithId{
		
		Optional<Animal> opAnimal = animalRepository.findById(idAnimal);
		
		if(opAnimal.isEmpty()) {
			
			throw new NotFoundWithId("Não foi encontrado um animal com o id informado");
			
		}
		
		Animal animal = opAnimal.get();
		
		animal.setLactacao(!animal.isLactacao());
		
		Animal novoAnimal = animalRepository.save(animal);
		
		AnimalOutput animalOutput = animalAssembler.converterOutput(novoAnimal);
		
		return animalOutput;
		
		
		
		
	}
}
