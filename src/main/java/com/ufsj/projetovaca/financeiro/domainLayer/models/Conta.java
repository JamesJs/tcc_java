package com.ufsj.projetovaca.financeiro.domainLayer.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ufsj.projetovaca.financeiro.domainLayer.models.embedded.TipoContaConta;

import lombok.Data;

@Entity
@Data
public class Conta {
	@Id
	@Column(name = "idConta")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private float valor;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date vencimento;
	@Column(nullable = true)
	private String descricao;
	@Column(nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataPagamento;
	@Embedded
	private TipoContaConta tipo;
	
}
