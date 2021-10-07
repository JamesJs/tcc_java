package com.ufsj.projetovaca.animal.apresentationLayer.assemblers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.animal.apresentationLayer.DTO.LoteInput;
import com.ufsj.projetovaca.animal.apresentationLayer.DTO.LoteOutput;
import com.ufsj.projetovaca.animal.domainLayer.models.Lote;
@Service
public class LoteAssembler {
	
	
	public Lote converterEntidade(LoteInput loteInput) {
		ModelMapper modelMapper = new ModelMapper();
		
		modelMapper.typeMap(LoteInput.class,Lote.class).addMappings(mp -> {   
			mp.skip(Lote::setId);
		});
		
		return modelMapper.map(loteInput, Lote.class);
		
	}
	public LoteOutput converterOutput(Lote lote) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		return modelMapper.map(lote, LoteOutput.class);
		
	}
	
	public List<LoteOutput> converterColecaoOutput(List<Lote> origemList){
			
			List<LoteOutput> destinoList = origemList.stream().
					map(value -> this.converterOutput(value)).collect(Collectors.toList());
			return destinoList;
	}
	
}
