package com.ufsj.projetovaca.animal.applicationLayer.applicationService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.animal.apresentationLayer.DTO.ProducaoLeiteOutput;
import com.ufsj.projetovaca.animal.apresentationLayer.assemblers.ProducaoLeiteAssembler;
import com.ufsj.projetovaca.animal.domainLayer.models.ProducaoLeite;
import com.ufsj.projetovaca.animal.domainLayer.repositories.ProducaoLeiteRepository;
@Service
public class EncontrarProducaoDeLeiteEntreDatas {
	
	@Autowired
	ProducaoLeiteRepository producaoLeiteRepository;
	
	@Autowired
	ProducaoLeiteAssembler producaoLeiteAssembler;
	
	public HashMap<String, Object> executar(Date dataInicial,Date dataFinal){
		
		List<ProducaoLeite> producoesLeiteEntreDatas = 
				producaoLeiteRepository.findByDataBetween(dataInicial, dataFinal);
		
		float total = 0;
		
		for(ProducaoLeite producaoLeite:producoesLeiteEntreDatas) {
			
			total += producaoLeite.getQuantLitros(); 	
		
		}
		
		List<ProducaoLeiteOutput> producoesLeiteOutput = 
				producaoLeiteAssembler.converterColecaoOutput(producoesLeiteEntreDatas);
		
		HashMap<String, Object> retorno = new HashMap<String, Object>();
		
		retorno.put("Total", total);
		
		retorno.put("ProducoesLeite", producoesLeiteOutput);
		
		return retorno;		
		
	}
}
