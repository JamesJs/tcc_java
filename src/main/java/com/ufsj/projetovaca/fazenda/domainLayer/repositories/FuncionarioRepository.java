package com.ufsj.projetovaca.fazenda.domainLayer.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufsj.projetovaca.fazenda.domainLayer.models.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
	Optional<List<Funcionario>> findByFuncao_IdFuncao(Long idFuncao);
}
