package com.ufsj.projetovaca.fazenda.apresentationLayer.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Entrada de matéria prima",description = "Modelo que demonstra a entrada dos endpoints de matéria prima.")
public class MateriaPrimaInput {
	@ApiModelProperty(value = "Tipo de matéria prima.",example = "MADEIRA",required = true)
	private String tipo;
}
