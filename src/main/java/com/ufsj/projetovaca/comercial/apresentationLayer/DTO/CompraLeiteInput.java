package com.ufsj.projetovaca.comercial.apresentationLayer.DTO;

import java.util.Date;

import lombok.Data;
@Data
public class CompraLeiteInput {

	private float valor;
	
	private Date data;
	
	private float litros;
	
	private long idComprador;
}
