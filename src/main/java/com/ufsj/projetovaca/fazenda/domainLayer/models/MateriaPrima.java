package com.ufsj.projetovaca.fazenda.domainLayer.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class MateriaPrima {
	@Id
	@Column(name="idMateriaPrima")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String tipo;
	@Column
	private Boolean isNaoUtilizado;
	
	
	public boolean disponivelParaUso() {
		return isNaoUtilizado ? false : true;
	}
}
