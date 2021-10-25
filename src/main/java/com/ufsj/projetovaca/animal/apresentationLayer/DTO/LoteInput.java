package com.ufsj.projetovaca.animal.apresentationLayer.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Entrada de lote",description = "Modelo que demonstra a entrada dos endpoints de lote.")
public class LoteInput {
	@ApiModelProperty(value = "Nome do lote.",example = "lote123",required = true)
	private String nome;
}
