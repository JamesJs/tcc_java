package com.ufsj.projetovaca.comercial.apresentationLayer.controllers.vendaGado;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufsj.projetovaca.comercial.applicationLayer.applicationService.CadastroVendaGado;
import com.ufsj.projetovaca.comercial.domainLayer.models.VendaGado;
import com.ufsj.projetovaca.comercial.domainLayer.models.embedded.Animal;
import com.ufsj.projetovaca.comercial.domainLayer.repositories.VendaGadoRepository;

@RestController
public class VendaGadoController {
	
	@Autowired
	VendaGadoRepository vendaGadoRepository;
	
	@Autowired
	CadastroVendaGado cadastroVendaGado;
	
	@GetMapping("vendaGado")
	public void teste(){
		
		Animal animal = new Animal();
		animal.setIdBrinco("asda");
		List<Animal> animais = new ArrayList<>();
		animais.add(animal);
		VendaGado vendaGado = new VendaGado();
		vendaGado.setQuantAnimais(0);
		vendaGado.setValor(10);
		vendaGado.setData(new Date());
		vendaGado.setAnimais(animais);
		System.out.println(animais);
		vendaGadoRepository.save(vendaGado);
		
	}
	@GetMapping("vendaGado2")
	public void teste2() {
		System.out.println(cadastroVendaGado.listar());
	}
}
