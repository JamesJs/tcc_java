package com.ufsj.projetovaca.comercial.apresentationLayer.DTO;

import java.util.Date;

import lombok.Data;
@Data
public class CompraGadoOutput {

	private long id;
	
	private float valor;
	
	private Date data;

	private float pesoTotal;

	private long idComprador;
}
