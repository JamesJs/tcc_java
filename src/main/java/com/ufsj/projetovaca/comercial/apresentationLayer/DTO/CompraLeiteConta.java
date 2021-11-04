package com.ufsj.projetovaca.comercial.apresentationLayer.DTO;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
public class CompraLeiteConta implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idCompra;
	@ApiModelProperty(value = "Valor da compra realizada.",example = "100.00",required = true)
	private float valor;
	@ApiModelProperty(value = "Data em que a compra foi realizada com o formato YYYY/mm/ddTHH:mm.",example = "2021/12/23T20:44",required = true)
	private Date data;
}
