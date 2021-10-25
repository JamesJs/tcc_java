package com.ufsj.projetovaca.comercial.apresentationLayer.DTO;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
@ApiModel(value = "Entrada de comprador",description = "Modelo que demonstra a entrada dos endpoints de comprador.")
public class CompradorInput {
	@ApiModelProperty(value = "Nome do comprador.",example = "Supermercado123",required = true)
	private String nome;
	@ApiModelProperty(required = true)
	private Contato contato;
	@ApiModelProperty(value = "Tipo de compra que o comprador realiza podendo ser de leite ou de gado.",example = "LEITE",required = true)
	private String tipoComprador;
	
}
