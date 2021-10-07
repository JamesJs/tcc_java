package com.ufsj.projetovaca.fazenda.apresentationLayer.assemblers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.fazenda.apresentationLayer.DTO.CochoInput;
import com.ufsj.projetovaca.fazenda.apresentationLayer.DTO.CochoOutput;
import com.ufsj.projetovaca.fazenda.domainLayer.models.Cocho;
@Service
public class CochoAssembler {
	
	
	public Cocho converterEntidade(CochoInput cochoInput) {
		ModelMapper modelMapper = new ModelMapper();
		
		modelMapper.typeMap(CochoInput.class,Cocho.class).addMappings(mp -> {   
			mp.skip(Cocho::setId);
		});
		
		return modelMapper.map(cochoInput, Cocho.class);
		
	}
	public CochoOutput converterOutput(Cocho cocho) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		return modelMapper.map(cocho, CochoOutput.class);
		
	}
	
	public List<CochoOutput> converterColecaoOutput(List<Cocho> origemList){
			
			List<CochoOutput> destinoList = origemList.stream().
					map(value -> this.converterOutput(value)).collect(Collectors.toList());
			return destinoList;
	}	
}
