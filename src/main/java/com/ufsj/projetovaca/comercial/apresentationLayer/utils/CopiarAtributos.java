package com.ufsj.projetovaca.comercial.apresentationLayer.utils;

import org.springframework.beans.BeanUtils;

public class CopiarAtributos {
		public static <G,K> void copiarAtributos(G destino,K origem,String id) {
			BeanUtils.copyProperties(origem, destino, id);
	}
}
