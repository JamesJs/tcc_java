package com.ufsj.projetovaca.fazenda.apresentationLayer.assemblers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.fazenda.apresentationLayer.DTO.FuncaoInput;
import com.ufsj.projetovaca.fazenda.apresentationLayer.DTO.FuncaoOutput;
import com.ufsj.projetovaca.fazenda.domainLayer.models.Funcao;
@Service
public class FuncaoAssembler {
	
	
	public Funcao converterEntidade(FuncaoInput funcaoInput) {
		ModelMapper modelMapper = new ModelMapper();
		
		modelMapper.typeMap(FuncaoInput.class,Funcao.class).addMappings(mp -> {   
			mp.skip(Funcao::setId);
		});
		
		return modelMapper.map(funcaoInput, Funcao.class);
		
	}
	public FuncaoOutput converterOutput(Funcao funcao) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		return modelMapper.map(funcao, FuncaoOutput.class);
		
	}
	
	public List<FuncaoOutput> converterColecaoOutput(List<Funcao> origemList){
			
			List<FuncaoOutput> destinoList = origemList.stream().
					map(value -> this.converterOutput(value)).collect(Collectors.toList());
			return destinoList;
	}	
}
