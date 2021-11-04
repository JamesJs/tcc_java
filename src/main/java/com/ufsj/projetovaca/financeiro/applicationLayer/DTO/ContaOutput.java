package com.ufsj.projetovaca.financeiro.applicationLayer.DTO;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ContaOutput {
	private long id;
	private float valor;
	private String descricao;
	private Date dataPagamento;
	private Long idTipo;
	private Date vencimento;
}
