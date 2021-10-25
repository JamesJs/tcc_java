package com.ufsj.projetovaca.comercial.domainLayer.domainServices;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.comercial.domainLayer.models.CompraGado;
import com.ufsj.projetovaca.comercial.domainLayer.models.CompraLeite;
import com.ufsj.projetovaca.comercial.domainLayer.repositories.CompraGadoRepository;
import com.ufsj.projetovaca.comercial.domainLayer.repositories.CompraLeiteRepository;
@Service
public class PodeDeletarComprador {
	
	@Autowired
	CompraLeiteRepository compraLeiteRepository;
	@Autowired
	CompraGadoRepository compraGadoRepository;
	
	public boolean execute(Long idComprador){
		Optional<List<CompraLeite>> opCompraLeite = compraLeiteRepository.findByComprador_IdComprador(idComprador);
		Optional<List<CompraGado>> opCompraGado = compraGadoRepository.findByComprador_IdComprador(idComprador);
		if(opCompraGado.get().isEmpty() && opCompraLeite.get().isEmpty()) {
			return true;
		}
		return false;
	}
}
