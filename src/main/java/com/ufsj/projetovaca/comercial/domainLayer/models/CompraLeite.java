package com.ufsj.projetovaca.comercial.domainLayer.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ufsj.projetovaca.comercial.domainLayer.models.embedded.CompraLeiteComprador;

import lombok.Data;
@Data
@Entity
public class CompraLeite {
	@Id
	@Column(name = "idCompraLeite")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	private float valor;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	@Column
	private float litros;
	
	@Column
	private boolean isCancelado;
	
	@Embedded
	private CompraLeiteComprador comprador;
	
	public long getCompradorId(){
		return this.getComprador().getIdComprador();
	}
}
