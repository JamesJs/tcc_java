package com.ufsj.projetovaca.animal.applicationLayer.applicationService;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.animal.domainLayer.domainServices.CalculaValorTotalProducaoLeite;
import com.ufsj.projetovaca.animal.domainLayer.models.Animal;
import com.ufsj.projetovaca.animal.domainLayer.models.ProducaoLeite;
import com.ufsj.projetovaca.animal.domainLayer.repositories.AnimalRepository;
import com.ufsj.projetovaca.animal.domainLayer.repositories.ProducaoLeiteRepository;
@Service

public class EncontrarProducaoLeitePorLote {
	
	@Autowired
	AnimalRepository animalRepository;
	
	@Autowired
	ProducaoLeiteRepository producaoLeiteRepository;
	
	@Autowired
	CalculaValorTotalProducaoLeite calculaValorTotalProducaoLeite;
	
	public HashMap<String, Object> executar(Long idLote){
		
		List<Animal> animais = animalRepository.getByloteIdLote(idLote);
		
		List<Long> idAnimais = animais.stream().map(animal->animal.getId()).collect(Collectors.toList());
		
		List<ProducaoLeite> producoesLeite = producaoLeiteRepository.findByAnimalIdAnimalIn(idAnimais);
		
		float total = calculaValorTotalProducaoLeite.executar(producoesLeite);
		
		HashMap<String, Object> retorno = new HashMap<String,Object>();
		
		retorno.put("Total", total);
		
		retorno.put("producoesLeite", producoesLeite);
		
		return retorno;
	}
}
