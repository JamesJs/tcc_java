package com.ufsj.projetovaca.fazenda.domainLayer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufsj.projetovaca.fazenda.domainLayer.models.Fazenda;

public interface FazendaRepository extends JpaRepository<Fazenda, Long> {

}
