package com.ufsj.projetovaca.authentication.domainLayer.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufsj.projetovaca.authentication.domainLayer.models.Usuario;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	public Optional<Usuario> findByLogin(String string);
}
