package com.ufsj.projetovaca.financeiro.applicationLayer.DTO;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ReceitaOutput {
	private Long id;
	private Long idTipo;
	private Date dataRecebimento;
	private float valor;
}
