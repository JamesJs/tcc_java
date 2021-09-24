package com.ufsj.projetovaca.fazenda.apresentationLayer.utils;

import java.util.List;

import com.ufsj.projetovaca.fazenda.infraLayer.assembler.IAssembler;

public class AssemblerAdapter<Destino,Origem> {
	
    IAssembler<Destino, Origem> assembler;
	
	public AssemblerAdapter(IAssembler<Destino, Origem> assembler) {
		this.assembler = assembler;
	}
	public Destino converterUnitario(Origem origem) {
		
		return assembler.toModel(origem);
	}
	public List<Destino> converterColecao(List<Origem> origemList){
		return assembler.ToCollectionModel(origemList);
	}
}
