package com.ufsj.projetovaca.comercial.domainLayer.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ufsj.projetovaca.comercial.domainLayer.models.embedded.Animal;
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
	
	@Column
	private float pesoTotal;
	
	@Embedded
	private CompraGadoComprador comprador;
	
	@ElementCollection
	@CollectionTable(name = "compraGadoAnimal",joinColumns = @JoinColumn(name = "idCompraGado"))
	private List<Animal> animais = new ArrayList<Animal>();
	
	
	
	
}
