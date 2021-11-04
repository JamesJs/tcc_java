package com.ufsj.projetovaca.financeiro.domainLayer.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



import lombok.Data;
@Entity
@Data
public class TipoConta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idTipoConta")
	private long id;
	@Column
	private String tipo;
}
