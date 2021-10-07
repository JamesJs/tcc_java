package com.ufsj.projetovaca.fazenda.apresentationLayer.assemblers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.fazenda.apresentationLayer.DTO.MateriaPrimaInput;
import com.ufsj.projetovaca.fazenda.apresentationLayer.DTO.MateriaPrimaOutput;
import com.ufsj.projetovaca.fazenda.domainLayer.models.MateriaPrima;
@Service
public class MateriaPrimaAssembler {
	
	
	public MateriaPrima converterEntidade(MateriaPrimaInput materiaPrimaOInput) {
		ModelMapper modelMapper = new ModelMapper();
		
		modelMapper.typeMap(MateriaPrimaInput.class,MateriaPrima.class).addMappings(mp -> {   
			mp.skip(MateriaPrima::setId);
		});
		
		return modelMapper.map(materiaPrimaOInput, MateriaPrima.class);
		
	}
	public MateriaPrimaOutput converterOutput(MateriaPrima materiaPrima) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		return modelMapper.map(materiaPrima, MateriaPrimaOutput.class);
		
	}
	
	public List<MateriaPrimaOutput> converterColecaoOutput(List<MateriaPrima> origemList){
			
			List<MateriaPrimaOutput> destinoList = origemList.stream().
					map(value -> this.converterOutput(value)).collect(Collectors.toList());
			return destinoList;
	}	
}
