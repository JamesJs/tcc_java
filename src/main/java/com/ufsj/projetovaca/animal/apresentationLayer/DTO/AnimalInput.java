package com.ufsj.projetovaca.animal.apresentationLayer.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Entrada de animal.",description = "Modelo que demonstra a entrada dos endpoints de animal.")
public class AnimalInput {
	@ApiModelProperty(value = "Código do brinco.",example = "AC12324",required = true)
	private String brinco;
	@ApiModelProperty(value = "Id do lote ao qual o animal pertence.",example = "1",required = true)
	private long idLote;
}
