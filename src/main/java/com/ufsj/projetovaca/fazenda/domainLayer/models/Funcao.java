package com.ufsj.projetovaca.fazenda.domainLayer.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Funcao {
	@Id
	@Column(name="idFuncao")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String nome;
	@Column
	private float salario;
	@Column
	private boolean isAtivado;
	
	public void ativarFuncao() {
		
		setAtivado(true);
		
	}
}
