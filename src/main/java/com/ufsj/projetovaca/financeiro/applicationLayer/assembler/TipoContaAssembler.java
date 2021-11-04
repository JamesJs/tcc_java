package com.ufsj.projetovaca.financeiro.applicationLayer.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.financeiro.applicationLayer.DTO.TipoContaInput;
import com.ufsj.projetovaca.financeiro.applicationLayer.DTO.TipoContaOutput;
import com.ufsj.projetovaca.financeiro.domainLayer.models.Conta;
import com.ufsj.projetovaca.financeiro.domainLayer.models.TipoConta;
@Service
public class TipoContaAssembler {
	
	
	public TipoConta converterEntidade(TipoContaInput compradorInput) {
		ModelMapper modelMapper = new ModelMapper();
		
		modelMapper.typeMap(TipoContaInput.class,Conta.class).addMappings(mp -> {   
			mp.skip(Conta::setId);
		});
		
		return modelMapper.map(compradorInput, TipoConta.class);
		
	}
	public TipoContaOutput converterOutput(TipoConta conta) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		return modelMapper.map(conta, TipoContaOutput.class);
		
	}
	
	public List<TipoContaOutput> converterColecaoOutput(List<TipoConta> origemList){
			
			List<TipoContaOutput> destinoList = origemList.stream().
					map(value -> this.converterOutput(value)).collect(Collectors.toList());
			return destinoList;
	}	
}
