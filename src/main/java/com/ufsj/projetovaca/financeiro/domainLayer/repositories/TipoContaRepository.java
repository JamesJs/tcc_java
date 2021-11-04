package com.ufsj.projetovaca.financeiro.domainLayer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufsj.projetovaca.financeiro.domainLayer.models.TipoConta;

public interface TipoContaRepository extends JpaRepository<TipoConta, Long> {

}
