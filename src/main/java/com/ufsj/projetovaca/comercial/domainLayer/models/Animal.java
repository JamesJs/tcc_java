package com.ufsj.projetovaca.comercial.domainLayer.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
@Data
@Embeddable
public class Animal {
	@Column(name = "idAnimal")
	private Long idAnimal;
}
