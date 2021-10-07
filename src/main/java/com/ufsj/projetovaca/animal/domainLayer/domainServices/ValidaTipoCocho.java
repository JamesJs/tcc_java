package com.ufsj.projetovaca.animal.domainLayer.domainServices;

import org.springframework.stereotype.Service;

@Service
public class ValidaTipoCocho {
	public boolean executar(String tipo) {
		
		if(tipo.compareToIgnoreCase("agua") != 0 &&
		   tipo.compareToIgnoreCase("racao") != 0 
			&& tipo.compareToIgnoreCase("sal") != 0 ) {
			
			return false;
			
		}
		
		return true;
		
	}
}
