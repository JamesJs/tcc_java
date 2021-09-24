package com.ufsj.projetovaca.fazenda.apresentationLayer.DTO;

import com.ufsj.projetovaca.fazenda.domainLayer.models.embedded.FuncaoFuncionario;

import lombok.Data;
@Data
public class FuncionarioOutput {
	private long id;
	private String nome;
	private boolean isDemitido;
	private FuncaoFuncionario funcaoFuncionario;
	private Contato contato;
}
