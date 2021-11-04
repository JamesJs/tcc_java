package com.ufsj.projetovaca.financeiro.applicationLayer.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.financeiro.applicationLayer.DTO.TipoReceitaInput;
import com.ufsj.projetovaca.financeiro.applicationLayer.DTO.TipoReceitaOutput;
import com.ufsj.projetovaca.financeiro.domainLayer.models.TipoReceita;
@Service
public class TipoReceitaAssembler {
	
	
	public TipoReceita converterEntidade(TipoReceitaInput compradorInput) {
		ModelMapper modelMapper = new ModelMapper();
		
		modelMapper.typeMap(TipoReceitaInput.class,TipoReceita.class).addMappings(mp -> {   
			mp.skip(TipoReceita::setId);
		});
		
		return modelMapper.map(compradorInput, TipoReceita.class);
		
	}
	public TipoReceitaOutput converterOutput(TipoReceita TipoReceita) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		return modelMapper.map(TipoReceita, TipoReceitaOutput.class);
		
	}
	
	public List<TipoReceitaOutput> converterColecaoOutput(List<TipoReceita> origemList){
			
			List<TipoReceitaOutput> destinoList = origemList.stream().
					map(value -> this.converterOutput(value)).collect(Collectors.toList());
			return destinoList;
	}	
}
