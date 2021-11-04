package com.ufsj.projetovaca.comercial.apresentationLayer.assemblers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.comercial.apresentationLayer.DTO.CompraGadoInput;
import com.ufsj.projetovaca.comercial.apresentationLayer.DTO.CompraGadoOutput;
import com.ufsj.projetovaca.comercial.domainLayer.models.CompraGado;
import com.ufsj.projetovaca.comercial.domainLayer.models.Comprador;
import com.ufsj.projetovaca.comercial.domainLayer.models.embedded.CompraGadoComprador;
@Service
public class CompraGadoAssembler {
	
	
	public CompraGado converterEntidade(CompraGadoInput compraGadoInput) {
		
		CompraGado compraGado = new CompraGado();
		CompraGadoComprador comprador = new CompraGadoComprador();
		comprador.setIdComprador(compraGadoInput.getIdComprador());
		compraGado.setComprador(comprador);
		compraGado.setData(compraGadoInput.getData());
		compraGado.setValor(compraGadoInput.getValor());
		
		return compraGado;
		
	}
	
	
	public CompraGadoOutput converterOutput(CompraGado compraGado) {
		
		CompraGadoOutput compraGadoOutput = new CompraGadoOutput();
		
		compraGadoOutput.setData(compraGado.getData());
		compraGadoOutput.setId(compraGado.getId());
		compraGadoOutput.setIdComprador(compraGado.getComprador().getIdComprador());
		compraGadoOutput.setValor(compraGado.getValor());
		compraGadoOutput.setIdsAnimais(compraGado.obtemIdsAnimais());
		
		return compraGadoOutput;
		
		
		
	}
	
	public List<CompraGadoOutput> converterColecaoOutput(List<CompraGado> origemList){
			
			List<CompraGadoOutput> destinoList = origemList.stream().
					map(value -> this.converterOutput(value)).collect(Collectors.toList());
			return destinoList;
	}	
}
