package com.ufsj.projetovaca.animal.applicationLayer.applicationService;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.animal.apresentationLayer.DTO.ProducaoLeiteOutput;
import com.ufsj.projetovaca.animal.apresentationLayer.assemblers.ProducaoLeiteAssembler;
import com.ufsj.projetovaca.animal.domainLayer.domainServices.CalculaValorTotalProducaoLeite;
import com.ufsj.projetovaca.animal.domainLayer.models.ProducaoLeite;
import com.ufsj.projetovaca.animal.domainLayer.repositories.ProducaoLeiteRepository;

@Service
public class EncontrarProducaoLeitePorAnimal {
	
	@Autowired
	ProducaoLeiteRepository producaoLeiteRepository;
	
	@Autowired
	ProducaoLeiteAssembler producaoLeiteAssembler;
	
	@Autowired
	CalculaValorTotalProducaoLeite calculaValorTotalProducaoLeite;
	
	public HashMap<String, Object> executar(long idAnimal){
		
		List<ProducaoLeite> producoesLeite = producaoLeiteRepository.findByAnimalIdAnimal(idAnimal);
		
		float total = calculaValorTotalProducaoLeite.executar(producoesLeite);
		
		List<ProducaoLeiteOutput> producoesLeiteOutput = producaoLeiteAssembler.converterColecaoOutput(producoesLeite);
		
		HashMap<String, Object> retorno = new HashMap<String, Object>();
		
		retorno.put("Total", total);
		
		retorno.put("ProducoesLeite", producoesLeiteOutput);
		
		return retorno;
	}
}
