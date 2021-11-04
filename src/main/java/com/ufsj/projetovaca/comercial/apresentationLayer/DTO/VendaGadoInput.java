package com.ufsj.projetovaca.comercial.apresentationLayer.DTO;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
@ApiModel(value = "Entrada de venda de gado",description = "Modelo que demonstra a entrada dos endpoints de venda de gado.")
public class VendaGadoInput {
	
	@ApiModelProperty(value = "Valor da venda realizada.",example = "100.00",required = true)
	private float valor;
	@ApiModelProperty(value = "Data em que a venda foi realizada com o formato YYYY/mm/ddTHH:mm.",example = "2021/12/23T20:44",required = true)
	@JsonFormat(pattern="yyyy/MM/dd'T'HH:mm:ss")
	private Date data;
	@ApiModelProperty(value = "Quantidade de animais adquiridos.",example = "5",required = true)
	private int quantAnimais;
	@ApiModelProperty(value = "Id do vendedor que realizou a venda e que está cadastrado na aplicação.",example = "1",required = true)
	private long idVendedor;
}
