package com.fpuna.repository;

import com.fpuna.domain.FacturaCompra;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the FacturaCompra entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FacturaCompraRepository extends JpaRepository<FacturaCompra,Long> {
    
}
