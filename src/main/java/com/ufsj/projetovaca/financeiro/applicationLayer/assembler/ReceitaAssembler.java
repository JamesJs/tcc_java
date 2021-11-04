package com.ufsj.projetovaca.financeiro.applicationLayer.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.financeiro.applicationLayer.DTO.ReceitaInput;
import com.ufsj.projetovaca.financeiro.applicationLayer.DTO.ReceitaOutput;
import com.ufsj.projetovaca.financeiro.domainLayer.models.Receita;
@Service
public class ReceitaAssembler {
	
	
	public Receita converterEntidade(ReceitaInput compradorInput) {
		ModelMapper modelMapper = new ModelMapper();
		
		modelMapper.typeMap(ReceitaInput.class,Receita.class).addMappings(mp -> {   
			mp.skip(Receita::setId);
		});
		
		return modelMapper.map(compradorInput, Receita.class);
		
	}
	public ReceitaOutput converterOutput(Receita Receita) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		return modelMapper.map(Receita, ReceitaOutput.class);
		
	}
	
	public List<ReceitaOutput> converterColecaoOutput(List<Receita> origemList){
			
			List<ReceitaOutput> destinoList = origemList.stream().
					map(value -> this.converterOutput(value)).collect(Collectors.toList());
			return destinoList;
	}	
}

