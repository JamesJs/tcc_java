package com.ufsj.projetovaca.comercial.applicationLayer.applicationService;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.comercial.domainLayer.models.CompraLeite;
import com.ufsj.projetovaca.comercial.domainLayer.repositories.CompraLeiteRepository;


@Service
public class CancelaCompraLeiteConta {
	@Autowired
	CompraLeiteRepository compraLeiteRepository;
	public void execute(Long id) {
		Optional<CompraLeite> opCompraLeite	= compraLeiteRepository.findById(id);
		if(opCompraLeite.isEmpty()) {
			return;
		}
		System.out.println("Cancelando compra");
		CompraLeite compraLeite = opCompraLeite.get();
		compraLeite.setCancelado(true);
		compraLeiteRepository.save(compraLeite);
	}


}
