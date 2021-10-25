package com.ufsj.projetovaca.animal.apresentationLayer.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "SubModelo de lote",description = "Submodelo usado em alguns endpoints de animal.")
public class LoteAnimalOutput {
	@ApiModelProperty(value = "Id do lote.",example = "1",required = true)
	private long id;
	@ApiModelProperty(value = "Nome do lote.",example = "lote213",required = true)
	private String nome;
}
