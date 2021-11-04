package com.ufsj.projetovaca.financeiro.domainLayer.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.ufsj.projetovaca.financeiro.domainLayer.models.Receita;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {
	public Optional<List<Receita>> findByTipo_idTipoReceita(Long id);
}
