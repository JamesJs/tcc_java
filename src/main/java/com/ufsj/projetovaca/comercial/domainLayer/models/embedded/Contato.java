package com.ufsj.projetovaca.comercial.domainLayer.models.embedded;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;
@Embeddable
@Data
public class Contato {
		@Column
		private String numeroCel;
		@Column
		private String numeroFixo;
		@Column
		private String email;
}

