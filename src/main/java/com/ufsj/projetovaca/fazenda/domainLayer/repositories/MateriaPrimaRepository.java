package com.ufsj.projetovaca.fazenda.domainLayer.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufsj.projetovaca.fazenda.domainLayer.models.MateriaPrima;

public interface MateriaPrimaRepository extends JpaRepository<MateriaPrima, Long> {
	public Optional<MateriaPrima> findByTipo(String tipo);
}
