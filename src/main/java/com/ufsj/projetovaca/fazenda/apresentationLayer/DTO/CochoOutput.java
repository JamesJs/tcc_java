package com.ufsj.projetovaca.fazenda.apresentationLayer.DTO;
import lombok.Data;

@Data
public class CochoOutput {
	private long id;
	private String identificacao;
	private float altura;
	private float largura;
	private boolean isCoberto;
	private long idFazenda;
	private long idMateriaPrima;
}
