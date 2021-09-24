package com.ufsj.projetovaca.fazenda.apresentationLayer.DTO;




import lombok.Data;

@Data
public class CochoInput {
	private String identificacao;
	private float altura;
	private float largura;
	private boolean isCoberto;
	private long idMateriaPrima;
	private Localizacao localizacao;
}
