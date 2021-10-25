package com.ufsj.projetovaca.comercial.apresentationLayer.DTO;




import com.ufsj.projetovaca.comercial.domainLayer.models.embedded.Contato;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
@ApiModel(value = "Entrada de vendedor",description = "Modelo que demonstra a entrada dos endpoints de vendedor.")
public class VendedorInput {
	@ApiModelProperty(value = "Nome do vendedor.",example = "Fazenda riacho",required = true)
	private String nome;
	
	private Contato contato;
}
