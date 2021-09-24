package com.ufsj.projetovaca.comercial.applicationLayer.applicationService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.comercial.applicationLayer.exceptions.NotFoundWithId;
import com.ufsj.projetovaca.comercial.apresentationLayer.DTO.VendedorInput;
import com.ufsj.projetovaca.comercial.apresentationLayer.DTO.VendedorOutput;
import com.ufsj.projetovaca.comercial.domainLayer.models.Vendedor;
import com.ufsj.projetovaca.comercial.domainLayer.repositories.VendedorRepository;
import com.ufsj.projetovaca.comercial.infraLayer.assembler.AssemblerAdapter;
import com.ufsj.projetovaca.comercial.infraLayer.assembler.Conversores;
@Service
public class CadastroVendedor {
	
	Conversores<VendedorInput,VendedorOutput,Vendedor> conversores = 
			new Conversores<VendedorInput,VendedorOutput,Vendedor>(); 
	
	
	AssemblerAdapter<Vendedor, VendedorInput> conversorEntidade = 
			conversores.criarConversorEntidade(Vendedor.class);
	
	AssemblerAdapter<VendedorOutput, Vendedor> conversorOutput = 
			conversores.criarConversorOutput(VendedorOutput.class);
	
	
	@Autowired
	VendedorRepository vendedorRepository;
	
	
	
	public VendedorOutput salvar(VendedorInput vendedorInput) {
		
		Vendedor vendedor = conversorEntidade.converterUnitario(vendedorInput);
		
		vendedor.setAtivo(true);
		
		Vendedor novoVendedor = vendedorRepository.save(vendedor);
		
		VendedorOutput vendedorOutput = conversorOutput.converterUnitario(novoVendedor);
		
		return vendedorOutput;
		
	}
	
	public List<VendedorOutput> listar(){
		
		List<Vendedor> vendedores = vendedorRepository.findAll();
		
		List<VendedorOutput> vendedoresOutput = conversorOutput.converterColecao(vendedores);
		
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
		
		VendedorOutput vendedorOutput = conversorOutput.converterUnitario(novoVendedor);
		
		return vendedorOutput;
			
	}
	
	public VendedorOutput atualizar(Long id,VendedorInput vendedorInput) throws NotFoundWithId {
		
		Optional<Vendedor> opVendedor = vendedorRepository.findById(id);
		
		if(opVendedor.isEmpty()) {
			
			throw new NotFoundWithId("Não encontrado vendedor com esse id");
		}
		
		Vendedor vendedor = opVendedor.get();
		
		
		
		Vendedor novoVendedor = conversorEntidade.converterUnitario(vendedorInput);
		
		BeanUtils.copyProperties(novoVendedor, vendedor,"id","ativo");
		
		VendedorOutput vendedorOutput = 
				conversorOutput.converterUnitario(vendedorRepository.save(vendedor));
		
		return vendedorOutput;
		
		
		
	}
}
