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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ufsj.projetovaca.comercial.domainLayer.models.embedded.VendaGadoVendedor;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
@Entity
public class VendaGado {
	@Id
	@Column(name = "idVendaGado")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private float valor;
	@Column
	private boolean isCancelado;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	@Column
	private int quantAnimais;
	@ApiModelProperty(value = "Id do vendedor que realizou a venda e que está cadastrado na aplicação.",example = "1",required = true)
	@Embedded
	private VendaGadoVendedor vendedor;
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name="VendaGadoAnimal",joinColumns = @JoinColumn(name="idVendaGado"))
	private List<Animal> animais = new ArrayList<Animal>();
}
