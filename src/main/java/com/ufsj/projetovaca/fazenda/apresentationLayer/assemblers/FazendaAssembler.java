package com.ufsj.projetovaca.fazenda.apresentationLayer.assemblers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.fazenda.apresentationLayer.DTO.FazendaInput;
import com.ufsj.projetovaca.fazenda.apresentationLayer.DTO.FazendaOutput;
import com.ufsj.projetovaca.fazenda.domainLayer.models.Fazenda;
@Service
public class FazendaAssembler {
	
	
	public Fazenda converterEntidade(FazendaInput fazendaInput) {
		ModelMapper modelMapper = new ModelMapper();
		
		modelMapper.typeMap(FazendaInput.class,Fazenda.class).addMappings(mp -> {   
			mp.skip(Fazenda::setId);
		});
		
		return modelMapper.map(fazendaInput, Fazenda.class);
		
	}
	public FazendaOutput converterOutput(Fazenda fazenda) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		return modelMapper.map(fazenda, FazendaOutput.class);
		
	}
	
	public List<FazendaOutput> converterColecaoOutput(List<Fazenda> origemList){
			
			List<FazendaOutput> destinoList = origemList.stream().
					map(value -> this.converterOutput(value)).collect(Collectors.toList());
			return destinoList;
	}	
}
