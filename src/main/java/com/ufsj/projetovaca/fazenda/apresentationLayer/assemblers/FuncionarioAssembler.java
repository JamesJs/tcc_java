package com.ufsj.projetovaca.fazenda.apresentationLayer.assemblers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.fazenda.apresentationLayer.DTO.FuncionarioInput;
import com.ufsj.projetovaca.fazenda.apresentationLayer.DTO.FuncionarioOutput;
import com.ufsj.projetovaca.fazenda.domainLayer.models.Funcionario;
@Service
public class FuncionarioAssembler {
	
	
	public Funcionario converterEntidade(FuncionarioInput funcionarioInput) {
		ModelMapper modelMapper = new ModelMapper();
		
		modelMapper.typeMap(FuncionarioInput.class,Funcionario.class).addMappings(mp -> {   
			mp.skip(Funcionario::setId);
		});
		
		return modelMapper.map(funcionarioInput, Funcionario.class);
		
	}
	public FuncionarioOutput converterOutput(Funcionario funcionario) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		return modelMapper.map(funcionario, FuncionarioOutput.class);
		
	}
	
	public List<FuncionarioOutput> converterColecaoOutput(List<Funcionario> origemList){
			
			List<FuncionarioOutput> destinoList = origemList.stream().
					map(value -> this.converterOutput(value)).collect(Collectors.toList());
			return destinoList;
	}	
}
