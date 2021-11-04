package com.ufsj.projetovaca.financeiro.domainLayer.domainServices;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.financeiro.domainLayer.models.TipoConta;
import com.ufsj.projetovaca.financeiro.domainLayer.repositories.TipoContaRepository;
@Service
public class PodeCriarConta {
	@Autowired
	TipoContaRepository tipoContaRepository;
	
	public boolean execute(Long idTipo) {
		
		Optional<TipoConta> opTipoConta = tipoContaRepository.findById(idTipo);
		if(opTipoConta.isEmpty()) {
			
			return false;
			
		}
		
		return true;
		
		
		
	}
}
