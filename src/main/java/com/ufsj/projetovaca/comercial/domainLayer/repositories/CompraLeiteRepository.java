package com.ufsj.projetovaca.comercial.domainLayer.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufsj.projetovaca.comercial.domainLayer.models.CompraLeite;
@Repository
public interface CompraLeiteRepository extends JpaRepository<CompraLeite, Long> {
	public Optional<List<CompraLeite>> findByComprador_IdComprador(long idComprador);
}
