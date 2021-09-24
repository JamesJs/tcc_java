package com.ufsj.projetovaca.fazenda.apresentationLayer.DTO;

import lombok.Data;

@Data
public class MateriaPrimaOutput {
	private long id;
	private String tipo;
	private Boolean isNaoUtilizado;
}
