package com.ufsj.projetovaca.comercial.applicationLayer.applicationService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.comercial.applicationLayer.exceptions.NotFoundWithId;
import com.ufsj.projetovaca.comercial.apresentationLayer.DTO.VendedorInput;
import com.ufsj.projetovaca.comercial.apresentationLayer.DTO.VendedorOutput;
import com.ufsj.projetovaca.comercial.apresentationLayer.assemblers.VendedorAssembler;
import com.ufsj.projetovaca.comercial.domainLayer.models.Vendedor;
import com.ufsj.projetovaca.comercial.domainLayer.repositories.VendedorRepository;

@Service
public class CadastroVendedor {
	
	@Autowired
	VendedorAssembler vendedorAssembler;
	
	@Autowired
	VendedorRepository vendedorRepository;
	
	
	
	public VendedorOutput salvar(VendedorInput vendedorInput) {
		
		Vendedor vendedor = vendedorAssembler.converterEntidade(vendedorInput);
		
		vendedor.setAtivo(true);
		
		Vendedor novoVendedor = vendedorRepository.save(vendedor);
		
		VendedorOutput vendedorOutput = vendedorAssembler.converterOutput(novoVendedor);
		
		return vendedorOutput;
		
	}
	
	public List<VendedorOutput> listar(){
		
		List<Vendedor> vendedores = vendedorRepository.findAll();
		
		List<VendedorOutput> vendedoresOutput = vendedorAssembler.converterColecaoOutput(vendedores);
		
		return vendedoresOutput;
		
	}
	
	public VendedorOutput desativar(Long id) throws NotFoundWithId {
		
		Optional<Vendedor> opVendedor = vendedorRepository.findById(id);
		
		if(opVendedor.isEmpty()) {
			
			throw new NotFoundWithId("Vendedor não encontrado com o id");
			
		}
		
		Vendedor vendedor = opVendedor.get();
		
		vendedor.setAtivo(!vendedor.isAtivo());
		
		Vendedor novoVendedor = vendedorRepository.save(vendedor);
		
		VendedorOutput vendedorOutput = vendedorAssembler.converterOutput(novoVendedor);
		
		return vendedorOutput;
			
	}
	
public VendedorOutput deletar(Long id) throws NotFoundWithId {
		
		Optional<Vendedor> opVendedor = vendedorRepository.findById(id);
		
		if(opVendedor.isEmpty()) {
			
			throw new NotFoundWithId("Vendedor não encontrado com o id");
			
		}
		
		Vendedor vendedor = opVendedor.get();
		
		vendedorRepository.delete(vendedor);
		
		VendedorOutput vendedorOutput = vendedorAssembler.converterOutput(vendedor);
		
		return vendedorOutput;
			
	}
	
	public VendedorOutput atualizar(Long id,VendedorInput vendedorInput) throws NotFoundWithId {
		
		Optional<Vendedor> opVendedor = vendedorRepository.findById(id);
		
		if(opVendedor.isEmpty()) {
			
			throw new NotFoundWithId("Não encontrado vendedor com esse id");
		}
		
		Vendedor vendedor = opVendedor.get();
		
		
		
		Vendedor novoVendedor = vendedorAssembler.converterEntidade(vendedorInput);
		
		BeanUtils.copyProperties(novoVendedor, vendedor,"id","ativo");
		
		VendedorOutput vendedorOutput = 
				vendedorAssembler.converterOutput(vendedorRepository.save(vendedor));
		
		return vendedorOutput;
		
		
		
	}
}
