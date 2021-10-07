package com.ufsj.projetovaca.animal.infraLayer.assembler;

import java.util.List;

public interface IAssembler<Destino,Origem>{;
	public Destino toModel(Origem origem);
	public List<Destino> ToCollectionModel(List<Origem> origemList);
}
