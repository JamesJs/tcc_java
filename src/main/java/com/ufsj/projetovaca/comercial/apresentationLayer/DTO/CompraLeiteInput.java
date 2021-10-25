package com.ufsj.projetovaca.comercial.apresentationLayer.DTO;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
@ApiModel(value = "Entrada de compra de leite",description = "Modelo que demonstra a entrada dos endpoints de compra de leite.")
public class CompraLeiteInput {
	@ApiModelProperty(value = "Valor da compra realizada.",example = "100.00",required = true)
	private float valor;
	@ApiModelProperty(value = "Data em que a compra foi realizada com o formato YYYY/mm/ddTHH:mm.",example = "2021/12/23T20:44",required = true)
	private Date data;
	@ApiModelProperty(value = "Litro total vendido.",example = "10.0",required = true)
	private float litros;
	@ApiModelProperty(value = "Id do comprador que irá realizar a compra e que está cadastrado na aplicação.",example = "1",required = true)
	private long idComprador;
}
