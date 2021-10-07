package com.ufsj.projetovaca.animal.apresentationLayer.DTO;

import lombok.Data;

@Data
public class AnimalProducaoLeiteOutput {
	private long id;
	private boolean isLactacao;
	private String idBrinco;
	private LoteAnimalOutput lote;
	
}
