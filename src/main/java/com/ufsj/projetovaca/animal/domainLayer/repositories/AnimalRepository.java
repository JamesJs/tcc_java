package com.ufsj.projetovaca.animal.domainLayer.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufsj.projetovaca.animal.domainLayer.models.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
	public List<Animal> findByloteIdLote(long idLote);
}
