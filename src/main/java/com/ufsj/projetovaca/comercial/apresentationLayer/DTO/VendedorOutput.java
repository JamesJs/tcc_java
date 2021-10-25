package com.ufsj.projetovaca.comercial.apresentationLayer.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Saída de vendedor.",description = "Modelo que demonstra a saída dos endpoints de vendedor.")
public class VendedorOutput {
	@ApiModelProperty(value = "Id do vendedor.",example = "1",required = true)
	private long id;
	@ApiModelProperty(value = "Nome do vendedor.",example = "Fazenda riacho",required = true)
	private String nome;
	
	private Contato contato;
	@ApiModelProperty(value = "Campo que informa se o vendedor ainda é ativo.",example = "false",required = true)
	private boolean isAtivo;
}
