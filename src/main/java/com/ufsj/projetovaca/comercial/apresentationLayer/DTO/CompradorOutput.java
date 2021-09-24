package com.ufsj.projetovaca.comercial.apresentationLayer.DTO;

import lombok.Data;

@Data
public class CompradorOutput {
	
	private long id;
	
	private String nome;
	
	private Contato contato;
	
	private boolean isAtivo;
	
	private String tipoComprador;
}
