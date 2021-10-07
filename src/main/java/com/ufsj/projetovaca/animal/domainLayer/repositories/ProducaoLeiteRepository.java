package com.ufsj.projetovaca.animal.domainLayer.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufsj.projetovaca.animal.domainLayer.models.ProducaoLeite;

public interface ProducaoLeiteRepository extends JpaRepository<ProducaoLeite, Long> {
	public List<ProducaoLeite> findByDataBetween(Date dataInicial,Date dataFinal);
	public List<ProducaoLeite> findByAnimalIdAnimalIn(List<Long> idAnimais);
	public List<ProducaoLeite> findByAnimalIdAnimal(long idAnimal);
}
