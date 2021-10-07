package com.ufsj.projetovaca.animal.domainLayer.domainServices;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.animal.domainLayer.models.Animal;
import com.ufsj.projetovaca.animal.domainLayer.repositories.AnimalRepository;
@Service
public class ValidaExisteAnimal {
	
	@Autowired
	AnimalRepository animalRepository;
	
	public boolean execute(long idAnimal){
		
		Optional<Animal> opAnimal = animalRepository.findById(idAnimal);
		
		if(opAnimal.isEmpty()) {
			
			return false;
			
		}
		
		return true;
		
	}
}
