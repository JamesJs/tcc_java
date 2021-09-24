package com.ufsj.projetovaca.fazenda.apresentationLayer.DTO;


import com.ufsj.projetovaca.fazenda.domainLayer.models.embedded.Localizacao;

import lombok.Data;

@Data
public class FazendaInput {

	private String nome;

	private int tamanho;

	private int quantFuncionarios;

	private Localizacao localizacao;
	
	
}
