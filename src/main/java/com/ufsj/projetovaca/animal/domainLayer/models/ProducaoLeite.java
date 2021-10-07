package com.ufsj.projetovaca.animal.domainLayer.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ufsj.projetovaca.animal.domainLayer.models.embedded.AnimalProducaoLeite;

import lombok.Data;
@Data
@Entity
public class ProducaoLeite {
	//Fazer a data como id ou fazer esse entidade como object value da entidade animal
	@Id
	@Column(name = "idProducaoLeite")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private float quantLitros;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	@Embedded
	private AnimalProducaoLeite animal;
	
	public void relacionarComAnimal(long idAnimal) {
		
		AnimalProducaoLeite animalProducaoLeite = new AnimalProducaoLeite();
		
		animalProducaoLeite.setIdAnimal(idAnimal);
		
		this.setAnimal(animalProducaoLeite);

	}
	
	public long obterIdAnimal() {
		return this.getAnimal().getIdAnimal();
	}
}
