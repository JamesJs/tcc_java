package com.ufsj.projetovaca.comercial.apresentationLayer.DTO;

import lombok.Data;

@Data
public class CompradorInput {
	
	private String nome;
	
	private Contato contato;
	
	private String tipoComprador;
	
}
