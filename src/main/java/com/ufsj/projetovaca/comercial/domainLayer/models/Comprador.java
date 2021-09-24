package com.ufsj.projetovaca.comercial.domainLayer.models;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ufsj.projetovaca.comercial.domainLayer.models.embedded.Contato;
import com.ufsj.projetovaca.comercial.domainLayer.models.enums.TipoComprador;

import lombok.Data;
@Data
@Entity
public class Comprador {
	@Id
	@Column(name = "idComprador")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String nome;
	@Column
	private boolean isAtivo;
	@Column
	private TipoComprador tipoComprador;
	@Embedded
	private Contato contato;
	
	public boolean definirComprador(String msg){
		
		if(msg.toLowerCase().equals("leite")) {
			setTipoComprador(TipoComprador.LEITE);
			return true;
		}
		else if(msg.toLowerCase().equals("gado".toLowerCase())){
			setTipoComprador(TipoComprador.GADO);
			return true;
		}else if(msg.toLowerCase().equals("ambos".toLowerCase())) {
			setTipoComprador(TipoComprador.AMBOS);
			return true;
		}else {
			return false;
		}
		
	}
}
