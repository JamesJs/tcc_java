package com.ufsj.projetovaca.comercial.apresentationLayer.DTO;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@ApiModel(value = "Saída de venda de gado",description = "Modelo que demonstra a saída dos endpoints de venda de gado.")
@Data
public class VendaGadoOutput {
	@ApiModelProperty(value = "Id da venda realizada.",example = "1",required = true)
	private long id;
	@ApiModelProperty(value = "Valor da venda realizada.",example = "100.00",required = true)
	private float valor;
	@ApiModelProperty(value = "Data em que a venda foi realizada com o formato YYYY/mm/ddTHH:mm.",example = "2021/12/23T20:44",required = true)
	@JsonFormat(pattern="yyyy/MM/dd'T'HH:mm:ss")
	private Date data;
	@ApiModelProperty(value = "Quantidade de animais adquiridos.",example = "5",required = true)
	private int quantAnimais;
	@ApiModelProperty(value = "Id do vendedor que realizou a venda e que está cadastrado na aplicação.",example = "1",required = true)
	private long idVendedor;
	private boolean isCancelado;
}
