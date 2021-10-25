package com.ufsj.projetovaca.fazenda.applicationLayer.applicationService;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufsj.projetovaca.fazenda.applicationLayer.exceptions.FoundCochoInFazenda;
import com.ufsj.projetovaca.fazenda.applicationLayer.exceptions.NotFoundWithId;
import com.ufsj.projetovaca.fazenda.apresentationLayer.DTO.FazendaInput;
import com.ufsj.projetovaca.fazenda.apresentationLayer.DTO.FazendaOutput;
import com.ufsj.projetovaca.fazenda.apresentationLayer.assemblers.FazendaAssembler;
import com.ufsj.projetovaca.fazenda.domainLayer.models.Fazenda;
import com.ufsj.projetovaca.fazenda.domainLayer.repositories.FazendaRepository;


@Service
public class CadastroFazenda {
	
	@Autowired
	FazendaRepository fazendaRepository;
	
	@Autowired
	FazendaAssembler fazendaAssembler;
	
	
	
	public FazendaOutput salvar(FazendaInput fazendaInput) {
		Fazenda fazenda = fazendaAssembler.converterEntidade(fazendaInput);
		
		
		fazenda.setVendida(false);
		
		FazendaOutput fazendaOutput = fazendaAssembler.converterOutput(fazendaRepository.save(fazenda));
		
		return fazendaOutput;
		
	}
	public FazendaOutput vender(Long id) throws NotFoundWithId {
		
		Optional<Fazenda> opFazenda = fazendaRepository.findById(id);
		
		if(opFazenda.isEmpty()) {
			
			throw new NotFoundWithId("Fazenda não encontrada");
			
		}
		
		Fazenda fazenda = opFazenda.get();
		
		fazenda.setVendida(!fazenda.isVendida());
		
		FazendaOutput fazendaOutput = fazendaAssembler.converterOutput(fazendaRepository.save(fazenda));
		
		return fazendaOutput;
	}
	@Transactional("fazendaTransactionManager")
	public FazendaOutput deletar(Long id,String queryParam) throws NotFoundWithId, FoundCochoInFazenda {
		Optional<Fazenda> opFazenda = fazendaRepository.findById(id);
		
		if(opFazenda.isEmpty()) {
			
			throw new NotFoundWithId("Fazenda não encontrada");
			
		}
		Fazenda fazenda = opFazenda.get();
		if(!fazenda.podeRemover()&& !queryParam.equals("yes")) {
			throw new FoundCochoInFazenda("Foi encontrado um cocho pertencente à essa fazenda. Para deletar a fazenda e todos os seus cochos envie um query param force=yes");
		}
		fazendaRepository.delete(fazenda);
		FazendaOutput fazendaOutput = fazendaAssembler.converterOutput(fazenda);
		return fazendaOutput;
		
	}
}
