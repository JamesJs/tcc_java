package com.ufsj.projetovaca.fazenda.domainLayer.models;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ufsj.projetovaca.comercial.domainLayer.models.enums.TipoComprador;
import com.ufsj.projetovaca.fazenda.domainLayer.models.embedded.CochoMateriaPrima;
import com.ufsj.projetovaca.fazenda.domainLayer.models.embedded.Localizacao;
import com.ufsj.projetovaca.fazenda.domainLayer.models.enums.TipoCocho;

import lombok.Data;
@Data
@Entity
public class Cocho {
	@Id
	@Column(name="idCocho")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String identificacao;
	@Column
	private float altura;
	@Column 
	private float largura;
	@Column
	private boolean isCoberto;
	@Embedded
	private CochoMateriaPrima cochoMateriaPrima;
	@Embedded
	private Localizacao localizacao;
	@Column
	private TipoCocho tipo;
	
	public boolean definirTipoCocho(String tipo) {
		
		if(tipo.toLowerCase().equals("racao")) {
			setTipo(TipoCocho.RACAO);
			return true;
		}
		else if(tipo.toLowerCase().equals("agua".toLowerCase())){
			setTipo(TipoCocho.AGUA);
			return true;
		}else if(tipo.toLowerCase().equals("sal".toLowerCase())) {
			setTipo(TipoCocho.SAL);
			return true;
		}else {
			return false;
		}
		
		
	}
}
