package com.ufsj.projetovaca.comercial.apresentationLayer.assemblers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.comercial.apresentationLayer.DTO.CompraLeiteConta;
import com.ufsj.projetovaca.comercial.apresentationLayer.DTO.CompraLeiteInput;
import com.ufsj.projetovaca.comercial.apresentationLayer.DTO.CompraLeiteOutput;
import com.ufsj.projetovaca.comercial.domainLayer.models.CompraLeite;
@Service
public class CompraLeiteAssembler {
	
	
	public CompraLeite converterEntidade(CompraLeiteInput compraLeiteInput) {
		ModelMapper modelMapper = new ModelMapper();
		
		modelMapper.typeMap(CompraLeiteInput.class,CompraLeite.class).addMappings(mp -> {   
			mp.skip(CompraLeite::setId);
		});
		
		return modelMapper.map(compraLeiteInput, CompraLeite.class);
		
	}
	
	public CompraLeiteConta criarNovaContaLeite(CompraLeite compraLeite) {
		CompraLeiteConta compraLeiteConta = new CompraLeiteConta();
		compraLeiteConta.setData(compraLeite.getData());
		compraLeiteConta.setValor(compraLeite.getValor());
		compraLeiteConta.setIdCompra(compraLeite.getId());
		return compraLeiteConta;
	}
	
	
	public CompraLeiteOutput converterOutput(CompraLeite compraLeite) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		return modelMapper.map(compraLeite, CompraLeiteOutput.class);
		
	}
	
	public List<CompraLeiteOutput> converterColecaoOutput(List<CompraLeite> origemList){
			
			List<CompraLeiteOutput> destinoList = origemList.stream().
					map(value -> this.converterOutput(value)).collect(Collectors.toList());
			return destinoList;
	}	
}
