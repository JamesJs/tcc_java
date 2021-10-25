package com.ufsj.projetovaca.animal.apresentationLayer.DTO;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
@ApiModel(value = "Saída de produção de leite",description = "Modelo que demonstra a saída dos endpoints de produção de leite.")
public class ProducaoLeiteOutput {
	@ApiModelProperty(value = "Id da produção de leite.",example = "1",required = true)
	private long id;
	@ApiModelProperty(value = "Quantidade de litros de leite produzidos.",example = "10.00",required = true)
	private float quantLitros;
	@ApiModelProperty(value = "Data da produção no formato YYYY/mm/ddTHH:mm.",example = "2021/11/12T08:00",required = true)
	private Date data;
	@ApiModelProperty(value = "Id do animal que produziu o leite.",example = "1",required = true)
	private long idAnimal;
}
