package com.ufsj.projetovaca.financeiro.domainLayer.models.embedded;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;
@Data
@Embeddable
public class TipoReceitaReceita {
	@Column
	private long idTipoReceita;
}
