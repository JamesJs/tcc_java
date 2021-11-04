package com.ufsj.projetovaca.financeiro.applicationLayer.DTO;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
@Data
public class ContaInput {
	private float valor;
	private Date vencimento;
	private String descricao;
	private Date dataPagamento;
	private Long idTipo;
}
