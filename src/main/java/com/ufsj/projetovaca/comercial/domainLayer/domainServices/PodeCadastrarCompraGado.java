package com.ufsj.projetovaca.comercial.domainLayer.domainServices;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.comercial.domainLayer.models.Comprador;
import com.ufsj.projetovaca.comercial.domainLayer.repositories.CompradorRepository;
@Service
public class PodeCadastrarCompraGado {
	
	@Autowired
	CompradorRepository compradorRepository;
	
	public boolean execute(long id) {
		
		Optional<Comprador> opComprador = compradorRepository.findById(id);
		
		if(opComprador.isEmpty()) {
			
			return false;
		
		}
		
		return true;
		
		
	}
}
