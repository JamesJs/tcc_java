package com.ufsj.projetovaca.animal.applicationLayer.applicationService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.animal.apresentationLayer.DTO.AnimalInput;
import com.ufsj.projetovaca.animal.apresentationLayer.DTO.AnimalOutput;
import com.ufsj.projetovaca.animal.apresentationLayer.assemblers.AnimalAssembler;
import com.ufsj.projetovaca.animal.domainLayer.domainServices.ValidaExisteLote;
import com.ufsj.projetovaca.animal.domainLayer.models.Animal;
import com.ufsj.projetovaca.animal.domainLayer.repositories.AnimalRepository;
import com.ufsj.projetovaca.fazenda.applicationLayer.exceptions.NotFoundWithId;



@Service
public class CadastroAnimal {	
	
	@Autowired
	AnimalAssembler animalAssembler;
	
	@Autowired
	AnimalRepository animalRepository;
	
	@Autowired
	ValidaExisteLote validaExisteLote;
	
	
	public AnimalOutput cadastrar(AnimalInput animalInput) throws NotFoundWithId{
		
		boolean existeLote = validaExisteLote.execute(animalInput.getIdLote());
		
		if(!existeLote) {
			
			throw new NotFoundWithId("Não foi encontrado lote com o id informado");
			
		}
		
		Animal animal = animalAssembler.converterEntidade(animalInput);
		
		//animal.relacionarComLote(animalInput.getIdLote());
		
		System.out.println(animal);
		
		Animal novoAnimal = animalRepository.save(animal);	
		
		AnimalOutput animalOutput = animalAssembler.converterOutput(novoAnimal);
		
		return animalOutput;
			
	}
	public List<AnimalOutput> listar(){
		
		List<Animal> animais = animalRepository.findAll();
		
		return animalAssembler.converterColecaoOutput(animais);
		
	}
	
	public AnimalOutput vender(long id) throws NotFoundWithId {
		
		Optional<Animal> opAnimal = animalRepository.findById(id);
		
		if(opAnimal.isEmpty()) {
			
			throw new NotFoundWithId("Não foi encontrado um animal com o id informado");
			
		}
		
		Animal animal = opAnimal.get();
		
		animal.setVendido(!animal.isVendido());
		
		Animal novoAnimal = animalRepository.save(animal);
		
		AnimalOutput animalOutput = animalAssembler.converterOutput(novoAnimal);
		
		return animalOutput;
		
	}
	
}
