package com.ufsj.projetovaca.comercial.domainLayer.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

//Não usar por enquanto
//import com.ufsj.projetovaca.comercial.domainLayer.models.embedded.Animal;
import com.ufsj.projetovaca.comercial.domainLayer.models.embedded.CompraGadoComprador;

import lombok.Data;
@Data
@Entity
public class CompraGado {
	
	@Id
	@Column(name = "idCompraGado")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	private float valor;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	//@Column
	//private float pesoTotal;
	
	@Column
	private boolean isCancelada;
	
	@Embedded
	private CompraGadoComprador comprador;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name="CompraGadoAnimal",joinColumns = @JoinColumn(name="idCompraGado"))
	private List<Animal> animais = new ArrayList<Animal>();
	
	public void adicionaAnimaisCompra(List<Long> ids){
		ids.stream().forEach((id)->{
			Animal animal = new Animal();
			animal.setIdAnimal(id);
		
			this.animais.add(animal);
		});
	}
	
	public List<Long> obtemIdsAnimais(){
		List<Long> ids = new ArrayList<Long>();
		animais.stream().forEach((animal)->{
			ids.add(animal.getIdAnimal());
		});
		return ids;
	}
	
	
}
