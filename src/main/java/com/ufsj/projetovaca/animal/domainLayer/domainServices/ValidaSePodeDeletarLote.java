package com.ufsj.projetovaca.animal.domainLayer.domainServices;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.animal.domainLayer.models.Animal;
import com.ufsj.projetovaca.animal.domainLayer.repositories.AnimalRepository;
@Service
public class ValidaSePodeDeletarLote {
	
	@Autowired
	AnimalRepository animalRepository;
	
	
	public boolean execute(long idLote){
		Optional<List<Animal>> opAnimal = animalRepository.findByloteIdLote(idLote);
		System.out.println(opAnimal.get());
		if(opAnimal.get().isEmpty()) {
			
			return true;
			
		}
		
		return false;
	}
}
