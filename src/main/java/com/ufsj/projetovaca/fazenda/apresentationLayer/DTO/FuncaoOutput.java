package com.ufsj.projetovaca.fazenda.apresentationLayer.DTO;

import lombok.Data;

@Data
public class FuncaoOutput {
	private Long id;
	private String nome;
	private float salario;
	private boolean isAtivado;
}
