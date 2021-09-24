package com.ufsj.projetovaca.fazenda.domainLayer.models.embedded;

import javax.persistence.Column;

import lombok.Data;

@Data
public class CochoMateriaPrima {
	@Column
	private long idMateriaPrima;
}
