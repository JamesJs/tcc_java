package com.ufsj.projetovaca.comercial.domainLayer.models.embedded;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;
@Embeddable
@Data
public class CompraGadoComprador {
	@Column
	private long idComprador;
}
