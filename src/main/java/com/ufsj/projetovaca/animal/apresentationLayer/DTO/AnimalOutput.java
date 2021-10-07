package com.ufsj.projetovaca.animal.apresentationLayer.DTO;

import lombok.Data;

@Data
public class AnimalOutput {
	private long id;
	private boolean isLactacao;
	private boolean isPrenha;
	private String brinco;
	private long idLote;
	private boolean isVendido;
	private int idaAoCochoSal;
	private int idaAoCochoAgua;
	private int idaAoCochoRacao;
}
