package com.fpuna.repository;

import com.fpuna.domain.DetalleFacturaCompra;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the DetalleFacturaCompra entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DetalleFacturaCompraRepository extends JpaRepository<DetalleFacturaCompra,Long> {
    
}
