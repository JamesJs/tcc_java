package com.ufsj.projetovaca.fazenda.apresentationLayer.DTO;

import com.ufsj.projetovaca.fazenda.domainLayer.models.embedded.Localizacao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Saída de fazenda",description = "Modelo que demonstra a saída dos endpoints de fazenda.")
public class FazendaOutput {
	@ApiModelProperty(value = "Id da fazenda.",example = "1",required = true)
	private Long id;
	@ApiModelProperty(value = "Nome da fazenda.",example = "Fazenda riacho",required = true)
	private String nome;
	@ApiModelProperty(value = "Tamanho da fazenda em hectáres.",example = "12",required = true)
	private int tamanho;
	@ApiModelProperty(value = "Quantidade de funcionários na fazenda.",example = "10",required = true)
	private int quantFuncionarios;
	@ApiModelProperty(value = "Campo que informa se a fazenda foi vendida.",example = "false",required = true)
	private Boolean isVendida;

	private Localizacao localizacao;
	
}
