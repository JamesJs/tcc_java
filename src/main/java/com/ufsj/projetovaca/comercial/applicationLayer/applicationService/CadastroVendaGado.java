package com.ufsj.projetovaca.comercial.applicationLayer.applicationService;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufsj.projetovaca.comercial.domainLayer.models.VendaGado;
import com.ufsj.projetovaca.comercial.domainLayer.repositories.VendaGadoRepository;

@Service
public class CadastroVendaGado {
	
	@Autowired
	VendaGadoRepository vendaGadoRepository; 
	
	
	
	@Transactional("comercialTransactionManager")
	
	public List<VendaGado> listar(){
		
		return vendaGadoRepository.findAll();
	
	}
	
	
}
