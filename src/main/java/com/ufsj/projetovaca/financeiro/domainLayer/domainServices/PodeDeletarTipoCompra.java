package com.ufsj.projetovaca.financeiro.domainLayer.domainServices;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.financeiro.domainLayer.models.Conta;
import com.ufsj.projetovaca.financeiro.domainLayer.repositories.ContaRepository;
@Service
public class PodeDeletarTipoCompra {

	@Autowired
	ContaRepository contaRepository;
	
	public boolean execute(Long idTipoConta) {
		
		Optional<List<Conta>> opContas = contaRepository.findByTipo_idTipoConta(idTipoConta);
		if(opContas.get().isEmpty()) {
			return true;
		}
		return false;
		
	}
	
}
