package com.ufsj.projetovaca.comercial.apresentationLayer.assemblers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.comercial.apresentationLayer.DTO.CompradorInput;
import com.ufsj.projetovaca.comercial.apresentationLayer.DTO.CompradorOutput;
import com.ufsj.projetovaca.comercial.domainLayer.models.Comprador;
@Service
public class CompradorAssembler {
	
	
	public Comprador converterEntidade(CompradorInput compradorInput) {
		ModelMapper modelMapper = new ModelMapper();
		
		modelMapper.typeMap(CompradorInput.class,Comprador.class).addMappings(mp -> {   
			mp.skip(Comprador::setId);
		});
		
		return modelMapper.map(compradorInput, Comprador.class);
		
	}
	public CompradorOutput converterOutput(Comprador comprador) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		return modelMapper.map(comprador, CompradorOutput.class);
		
	}
	
	public List<CompradorOutput> converterColecaoOutput(List<Comprador> origemList){
			
			List<CompradorOutput> destinoList = origemList.stream().
					map(value -> this.converterOutput(value)).collect(Collectors.toList());
			return destinoList;
	}	
}
