package com.ufsj.projetovaca.animal.applicationLayer.applicationService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.animal.applicationLayer.exceptions.NotFoundCochoType;
import com.ufsj.projetovaca.animal.apresentationLayer.DTO.AnimalOutput;
import com.ufsj.projetovaca.animal.apresentationLayer.assemblers.AnimalAssembler;
import com.ufsj.projetovaca.animal.domainLayer.domainServices.ValidaTipoCocho;
import com.ufsj.projetovaca.animal.domainLayer.models.Animal;
import com.ufsj.projetovaca.animal.domainLayer.repositories.AnimalRepository;
import com.ufsj.projetovaca.fazenda.applicationLayer.exceptions.NotFoundWithId;
@Service
public class AcresentarIdaAoCochoAnimal {

	@Autowired
	AnimalRepository animalRepository;
	
	@Autowired
	ValidaTipoCocho validaTipoCocho;
	
	@Autowired
	AnimalAssembler animalAssembler;
	
	
	public AnimalOutput executar(long id, String tipo) throws NotFoundWithId, NotFoundCochoType{
		
		System.out.println(validaTipoCocho.executar(tipo));
		
		if(!validaTipoCocho.executar(tipo)) {
			
			throw new NotFoundCochoType("Tipo de cocho informado inválido");
			
		}
		
		Optional<Animal> opAnimal = animalRepository.findById(id);
		
		if(opAnimal.isEmpty()) {
			
			throw new NotFoundWithId("Não foi encontrado um animal com o id informado");
			
		}
		
		Animal animal = opAnimal.get();
		
		animal.incrementaIdaAoCocho(tipo);
		
		Animal novoAnimal = animalRepository.save(animal);
		
		AnimalOutput animalOutput = animalAssembler.converterOutput(novoAnimal);
		
		return animalOutput;
		
		
		
	}
	
}
