package com.ufsj.projetovaca.animal.domainLayer.models.embedded;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;
@Embeddable
@Data

public class AnimalProducaoLeite {
	@Column
	private long idAnimal;
}
