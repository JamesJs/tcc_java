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

import com.ufsj.projetovaca.financeiro.domainLayer.models.embedded.TipoReceitaReceita;

import lombok.Data;
@Entity
@Data
public class Receita {
	@Id
	@Column(name = "idConta")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private float valor;
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataRecebimento;
	@Embedded
	private TipoReceitaReceita tipo = new TipoReceitaReceita();
	
	public void definirTipo(int id) {
		tipo.setIdTipoReceita(id);
	}
}
