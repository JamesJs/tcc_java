package com.ufsj.projetovaca.comercial.apresentationLayer.DTO;




import com.ufsj.projetovaca.comercial.domainLayer.models.embedded.Contato;

import lombok.Data;
@Data
public class VendedorInput {
	
	private String nome;
	
	private Contato contato;
}
