package com.fpuna.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.fpuna.domain.FacturaCompra;

import com.fpuna.repository.FacturaCompraRepository;
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
 * REST controller for managing FacturaCompra.
 */
@RestController
@RequestMapping("/api")
public class FacturaCompraResource {

    private final Logger log = LoggerFactory.getLogger(FacturaCompraResource.class);

    private static final String ENTITY_NAME = "facturaCompra";

    private final FacturaCompraRepository facturaCompraRepository;

    public FacturaCompraResource(FacturaCompraRepository facturaCompraRepository) {
        this.facturaCompraRepository = facturaCompraRepository;
    }

    /**
     * POST  /factura-compras : Create a new facturaCompra.
     *
     * @param facturaCompra the facturaCompra to create
     * @return the ResponseEntity with status 201 (Created) and with body the new facturaCompra, or with status 400 (Bad Request) if the facturaCompra has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/factura-compras")
    @Timed
    public ResponseEntity<FacturaCompra> createFacturaCompra(@RequestBody FacturaCompra facturaCompra) throws URISyntaxException {
        log.debug("REST request to save FacturaCompra : {}", facturaCompra);
        if (facturaCompra.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new facturaCompra cannot already have an ID")).body(null);
        }
        FacturaCompra result = facturaCompraRepository.save(facturaCompra);
        return ResponseEntity.created(new URI("/api/factura-compras/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /factura-compras : Updates an existing facturaCompra.
     *
     * @param facturaCompra the facturaCompra to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated facturaCompra,
     * or with status 400 (Bad Request) if the facturaCompra is not valid,
     * or with status 500 (Internal Server Error) if the facturaCompra couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/factura-compras")
    @Timed
    public ResponseEntity<FacturaCompra> updateFacturaCompra(@RequestBody FacturaCompra facturaCompra) throws URISyntaxException {
        log.debug("REST request to update FacturaCompra : {}", facturaCompra);
        if (facturaCompra.getId() == null) {
            return createFacturaCompra(facturaCompra);
        }
        FacturaCompra result = facturaCompraRepository.save(facturaCompra);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, facturaCompra.getId().toString()))
            .body(result);
    }

    /**
     * GET  /factura-compras : get all the facturaCompras.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of facturaCompras in body
     */
    @GetMapping("/factura-compras")
    @Timed
    public List<FacturaCompra> getAllFacturaCompras() {
        log.debug("REST request to get all FacturaCompras");
        return facturaCompraRepository.findAll();
    }

    /**
     * GET  /factura-compras/:id : get the "id" facturaCompra.
     *
     * @param id the id of the facturaCompra to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the facturaCompra, or with status 404 (Not Found)
     */
    @GetMapping("/factura-compras/{id}")
    @Timed
    public ResponseEntity<FacturaCompra> getFacturaCompra(@PathVariable Long id) {
        log.debug("REST request to get FacturaCompra : {}", id);
        FacturaCompra facturaCompra = facturaCompraRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(facturaCompra));
    }

    /**
     * DELETE  /factura-compras/:id : delete the "id" facturaCompra.
     *
     * @param id the id of the facturaCompra to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/factura-compras/{id}")
    @Timed
    public ResponseEntity<Void> deleteFacturaCompra(@PathVariable Long id) {
        log.debug("REST request to delete FacturaCompra : {}", id);
        facturaCompraRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
