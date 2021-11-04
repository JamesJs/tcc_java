package com.ufsj.projetovaca.fazenda.apresentationLayer.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Saída de matéria prima",description = "Modelo que demonstra a saída dos endpoints de matéria prima.")
public class MateriaPrimaOutput {
	@ApiModelProperty(value = "Id da matéria prima.",example = "1",required = true)
	private long id;
	@ApiModelProperty(value = "Tipo de matéria prima.",example = "MADEIRA",required = true)
	private String tipo;
	@ApiModelProperty(value = "Informa se a matéria prima ainda pode ser selecionada.",example = "true",required = true)
	private boolean isNaoUtilizado;
}
