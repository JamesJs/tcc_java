package com.ufsj.projetovaca.fazenda.apresentationLayer.DTO;
import com.ufsj.projetovaca.fazenda.domainLayer.models.embedded.Contato;
import com.ufsj.projetovaca.fazenda.domainLayer.models.embedded.FuncaoFuncionario;

import lombok.Data;
@Data
public class FuncionarioInput {
	private String nome;
	private FuncaoFuncionario funcaoFuncionario;
	private Contato contato;
}
