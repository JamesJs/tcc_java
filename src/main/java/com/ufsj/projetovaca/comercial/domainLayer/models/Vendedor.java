package com.ufsj.projetovaca.comercial.domainLayer.models;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ufsj.projetovaca.comercial.domainLayer.models.embedded.Contato;

import lombok.Data;
@Data
@Entity
public class Vendedor {
	@Id
	@Column(name = "idVendedor")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String nome;
	@Column
	private boolean isAtivo;
	@Embedded
	private Contato contato;
}
