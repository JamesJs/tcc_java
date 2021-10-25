package com.ufsj.projetovaca.fazenda.apresentationLayer.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
@ApiModel(value = "Contatos",description = "Submodelo de contatos que está presente em alguns modelos.")
public class Contato {
	@ApiModelProperty(value = "Número de celular para contato.",example = "+5531995898098",required = true)
	private String numeroCel;
	@ApiModelProperty(value = "Número fixo para contato.",example = "+553137216805",required = true)
	private String numeroFixo;
	@ApiModelProperty(value = "Email para contato.",example = "thiagobaldino5@gmail.com",required = true)
	private String email;
}
