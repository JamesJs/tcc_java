package com.ufsj.projetovaca.animal.apresentationLayer.assemblers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.animal.apresentationLayer.DTO.AnimalInput;
import com.ufsj.projetovaca.animal.apresentationLayer.DTO.AnimalOutput;
import com.ufsj.projetovaca.animal.domainLayer.models.Animal;
@Service
public class AnimalAssembler {
	
	
	public Animal converterEntidade(AnimalInput animalInput) {
		ModelMapper modelMapper = new ModelMapper();
		
		modelMapper.typeMap(AnimalInput.class,Animal.class).addMappings(mp -> {   
			mp.skip(Animal::setId);
		});
		
		return modelMapper.map(animalInput, Animal.class);
		
	}
	public AnimalOutput converterOutput(Animal animal) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		return modelMapper.map(animal, AnimalOutput.class);
		
	}
	
	public List<AnimalOutput> converterColecaoOutput(List<Animal> origemList){
			
			List<AnimalOutput> destinoList = origemList.stream().
					map(value -> this.converterOutput(value)).collect(Collectors.toList());
			return destinoList;
	}
	
}
	