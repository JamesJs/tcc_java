package com.ufsj.projetovaca.fazenda.domainLayer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufsj.projetovaca.fazenda.domainLayer.models.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

}
