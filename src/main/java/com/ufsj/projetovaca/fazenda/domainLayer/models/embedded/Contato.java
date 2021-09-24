package com.ufsj.projetovaca.fazenda.domainLayer.models.embedded;

import javax.persistence.Column;

import lombok.Data;

@Data
public class Contato {
		@Column
		private String numeroCel;
		@Column
		private String numeroFixo;
		@Column
		private String email;
}
