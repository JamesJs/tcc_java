package com.ufsj.projetovaca.comercial.apresentationLayer.assemblers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.comercial.apresentationLayer.DTO.VendedorInput;
import com.ufsj.projetovaca.comercial.apresentationLayer.DTO.VendedorOutput;
import com.ufsj.projetovaca.comercial.domainLayer.models.Vendedor;
@Service
public class VendedorAssembler {
	
	
	public Vendedor converterEntidade(VendedorInput vendedorInput) {
		ModelMapper modelMapper = new ModelMapper();
		
		modelMapper.typeMap(VendedorInput.class,Vendedor.class).addMappings(mp -> {   
			mp.skip(Vendedor::setId);
		});
		
		return modelMapper.map(vendedorInput, Vendedor.class);
		
	}
	public VendedorOutput converterOutput(Vendedor vendedor) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		return modelMapper.map(vendedor, VendedorOutput.class);
		
	}
	
	public List<VendedorOutput> converterColecaoOutput(List<Vendedor> origemList){
			
			List<VendedorOutput> destinoList = origemList.stream().
					map(value -> this.converterOutput(value)).collect(Collectors.toList());
			return destinoList;
	}	
}
