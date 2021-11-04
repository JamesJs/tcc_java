package com.ufsj.projetovaca.fazenda.apresentationLayer.DTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@ApiModel(value = "Saída de cocho",description = "Modelo que demonstra a saída dos endpoints de cocho.")
@Data
public class CochoOutput {
	private long id;
	@ApiModelProperty(value = "Identificação do cocho.",example = "A3DSR34",required = true)
	private String identificacao;
	@ApiModelProperty(value = "Altura do cocho em metros.",example = "0.3",required = true)
	private float altura;
	@ApiModelProperty(value = "Largura do cocho em metros.",example = "1",required = true)
	private float largura;
	@ApiModelProperty(value = "Campo que informa se o cocho é coberto ou não.",example = "falso",required = true)
	private boolean isCoberto;
	@ApiModelProperty(value = "Id da fazenda na qual o cocho pertence.",example = "1",required = true)
	private long idFazenda;
	@ApiModelProperty(value = "Id da matéria prima da qual o cocho é feito.",example = "1",required = true)
	private long idMateriaPrima;
	@ApiModelProperty(value = "Tipo de cocho podendo ser de SAL,AGUA ou RACAO.",example = "AGUA",required = true)
	private String tipo;
}
