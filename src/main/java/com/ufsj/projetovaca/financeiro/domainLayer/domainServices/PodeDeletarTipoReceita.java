package com.ufsj.projetovaca.financeiro.domainLayer.domainServices;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.financeiro.domainLayer.models.Receita;
import com.ufsj.projetovaca.financeiro.domainLayer.repositories.ReceitaRepository;
@Service
public class PodeDeletarTipoReceita {
	@Autowired
	ReceitaRepository receitaRepository;
	
	public boolean execute(Long idTipoReceita) {
		
		Optional<List<Receita>> opReceitas = receitaRepository.findByTipo_idTipoReceita(idTipoReceita);
		if(opReceitas.get().isEmpty()) {
			return true;
		}
		return false;
		
	}
}
