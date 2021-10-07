package com.ufsj.projetovaca.animal.domainLayer.models.embedded;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;
@Data
@Embeddable
public class LoteAnimal {
	@Column
	private long idLote;
}
