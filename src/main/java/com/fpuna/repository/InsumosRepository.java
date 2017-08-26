package com.fpuna.repository;

import com.fpuna.domain.Insumos;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Insumos entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InsumosRepository extends JpaRepository<Insumos,Long> {
    
}
