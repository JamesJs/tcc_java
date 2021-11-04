package com.ufsj.projetovaca.animal.apresentationLayer.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@ApiModel(value = "Saída de animal",description = "Modelo que demonstra a saída dos endpoints de animal.")
@Data
public class AnimalOutput {
	@ApiModelProperty(value = "Id do animal.",example = "1",required = true)
	private long id;
	@ApiModelProperty(value = "Campo que informa se o animal está em período de lactação",example = "false",required = true,dataType = "boolean")
	private boolean isLactacao;
	@ApiModelProperty(value = "Campo que indica se o animal está prenho.",example = "true",required = true,dataType = "boolean")
	private boolean isPrenha;
	@ApiModelProperty(value = "Código do brinco.",example = "AC12324",required = true)
	private String brinco;
	@ApiModelProperty(value = "Id do lote ao qual o animal pertence. Id com o valor 0 indica que o animal não pertence à nenhum cocho.",example = "AC12324",required = true)
	private LoteOutput lote;
	@ApiModelProperty(value = "Campo que indica se o animal já foi vendido.",example = "false",required = true,dataType = "boolean")
	private boolean isVendido;
	@ApiModelProperty(value = "Quantidade de vezes que o animal foi no cocho de sal.",example = "1",required = true)
	private int idaAoCochoSal;
	@ApiModelProperty(value = "Quantidade de vezes que o animal foi no cocho de água.",example = "1",required = true)
	private int idaAoCochoAgua;
	@ApiModelProperty(value = "Quantidade de vezes que o animal foi no cocho de ração.",example = "1",required = true)
	private int idaAoCochoRacao;
}
