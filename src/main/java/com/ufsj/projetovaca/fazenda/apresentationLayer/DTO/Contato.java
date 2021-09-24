package com.ufsj.projetovaca.fazenda.apresentationLayer.DTO;

import javax.persistence.Column;

import lombok.Data;
@Data
public class Contato {
	private String numeroCel;
	@Column
	private String numeroFixo;
	@Column
	private String email;
}
