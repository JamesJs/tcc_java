package com.ufsj.projetovaca.animal.apresentationLayer.DTO;

import java.util.Date;

import lombok.Data;
@Data
public class ProducaoLeiteOutput {
	private long id;
	private float quantLitros;
	private Date data;
	private long idAnimal;
}
