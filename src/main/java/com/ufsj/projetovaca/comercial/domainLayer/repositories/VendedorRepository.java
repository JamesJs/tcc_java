package com.ufsj.projetovaca.comercial.domainLayer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufsj.projetovaca.comercial.domainLayer.models.Vendedor;
@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Long> {

}
