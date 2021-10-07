package com.ufsj.projetovaca.animal.domainLayer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufsj.projetovaca.animal.domainLayer.models.Lote;

public interface LoteRepository extends JpaRepository<Lote, Long>{
	
}
