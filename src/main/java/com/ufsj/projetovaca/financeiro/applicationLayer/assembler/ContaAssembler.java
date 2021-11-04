package com.ufsj.projetovaca.financeiro.applicationLayer.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.financeiro.applicationLayer.DTO.ContaInput;
import com.ufsj.projetovaca.financeiro.applicationLayer.DTO.ContaOutput;
import com.ufsj.projetovaca.financeiro.domainLayer.models.Conta;
@Service
public class ContaAssembler {
	
	
	public Conta converterEntidade(ContaInput compradorInput) {
		ModelMapper modelMapper = new ModelMapper();
		
		modelMapper.typeMap(ContaInput.class,Conta.class).addMappings(mp -> {   
			mp.skip(Conta::setId);
		});
		
		return modelMapper.map(compradorInput, Conta.class);
		
	}
	public ContaOutput converterOutput(Conta conta) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		return modelMapper.map(conta, ContaOutput.class);
		
	}
	
	public List<ContaOutput> converterColecaoOutput(List<Conta> origemList){
			
			List<ContaOutput> destinoList = origemList.stream().
					map(value -> this.converterOutput(value)).collect(Collectors.toList());
			return destinoList;
	}	
}
