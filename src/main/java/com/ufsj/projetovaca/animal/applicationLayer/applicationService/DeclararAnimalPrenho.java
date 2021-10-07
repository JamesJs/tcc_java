package com.ufsj.projetovaca.animal.applicationLayer.applicationService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.animal.apresentationLayer.DTO.AnimalInput;
import com.ufsj.projetovaca.animal.apresentationLayer.DTO.AnimalOutput;
import com.ufsj.projetovaca.animal.domainLayer.models.Animal;
import com.ufsj.projetovaca.animal.domainLayer.repositories.AnimalRepository;
import com.ufsj.projetovaca.animal.infraLayer.assembler.AssemblerAdapter;
import com.ufsj.projetovaca.animal.infraLayer.assembler.Conversores;
import com.ufsj.projetovaca.fazenda.applicationLayer.exceptions.NotFoundWithId;

@Service
public class DeclararAnimalPrenho {
	
	
	Conversores<AnimalInput, AnimalOutput, Animal> conversores = 
			new Conversores<AnimalInput, AnimalOutput, Animal>();
	
	AssemblerAdapter<AnimalOutput, Animal> conversorOutput = 
			conversores.criarConversorOutput(AnimalOutput.class);
	
	@Autowired
	AnimalRepository animalRepository;
	
	
	
	public AnimalOutput execute(long idAnimal) throws NotFoundWithId{
		
		Optional<Animal> opAnimal = animalRepository.findById(idAnimal);
		
		if(opAnimal.isEmpty()) {
			
			throw new NotFoundWithId("Não foi encontrado animal com o id informado");
		
		}
		
		Animal animal = opAnimal.get();
		
		animal.setPrenha(!animal.isPrenha());
		
		Animal novoAnimal = animalRepository.save(animal);
		
		AnimalOutput animalOutput = conversorOutput.converterUnitario(novoAnimal);
		
		return animalOutput;
		
	}
}
