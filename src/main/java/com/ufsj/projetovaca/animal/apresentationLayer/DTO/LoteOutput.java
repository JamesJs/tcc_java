package com.ufsj.projetovaca.animal.apresentationLayer.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Saída de lote",description = "Modelo que demonstra a saída dos endpoints de lote.")
public class LoteOutput {
	@ApiModelProperty(value = "Id do lote.",example = "1",required = true)
	private long id;
	@ApiModelProperty(value = "Nome do lote.",example = "lote123",required = true)
	private String nome;
}
