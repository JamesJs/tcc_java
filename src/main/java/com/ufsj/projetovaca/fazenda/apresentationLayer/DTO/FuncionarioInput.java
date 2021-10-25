package com.ufsj.projetovaca.fazenda.apresentationLayer.DTO;
import com.ufsj.projetovaca.fazenda.domainLayer.models.embedded.Contato;
import com.ufsj.projetovaca.fazenda.domainLayer.models.embedded.FuncaoFuncionario;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
@ApiModel(value = "Entrada de funcionário.",description = "Modelo que demonstra a entrada dos endpoints de funcionário.")
public class FuncionarioInput {
	@ApiModelProperty(value = "Nome do funcionário.",example = "Thiago Baldino Moreira",required = true)
	private String nome;
	@ApiModelProperty(value = "Id da função do funcionário.",required = true)
	private FuncaoFuncionario funcaoFuncionario;
	private Contato contato;
}
