package com.ufsj.projetovaca.comercial.apresentationLayer.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Saída de comprador",description = "Modelo que demonstra a saída dos endpoints de comprador.")
public class CompradorOutput {
	@ApiModelProperty(value = "Id do comprador.",example = "1",required = true)
	private long id;
	@ApiModelProperty(value = "Nome do comprador.",example = "Supermercado123",required = true)
	private String nome;
	
	private Contato contato;
	@ApiModelProperty(value = "Indica se o comprador está ativo na aplicação.",example = "LEITE",required = true)
	private boolean isAtivo;
	@ApiModelProperty(value = "Tipo de compra que o comprador realiza podendo ser de leite ou de gado.",example = "LEITE",required = true)
	private String tipoComprador;
}
