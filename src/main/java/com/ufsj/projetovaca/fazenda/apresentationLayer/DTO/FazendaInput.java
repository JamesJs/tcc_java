package com.ufsj.projetovaca.fazenda.apresentationLayer.DTO;


import com.ufsj.projetovaca.fazenda.domainLayer.models.embedded.Localizacao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Entrada de fazenda",description = "Modelo que demonstra a entrada dos endpoints de fazenda.")
public class FazendaInput {
	@ApiModelProperty(value = "Nome da fazenda.",example = "Fazenda riacho",required = true)
	private String nome;
	@ApiModelProperty(value = "Tamanho da fazenda em hectáres.",example = "12",required = true)
	private int tamanho;
	@ApiModelProperty(value = "Quantidade de funcionários na fazenda.",example = "10",required = true)
	private int quantFuncionarios;

	private Localizacao localizacao;
	
	
}
