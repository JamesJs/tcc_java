package com.ufsj.projetovaca.animal.applicationLayer.applicationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.animal.apresentationLayer.DTO.LoteInput;
import com.ufsj.projetovaca.animal.apresentationLayer.DTO.LoteOutput;
import com.ufsj.projetovaca.animal.apresentationLayer.assemblers.LoteAssembler;
import com.ufsj.projetovaca.animal.domainLayer.models.Lote;
import com.ufsj.projetovaca.animal.domainLayer.repositories.LoteRepository;


@Service
public class CadastroLote {
	
	
	@Autowired
	LoteRepository loteRepository;
	
	@Autowired
	LoteAssembler loteAssembler;
	
	
	
	public LoteOutput cadastrar(LoteInput loteInput) {
		
		Lote lote = loteAssembler.converterEntidade(loteInput);
		
		Lote novoLote = loteRepository.save(lote);
		
		LoteOutput loteOutput = loteAssembler.converterOutput(novoLote);
		
		return loteOutput;
		
	}
}
