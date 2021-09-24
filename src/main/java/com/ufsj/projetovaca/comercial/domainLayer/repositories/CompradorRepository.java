package com.ufsj.projetovaca.comercial.domainLayer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufsj.projetovaca.comercial.domainLayer.models.Comprador;
@Repository
public interface CompradorRepository extends JpaRepository<Comprador, Long> {

}
