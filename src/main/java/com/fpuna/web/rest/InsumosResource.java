package com.fpuna.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.fpuna.domain.Insumos;

import com.fpuna.repository.InsumosRepository;
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
 * REST controller for managing Insumos.
 */
@RestController
@RequestMapping("/api")
public class InsumosResource {

    private final Logger log = LoggerFactory.getLogger(InsumosResource.class);

    private static final String ENTITY_NAME = "insumos";

    private final InsumosRepository insumosRepository;

    public InsumosResource(InsumosRepository insumosRepository) {
        this.insumosRepository = insumosRepository;
    }

    /**
     * POST  /insumos : Create a new insumos.
     *
     * @param insumos the insumos to create
     * @return the ResponseEntity with status 201 (Created) and with body the new insumos, or with status 400 (Bad Request) if the insumos has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/insumos")
    @Timed
    public ResponseEntity<Insumos> createInsumos(@RequestBody Insumos insumos) throws URISyntaxException {
        log.debug("REST request to save Insumos : {}", insumos);
        if (insumos.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new insumos cannot already have an ID")).body(null);
        }
        Insumos result = insumosRepository.save(insumos);
        return ResponseEntity.created(new URI("/api/insumos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /insumos : Updates an existing insumos.
     *
     * @param insumos the insumos to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated insumos,
     * or with status 400 (Bad Request) if the insumos is not valid,
     * or with status 500 (Internal Server Error) if the insumos couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/insumos")
    @Timed
    public ResponseEntity<Insumos> updateInsumos(@RequestBody Insumos insumos) throws URISyntaxException {
        log.debug("REST request to update Insumos : {}", insumos);
        if (insumos.getId() == null) {
            return createInsumos(insumos);
        }
        Insumos result = insumosRepository.save(insumos);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, insumos.getId().toString()))
            .body(result);
    }

    /**
     * GET  /insumos : get all the insumos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of insumos in body
     */
    @GetMapping("/insumos")
    @Timed
    public List<Insumos> getAllInsumos() {
        log.debug("REST request to get all Insumos");
        return insumosRepository.findAll();
    }

    /**
     * GET  /insumos/:id : get the "id" insumos.
     *
     * @param id the id of the insumos to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the insumos, or with status 404 (Not Found)
     */
    @GetMapping("/insumos/{id}")
    @Timed
    public ResponseEntity<Insumos> getInsumos(@PathVariable Long id) {
        log.debug("REST request to get Insumos : {}", id);
        Insumos insumos = insumosRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(insumos));
    }

    /**
     * DELETE  /insumos/:id : delete the "id" insumos.
     *
     * @param id the id of the insumos to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/insumos/{id}")
    @Timed
    public ResponseEntity<Void> deleteInsumos(@PathVariable Long id) {
        log.debug("REST request to delete Insumos : {}", id);
        insumosRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
