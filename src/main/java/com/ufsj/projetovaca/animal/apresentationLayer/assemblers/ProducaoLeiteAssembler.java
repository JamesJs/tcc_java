package com.ufsj.projetovaca.animal.apresentationLayer.assemblers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.animal.apresentationLayer.DTO.ProducaoLeiteInput;
import com.ufsj.projetovaca.animal.apresentationLayer.DTO.ProducaoLeiteOutput;
import com.ufsj.projetovaca.animal.domainLayer.models.ProducaoLeite;
@Service
public class ProducaoLeiteAssembler {
	
	
	public ProducaoLeite converterEntidade(ProducaoLeiteInput producaoLeiteInput) {
		ModelMapper modelMapper = new ModelMapper();
		
		modelMapper.typeMap(ProducaoLeiteInput.class,ProducaoLeite.class).addMappings(mp -> {   
			mp.skip(ProducaoLeite::setId);
		});
		
		return modelMapper.map(producaoLeiteInput, ProducaoLeite.class);
		
	}
	public ProducaoLeiteOutput converterOutput(ProducaoLeite producaoLeite) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		return modelMapper.map(producaoLeite, ProducaoLeiteOutput.class);
		
	}
	
	public List<ProducaoLeiteOutput> converterColecaoOutput(List<ProducaoLeite> origemList){
			
			List<ProducaoLeiteOutput> destinoList = origemList.stream().map(value -> this.converterOutput(value)).collect(Collectors.toList());
			System.out.println(destinoList);
			return destinoList;
	}
	
}
	