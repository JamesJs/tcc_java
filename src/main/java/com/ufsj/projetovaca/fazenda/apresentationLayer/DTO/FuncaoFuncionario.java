package com.ufsj.projetovaca.fazenda.apresentationLayer.DTO;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "Função dos funcionários",description = "Submodelo usado pelos funcionários para indicar o id da função.")
public class FuncaoFuncionario {
	private long idFuncao;
}
