package com.ufsj.projetovaca.authentication.domainLayer.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
@Data
@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idUsuario")
	long id;
	@Column
	String nome;
	@Column
	boolean isAdmin;
	@Column
	String senha;
	@Column
	String login;
	@Column
	boolean isDesativado;
}
