package com.ufsj.projetovaca.financeiro.domainLayer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufsj.projetovaca.financeiro.domainLayer.models.TipoReceita;
public interface TipoReceitaRepository extends JpaRepository<TipoReceita, Long> {

}
