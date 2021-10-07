package com.ufsj.projetovaca.animal.domainLayer.domainServices;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.animal.domainLayer.models.ProducaoLeite;
@Service
public class CalculaValorTotalProducaoLeite {
	public float executar(List<ProducaoLeite> producoesLeite){
		
		float total = 0;
		
		for(ProducaoLeite producaoLeite:producoesLeite) {
			
			total += producaoLeite.getQuantLitros();
			
		}
		
		return total;
		
	}
}
