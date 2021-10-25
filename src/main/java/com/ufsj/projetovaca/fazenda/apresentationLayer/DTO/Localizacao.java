package com.ufsj.projetovaca.fazenda.apresentationLayer.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Localização.",description = "Submodelo utilizado por outros modelos para indicar a localização geográfica.")
public class Localizacao {
	@ApiModelProperty(value = "Latitude do recurso.",example = "-54",required = true)
	private float latitude;
	@ApiModelProperty(value = "longitude do recurso.",example = "-34",required = true)
	private float longitude;
}
