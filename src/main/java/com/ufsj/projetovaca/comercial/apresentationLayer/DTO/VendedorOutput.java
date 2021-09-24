package com.ufsj.projetovaca.comercial.apresentationLayer.DTO;

import lombok.Data;

@Data
public class VendedorOutput {
	
	private long id;
	
	private String nome;
	
	private Contato contato;
	
	private boolean isAtivo;
}
