package com.ufsj.projetovaca.animal.apresentationLayer.assemblers;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.ufsj.projetovaca.animal.apresentationLayer.DTO.AnimalInput;
import com.ufsj.projetovaca.animal.apresentationLayer.DTO.AnimalInputAtualizacao;
import com.ufsj.projetovaca.animal.apresentationLayer.DTO.AnimalOutput;
import com.ufsj.projetovaca.animal.apresentationLayer.DTO.LoteOutput;
import com.ufsj.projetovaca.animal.domainLayer.models.Animal;
import com.ufsj.projetovaca.animal.domainLayer.models.Lote;
import com.ufsj.projetovaca.animal.domainLayer.repositories.LoteRepository;
@Service
public class AnimalAssembler {
	@Autowired
	LoteRepository loteRepository;
	
	public Animal converterEntidade(AnimalInput animalInput) {
		ModelMapper modelMapper = new ModelMapper();
		
		modelMapper.typeMap(AnimalInput.class,Animal.class).addMappings(mp -> {   
			mp.skip(Animal::setId);
		});
		
		return modelMapper.map(animalInput, Animal.class);
		
	}
	public HashMap<String,Object> converterAtributosEntidade(HashMap<String,Object> atributosAnimalInput){
		
		HashMap<String,Object> atributosAnimal = new HashMap<String,Object>();
		
		atributosAnimalInput.forEach((nomeAtributo,valorAtributo)->{
			
			if(nomeAtributo.equals("idLote")) {
				HashMap<String,Object> lote = new HashMap<String,Object>();
				lote.put("idLote", valorAtributo);
				atributosAnimal.put("lote", lote);
			}else {
				atributosAnimal.put(nomeAtributo, valorAtributo);
			}		
		});
		return atributosAnimal;
		
	}
	public Field validarAtributoComIs(String atributo){
		Field field;
		if(atributo.equals("vendido")||atributo.equals("lactacao")||atributo.equals("prenha")) {
			String novoNomeAtributo = atributo.substring(0,1).toUpperCase() + atributo.substring(1);
			
			field = ReflectionUtils.findField(Animal.class, "is"+novoNomeAtributo);
		}else field = ReflectionUtils.findField(Animal.class, atributo);
		return field;
		
	}
	public AnimalOutput converterOutput(Animal animal) {
		
		AnimalOutput animalOutput = new AnimalOutput();
		
		animalOutput.setBrinco(animal.getBrinco());
		
		animalOutput.setId(animal.getId());
		
		animalOutput.setIdaAoCochoAgua(animal.getIdaAoCochoAgua());
		
		animalOutput.setIdaAoCochoRacao(animal.getIdaAoCochoRacao());
		
		animalOutput.setIdaAoCochoSal(animal.getIdaAoCochoSal());
		
		animalOutput.setLactacao(animal.isLactacao());
		
		animalOutput.setPrenha(animal.isPrenha());
		
		animalOutput.setVendido(animal.isVendido());

		Optional<Lote> opLote = loteRepository.findById(animal.getLote().getIdLote());
		
		if(opLote.isEmpty() || opLote.get().getId() == 0) {
			
			animalOutput.setLote(null);
			
			return animalOutput;
			
		}
		
		LoteOutput loteOutput = new LoteOutput();
		
		Lote lote = opLote.get();
		
		loteOutput.setId(lote.getId());
		
		loteOutput.setNome(lote.getNome());
		
		animalOutput.setLote(loteOutput);
		
		return animalOutput;
		
	}
	
	public Animal converterAtualizacaoEntidade(AnimalInputAtualizacao animal) {
			ModelMapper modelMapper = new ModelMapper();
			modelMapper.typeMap(AnimalInputAtualizacao.class,Animal.class).addMappings(mp -> {   
				mp.skip(Animal::setId);
			});
			return modelMapper.map(animal, Animal.class);
			
			
	}
	
	public List<AnimalOutput> converterColecaoOutput(List<Animal> origemList){
			
			List<AnimalOutput> destinoList = origemList.stream().
					map(value -> this.converterOutput(value)).collect(Collectors.toList());
			return destinoList;
	}
	
}
	