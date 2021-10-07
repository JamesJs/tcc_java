package com.ufsj.projetovaca.animal.domainLayer.domainServices;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.animal.domainLayer.models.Lote;
import com.ufsj.projetovaca.animal.domainLayer.repositories.LoteRepository;

@Service
public class ValidaExisteLote {
	@Autowired
	LoteRepository loteRepository;
	public boolean execute(long idLote) {
		
		Optional<Lote> opLote = loteRepository.findById(idLote);
		
		if(opLote.isEmpty() && idLote != 0) {
			
			return false;
			
		}
		
		return true;
		
	}
}
