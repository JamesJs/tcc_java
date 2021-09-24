package com.ufsj.projetovaca.fazenda.applicationLayer.applicationService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.fazenda.domainLayer.models.MateriaPrima;
import com.ufsj.projetovaca.fazenda.domainLayer.repositories.MateriaPrimaRepository;

@Service
public class ExisteTipoDeMateriaPrima {
	
	@Autowired
	MateriaPrimaRepository materiaPrimaRepository;
	
	
	public MateriaPrima execute(String tipo){
	  Optional<MateriaPrima> opMateriaPrima = materiaPrimaRepository.findByTipo(tipo);
	  
	  if(opMateriaPrima.isEmpty()) {
		
		  return null;
	  }
	  
	  return opMateriaPrima.get();
	
	}
}
