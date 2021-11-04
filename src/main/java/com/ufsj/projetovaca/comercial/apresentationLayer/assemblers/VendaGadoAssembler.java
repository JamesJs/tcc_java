package com.ufsj.projetovaca.comercial.apresentationLayer.assemblers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.comercial.apresentationLayer.DTO.VendaGadoInput;
import com.ufsj.projetovaca.comercial.apresentationLayer.DTO.VendaGadoOutput;
import com.ufsj.projetovaca.comercial.domainLayer.models.VendaGado;
@Service
public class VendaGadoAssembler {
	
	
	public VendaGado converterEntidade(VendaGadoInput VendaGadoInput) {
		ModelMapper modelMapper = new ModelMapper();
		
		modelMapper.typeMap(VendaGadoInput.class,VendaGado.class).addMappings(mp -> {   
			mp.skip(VendaGado::setId);
		});
		
		return modelMapper.map(VendaGadoInput, VendaGado.class);
		
	}
	public VendaGadoOutput converterOutput(VendaGado VendaGado) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		return modelMapper.map(VendaGado, VendaGadoOutput.class);
		
	}
	
	public List<VendaGadoOutput> converterColecaoOutput(List<VendaGado> origemList){
			
			List<VendaGadoOutput> destinoList = origemList.stream().
					map(value -> this.converterOutput(value)).collect(Collectors.toList());
			return destinoList;
	}	
}
