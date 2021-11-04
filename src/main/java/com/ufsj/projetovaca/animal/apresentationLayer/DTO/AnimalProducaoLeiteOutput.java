package com.ufsj.projetovaca.animal.apresentationLayer.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Submodelo de animal",description = "Modelo usado em alguns endpoints de produção de leite.")
public class AnimalProducaoLeiteOutput {
	@ApiModelProperty(value = "Id do animal.",example = "1",required = true)
	private long id;
	@ApiModelProperty(value = "Campo que informa se o animal está em período de lactação.",example = "true",required = true)
	private boolean isLactacao;
	@ApiModelProperty(value = "Código do brinco.",example = "AC12324",required = true)
	private String idBrinco;
	private LoteAnimalOutput lote;
	
}
