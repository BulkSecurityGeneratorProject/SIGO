package com.fpuna.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.fpuna.domain.DetalleFacturaCompra;

import com.fpuna.repository.DetalleFacturaCompraRepository;
import com.fpuna.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing DetalleFacturaCompra.
 */
@RestController
@RequestMapping("/api")
public class DetalleFacturaCompraResource {

    private final Logger log = LoggerFactory.getLogger(DetalleFacturaCompraResource.class);

    private static final String ENTITY_NAME = "detalleFacturaCompra";

    private final DetalleFacturaCompraRepository detalleFacturaCompraRepository;

    public DetalleFacturaCompraResource(DetalleFacturaCompraRepository detalleFacturaCompraRepository) {
        this.detalleFacturaCompraRepository = detalleFacturaCompraRepository;
    }

    /**
     * POST  /detalle-factura-compras : Create a new detalleFacturaCompra.
     *
     * @param detalleFacturaCompra the detalleFacturaCompra to create
     * @return the ResponseEntity with status 201 (Created) and with body the new detalleFacturaCompra, or with status 400 (Bad Request) if the detalleFacturaCompra has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/detalle-factura-compras")
    @Timed
    public ResponseEntity<DetalleFacturaCompra> createDetalleFacturaCompra(@RequestBody DetalleFacturaCompra detalleFacturaCompra) throws URISyntaxException {
        log.debug("REST request to save DetalleFacturaCompra : {}", detalleFacturaCompra);
        if (detalleFacturaCompra.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new detalleFacturaCompra cannot already have an ID")).body(null);
        }
        DetalleFacturaCompra result = detalleFacturaCompraRepository.save(detalleFacturaCompra);
        return ResponseEntity.created(new URI("/api/detalle-factura-compras/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /detalle-factura-compras : Updates an existing detalleFacturaCompra.
     *
     * @param detalleFacturaCompra the detalleFacturaCompra to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated detalleFacturaCompra,
     * or with status 400 (Bad Request) if the detalleFacturaCompra is not valid,
     * or with status 500 (Internal Server Error) if the detalleFacturaCompra couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/detalle-factura-compras")
    @Timed
    public ResponseEntity<DetalleFacturaCompra> updateDetalleFacturaCompra(@RequestBody DetalleFacturaCompra detalleFacturaCompra) throws URISyntaxException {
        log.debug("REST request to update DetalleFacturaCompra : {}", detalleFacturaCompra);
        if (detalleFacturaCompra.getId() == null) {
            return createDetalleFacturaCompra(detalleFacturaCompra);
        }
        DetalleFacturaCompra result = detalleFacturaCompraRepository.save(detalleFacturaCompra);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, detalleFacturaCompra.getId().toString()))
            .body(result);
    }

    /**
     * GET  /detalle-factura-compras : get all the detalleFacturaCompras.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of detalleFacturaCompras in body
     */
    @GetMapping("/detalle-factura-compras")
    @Timed
    public List<DetalleFacturaCompra> getAllDetalleFacturaCompras() {
        log.debug("REST request to get all DetalleFacturaCompras");
        return detalleFacturaCompraRepository.findAll();
    }

    /**
     * GET  /detalle-factura-compras/:id : get the "id" detalleFacturaCompra.
     *
     * @param id the id of the detalleFacturaCompra to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the detalleFacturaCompra, or with status 404 (Not Found)
     */
    @GetMapping("/detalle-factura-compras/{id}")
    @Timed
    public ResponseEntity<DetalleFacturaCompra> getDetalleFacturaCompra(@PathVariable Long id) {
        log.debug("REST request to get DetalleFacturaCompra : {}", id);
        DetalleFacturaCompra detalleFacturaCompra = detalleFacturaCompraRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(detalleFacturaCompra));
    }

    /**
     * DELETE  /detalle-factura-compras/:id : delete the "id" detalleFacturaCompra.
     *
     * @param id the id of the detalleFacturaCompra to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/detalle-factura-compras/{id}")
    @Timed
    public ResponseEntity<Void> deleteDetalleFacturaCompra(@PathVariable Long id) {
        log.debug("REST request to delete DetalleFacturaCompra : {}", id);
        detalleFacturaCompraRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
