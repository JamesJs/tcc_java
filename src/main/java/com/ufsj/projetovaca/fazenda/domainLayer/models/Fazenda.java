package com.ufsj.projetovaca.fazenda.domainLayer.models;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.springframework.beans.BeanUtils;

import com.ufsj.projetovaca.fazenda.apresentationLayer.utils.CopiarAtributos;
import com.ufsj.projetovaca.fazenda.domainLayer.models.embedded.Localizacao;

import lombok.Data;
@Data
@Entity
public class Fazenda {
	@Id
	@Column(name="idFazenda")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String nome;
	@Column
	private int tamanho;
	@Column
	private int quantFuncionarios;
	@Column
	private boolean isVendida;
	@Embedded
	private Localizacao localizacao;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idFazenda")
	private List<Cocho> cochos = new ArrayList<Cocho>();
	
	public void addCocho(Cocho novoCocho) {
		cochos.add(novoCocho);
	}
	
	public Cocho removerCocho(long id) {
		Cocho findCocho = new Cocho();
		findCocho.setId(null);
		List<Cocho> cochosFiltrado =  cochos.stream().filter((cocho)-> 
		{
			if(cocho.getId() == id) {
				
				BeanUtils.copyProperties(cocho, findCocho);
				
				return false;
			}
			
			return true;
			
		}).collect(Collectors.toList());
		
		if(findCocho.getId() == null) {
			return null;
		}
		
		setCochos(cochosFiltrado);
		
		return findCocho;
		
	}
	
	public Cocho alterarCocho(long idCocho,Cocho attCocho) {
		
		Cocho findCocho = new Cocho(); 
		
		findCocho.setId(null);
		
		List<Cocho> novosCochos = cochos.stream().map((cocho)->{
		
			if(cocho.getId() == idCocho) {
		
				CopiarAtributos.copiarAtributos(cocho, attCocho, "id");
				
				BeanUtils.copyProperties(cocho, findCocho);	
				
				System.out.println(findCocho);
			}
		
			return cocho;
		
		}).collect(Collectors.toList());
		
		
		if(findCocho.getId() == null) {
			return null;
		}
		
		setCochos(novosCochos);
		
		return findCocho;
	
	}
	
	public boolean podeAdicionarCocho(Localizacao localizacao) {
		for(Cocho cocho:cochos) {
			 if(cocho.getLocalizacao().getLatitude() == localizacao.getLatitude() && 
			    cocho.getLocalizacao().getLongitude() == localizacao.getLongitude()) {
				 return false;
			 }
				 
		}
		return true;
	}
}
