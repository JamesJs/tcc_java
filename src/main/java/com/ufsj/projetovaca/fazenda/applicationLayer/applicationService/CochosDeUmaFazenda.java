package com.ufsj.projetovaca.fazenda.applicationLayer.applicationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufsj.projetovaca.fazenda.apresentationLayer.DTO.CochoOutput;
import com.ufsj.projetovaca.fazenda.apresentationLayer.assemblers.CochoAssembler;
import com.ufsj.projetovaca.fazenda.domainLayer.models.Cocho;
import com.ufsj.projetovaca.fazenda.domainLayer.models.Fazenda;
import com.ufsj.projetovaca.fazenda.domainLayer.repositories.FazendaRepository;

@Service
public class CochosDeUmaFazenda {
	
	
	@Autowired
	FazendaRepository fazendaRepository;
	@Autowired
	CochoAssembler cochoAssembler;
	
	@Transactional("fazendaTransactionManager")
	public List<CochoOutput> execute(long idFazenda){
		Optional<Fazenda> opFazenda = fazendaRepository.findById(idFazenda);
		if(opFazenda.isEmpty()) {
			return null;
		}
		Fazenda fazenda = opFazenda.get();
		List<Cocho> cochos = fazenda.getCochos();
		List<CochoOutput> cochosOutput = new ArrayList<CochoOutput>();
		for(Cocho cocho:cochos) {
				CochoOutput cochoOutput = cochoAssembler.converterOutput(cocho);
				BeanUtils.copyProperties(cocho,cochoOutput);
				cochoOutput.setIdFazenda(idFazenda);
				cochosOutput.add(cochoOutput);
			}
		return cochosOutput;
	}
}
