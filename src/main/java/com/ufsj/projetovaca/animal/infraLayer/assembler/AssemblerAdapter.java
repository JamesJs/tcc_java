package com.ufsj.projetovaca.animal.infraLayer.assembler;

import java.util.List;

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

