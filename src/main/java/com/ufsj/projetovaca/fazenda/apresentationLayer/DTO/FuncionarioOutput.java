package com.ufsj.projetovaca.fazenda.apresentationLayer.DTO;

import com.ufsj.projetovaca.fazenda.domainLayer.models.embedded.FuncaoFuncionario;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
@ApiModel(value = "Saída de funcionário.",description = "Modelo que demonstra a saída dos endpoints de funcionário.")
public class FuncionarioOutput {
	@ApiModelProperty(value = "Id do funcionário.",example = "1",required = true)
	private long id;
	@ApiModelProperty(value = "Nome do funcionário.",example = "Thiago Baldino Moreira",required = true)
	private String nome;
	@ApiModelProperty(value = "Campo que indica se o funcionário está demitido.",example = "true",required = true)
	private boolean isDemitido;
	private Long idFuncao;
	private Contato contato;
}
