package com.ufsj.projetovaca.financeiro.domainLayer.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufsj.projetovaca.financeiro.domainLayer.models.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {
	public Optional<List<Conta>> findByTipo_idTipoConta(Long id);
}
