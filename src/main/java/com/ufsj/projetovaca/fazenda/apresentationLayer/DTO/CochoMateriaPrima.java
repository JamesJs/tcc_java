package com.ufsj.projetovaca.fazenda.apresentationLayer.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Submodelo de cocho",description = "Submodelo que informa o id da matéria prima de um cocho.")
public class CochoMateriaPrima {
	@ApiModelProperty(value = "Id da matéria prima que o cocho irá utilizar.",example = "1",required = true)
	private long idMateriaPrima;
}
