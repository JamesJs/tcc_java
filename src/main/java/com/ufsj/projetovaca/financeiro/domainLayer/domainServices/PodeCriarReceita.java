package com.ufsj.projetovaca.financeiro.domainLayer.domainServices;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.financeiro.domainLayer.models.TipoReceita;
import com.ufsj.projetovaca.financeiro.domainLayer.repositories.TipoReceitaRepository;
@Service
public class PodeCriarReceita {
	@Autowired
	TipoReceitaRepository tipoReceitaRepository;
	public boolean execute(Long idTipoReceita) {
		Optional<TipoReceita> tipoReceita = tipoReceitaRepository.findById(idTipoReceita);
		if(tipoReceita.isEmpty()) {
			return false;
			
		}
		
		return true;
	}
}
