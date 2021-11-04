package com.ufsj.projetovaca.animal.applicationLayer.applicationService;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.ufsj.projetovaca.animal.apresentationLayer.DTO.AnimalInput;
import com.ufsj.projetovaca.animal.apresentationLayer.DTO.AnimalInputAtualizacao;
import com.ufsj.projetovaca.animal.apresentationLayer.DTO.AnimalOutput;
import com.ufsj.projetovaca.animal.apresentationLayer.assemblers.AnimalAssembler;
import com.ufsj.projetovaca.animal.domainLayer.domainServices.ValidaExisteLote;
import com.ufsj.projetovaca.animal.domainLayer.models.Animal;
import com.ufsj.projetovaca.animal.domainLayer.models.Lote;
import com.ufsj.projetovaca.animal.domainLayer.repositories.AnimalRepository;
import com.ufsj.projetovaca.animal.domainLayer.repositories.LoteRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufsj.projetovaca.animal.applicationLayer.exceptions.NotFoundAtributo;
import com.ufsj.projetovaca.animal.applicationLayer.exceptions.NotFoundWithId;


@Service

public class CadastroAnimal{	
	
	@Autowired
	AnimalAssembler animalAssembler;
	
	@Autowired
	AnimalRepository animalRepository;
	
	@Autowired
	LoteRepository loteRepository;
	
	@Autowired
	ValidaExisteLote validaExisteLote;
	
	
	public AnimalOutput cadastrar(AnimalInput animalInput) throws NotFoundWithId{
		
		boolean existeLote = validaExisteLote.execute(animalInput.getIdLote());
		
		if(!existeLote) {
			
			throw new NotFoundWithId("Não foi encontrado lote com o id informado");
			
		}
		
		Animal animal = animalAssembler.converterEntidade(animalInput);
		
		//animal.relacionarComLote(animalInput.getIdLote());
		
		animal.InicializarNovoAnimal();
		
		
		
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
	
	public AnimalOutput deletar(long id) throws NotFoundWithId {
		Optional<Animal> opAnimal = animalRepository.findById(id);
		if(opAnimal.isEmpty()) {
			throw new NotFoundWithId("Não foi encontrado um animal com o id informado");
		}
		Animal animal = opAnimal.get();
		animalRepository.delete(animal);
		AnimalOutput animalOutput = animalAssembler.converterOutput(animal);
		return animalOutput;
	}
	public AnimalOutput atualizar(long id,AnimalInputAtualizacao animalInput) throws NotFoundWithId {
		Optional<Animal> opAnimal = animalRepository.findById(id);
		Optional<Lote> opLote = loteRepository.findById(animalInput.getIdLote());
		if(opAnimal.isEmpty()) {
			throw new NotFoundWithId("Não foi encontrado um animal com o id informado");
		}
		if(opLote.isEmpty()) {
			throw new NotFoundWithId("Não foi encontrado um lote com o id informado");
		}
		Animal animal = opAnimal.get();
		Animal animalAtt = animalAssembler.converterAtualizacaoEntidade(animalInput);
		BeanUtils.copyProperties(animalAtt, animal,"id");
		Animal novoAnimal = animalRepository.save(animal);
		
		AnimalOutput animalOutput = animalAssembler.converterOutput(novoAnimal);
		return animalOutput;
		
		
	}
	public AnimalOutput atualizarAtributos(long id,HashMap<String,Object> atributosInput) throws NotFoundWithId {
		HashMap<String,Object> atributos = animalAssembler.converterAtributosEntidade(atributosInput);
		ObjectMapper objectMapper = new ObjectMapper();
		Optional<Animal> opAnimal = animalRepository.findById(id);
		if(opAnimal.isEmpty()) {
			throw new NotFoundWithId("Não foi encontrado um animal com o id informado");
		}
		
		if(atributosInput.containsKey("idLote")) {
			Optional<Lote> opLote = loteRepository.findById(Long.parseLong(atributosInput.get("idLote").toString()));
			
			if(opLote.isEmpty()) {
				throw new NotFoundWithId("Não foi encontrado um lote com o id informado");
			}
		}
		Animal animal = opAnimal.get();
		System.out.println(atributos);
		Animal animalNovosAtributos = objectMapper.convertValue(atributos, Animal.class);
		
		atributos.forEach((nomeAtributo,valorAtributo)->{
			Field field = animalAssembler.validarAtributoComIs(nomeAtributo);
			
			if(field == null) {
				
				throw new NotFoundAtributo("Atributo:"+nomeAtributo+" inválido");
				
			}
			
			field.setAccessible(true);
			
			Object novoValor = ReflectionUtils.getField(field, animalNovosAtributos);
			
			ReflectionUtils.setField(field, animal, novoValor);
		});
		System.out.println(animalNovosAtributos);
		Animal novoAnimal = animalRepository.save(animal);
		AnimalOutput animalOutput = animalAssembler.converterOutput(novoAnimal);
		return animalOutput;
		
	}
	
}
