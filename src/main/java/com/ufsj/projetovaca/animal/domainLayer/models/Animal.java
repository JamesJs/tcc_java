package com.ufsj.projetovaca.animal.domainLayer.models;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ufsj.projetovaca.animal.domainLayer.models.embedded.LoteAnimal;

import lombok.Data;
@Data
@Entity
public class Animal {
	@Id
	@Column(name="idAnimal")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private boolean isLactacao;
	@Column
	private boolean isPrenha;
	@Column
	private String brinco;
	@Column
	private boolean isVendido;
	@Embedded
	private LoteAnimal lote;
	@Column
	private int idaAoCochoSal;
	@Column
	private int idaAoCochoAgua;
	@Column
	private int idaAoCochoRacao;
	
	
	
	public void InicializarNovoAnimal(){
		setLactacao(false);
		setPrenha(false);	
		setVendido(false);
		setIdaAoCochoAgua(0);
		setIdaAoCochoRacao(0);
		setIdaAoCochoSal(0);
	}
	
	public void relacionarComLote(long idLote) {
		
		LoteAnimal loteAnimal = new LoteAnimal();
		
		loteAnimal.setIdLote(idLote);
		
		this.setLote(loteAnimal);
		
	}
	
	public void incrementaIdaAoCocho(String tipo) {
		
		if(tipo.toLowerCase().compareToIgnoreCase("agua") == 0) {
			
			this.setIdaAoCochoAgua(idaAoCochoAgua+1);
			
		}
		
		if(tipo.toLowerCase().compareToIgnoreCase("racao") == 0) {
			
			this.setIdaAoCochoRacao(idaAoCochoRacao+1);
			
		}
		
		if(tipo.toLowerCase().compareToIgnoreCase("sal") == 0) {
			
			this.setIdaAoCochoSal(idaAoCochoSal+1);
			
		}
		
		
	}
	
	
	
	
}
