package com.ufsj.projetovaca.animal.apresentationLayer.DTO;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
@ApiModel(value = "Modelo de produções de leite",description = "Modelo que mostra todas as produções de leite e o cálculo da soma.")
public class ProducaoLeiteTotalOutputResponse {
	@ApiModelProperty(value = "Produções de leite",required = true)
	List<ProducaoLeiteOutput> ProducoesLeite;
	@ApiModelProperty(value = "Total de litros",example = "100.00",required = true)
	float total;
}
