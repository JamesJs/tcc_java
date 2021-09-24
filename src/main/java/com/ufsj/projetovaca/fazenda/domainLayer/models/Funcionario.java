package com.ufsj.projetovaca.fazenda.domainLayer.models;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.ufsj.projetovaca.fazenda.domainLayer.models.embedded.Contato;
import com.ufsj.projetovaca.fazenda.domainLayer.models.embedded.FuncaoFuncionario;

import lombok.Data;

@Data
@Entity
public class Funcionario {
	@Id
	@Column(name="idFuncionario")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String nome;
	@Column
	private Boolean isDemitido;
	@Embedded
	private FuncaoFuncionario funcaoFuncionario;
	@Embedded
	private Contato contato;
	
}
