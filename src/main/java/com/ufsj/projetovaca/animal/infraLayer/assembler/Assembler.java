package com.ufsj.projetovaca.animal.infraLayer.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
public class Assembler<Destino,Origem> implements IAssembler<Destino,Origem> {
	Class<Destino> destino;
	Class<Origem> origem;
	ModelMapper modelMapper = new ModelMapper();
	
	
	public Assembler(Class<Destino> destino) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		this.destino = destino;
		
	}
	@Override
	public Destino toModel(Origem origem) {
		return modelMapper.map(origem, this.destino);
	}
	@Override
	public List<Destino> ToCollectionModel(List<Origem> origemList){
		
		List<Destino> destinoList = origemList.stream().map(value -> this.toModel(value)).collect(Collectors.toList());
		System.out.println(destinoList);
		return destinoList;
	}
}