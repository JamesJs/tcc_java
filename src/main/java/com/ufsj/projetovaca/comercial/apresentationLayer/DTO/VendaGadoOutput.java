package com.ufsj.projetovaca.comercial.apresentationLayer.DTO;

import java.util.Date;

import lombok.Data;

@Data
public class VendaGadoOutput {
	
	private long id;
	
	private float valor;
	
	private Date data;
	
	private int quantAnimais;
	
	private long idVendedor;
}
