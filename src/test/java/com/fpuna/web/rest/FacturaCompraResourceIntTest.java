package com.fpuna.web.rest;

import com.fpuna.SigoApp;

import com.fpuna.domain.FacturaCompra;
import com.fpuna.repository.FacturaCompraRepository;
import com.fpuna.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the FacturaCompraResource REST controller.
 *
 * @see FacturaCompraResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SigoApp.class)
public class FacturaCompraResourceIntTest {

    private static final Integer DEFAULT_COSTO = 1;
    private static final Integer UPDATED_COSTO = 2;

    @Autowired
    private FacturaCompraRepository facturaCompraRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFacturaCompraMockMvc;

    private FacturaCompra facturaCompra;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        FacturaCompraResource facturaCompraResource = new FacturaCompraResource(facturaCompraRepository);
        this.restFacturaCompraMockMvc = MockMvcBuilders.standaloneSetup(facturaCompraResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FacturaCompra createEntity(EntityManager em) {
        FacturaCompra facturaCompra = new FacturaCompra()
            .costo(DEFAULT_COSTO);
        return facturaCompra;
    }

    @Before
    public void initTest() {
        facturaCompra = createEntity(em);
    }

    @Test
    @Transactional
    public void createFacturaCompra() throws Exception {
        int databaseSizeBeforeCreate = facturaCompraRepository.findAll().size();

        // Create the FacturaCompra
        restFacturaCompraMockMvc.perform(post("/api/factura-compras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(facturaCompra)))
            .andExpect(status().isCreated());

        // Validate the FacturaCompra in the database
        List<FacturaCompra> facturaCompraList = facturaCompraRepository.findAll();
        assertThat(facturaCompraList).hasSize(databaseSizeBeforeCreate + 1);
        FacturaCompra testFacturaCompra = facturaCompraList.get(facturaCompraList.size() - 1);
        assertThat(testFacturaCompra.getCosto()).isEqualTo(DEFAULT_COSTO);
    }

    @Test
    @Transactional
    public void createFacturaCompraWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = facturaCompraRepository.findAll().size();

        // Create the FacturaCompra with an existing ID
        facturaCompra.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFacturaCompraMockMvc.perform(post("/api/factura-compras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(facturaCompra)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<FacturaCompra> facturaCompraList = facturaCompraRepository.findAll();
        assertThat(facturaCompraList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllFacturaCompras() throws Exception {
        // Initialize the database
        facturaCompraRepository.saveAndFlush(facturaCompra);

        // Get all the facturaCompraList
        restFacturaCompraMockMvc.perform(get("/api/factura-compras?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(facturaCompra.getId().intValue())))
            .andExpect(jsonPath("$.[*].costo").value(hasItem(DEFAULT_COSTO)));
    }

    @Test
    @Transactional
    public void getFacturaCompra() throws Exception {
        // Initialize the database
        facturaCompraRepository.saveAndFlush(facturaCompra);

        // Get the facturaCompra
        restFacturaCompraMockMvc.perform(get("/api/factura-compras/{id}", facturaCompra.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(facturaCompra.getId().intValue()))
            .andExpect(jsonPath("$.costo").value(DEFAULT_COSTO));
    }

    @Test
    @Transactional
    public void getNonExistingFacturaCompra() throws Exception {
        // Get the facturaCompra
        restFacturaCompraMockMvc.perform(get("/api/factura-compras/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFacturaCompra() throws Exception {
        // Initialize the database
        facturaCompraRepository.saveAndFlush(facturaCompra);
        int databaseSizeBeforeUpdate = facturaCompraRepository.findAll().size();

        // Update the facturaCompra
        FacturaCompra updatedFacturaCompra = facturaCompraRepository.findOne(facturaCompra.getId());
        updatedFacturaCompra
            .costo(UPDATED_COSTO);

        restFacturaCompraMockMvc.perform(put("/api/factura-compras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedFacturaCompra)))
            .andExpect(status().isOk());

        // Validate the FacturaCompra in the database
        List<FacturaCompra> facturaCompraList = facturaCompraRepository.findAll();
        assertThat(facturaCompraList).hasSize(databaseSizeBeforeUpdate);
        FacturaCompra testFacturaCompra = facturaCompraList.get(facturaCompraList.size() - 1);
        assertThat(testFacturaCompra.getCosto()).isEqualTo(UPDATED_COSTO);
    }

    @Test
    @Transactional
    public void updateNonExistingFacturaCompra() throws Exception {
        int databaseSizeBeforeUpdate = facturaCompraRepository.findAll().size();

        // Create the FacturaCompra

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restFacturaCompraMockMvc.perform(put("/api/factura-compras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(facturaCompra)))
            .andExpect(status().isCreated());

        // Validate the FacturaCompra in the database
        List<FacturaCompra> facturaCompraList = facturaCompraRepository.findAll();
        assertThat(facturaCompraList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteFacturaCompra() throws Exception {
        // Initialize the database
        facturaCompraRepository.saveAndFlush(facturaCompra);
        int databaseSizeBeforeDelete = facturaCompraRepository.findAll().size();

        // Get the facturaCompra
        restFacturaCompraMockMvc.perform(delete("/api/factura-compras/{id}", facturaCompra.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<FacturaCompra> facturaCompraList = facturaCompraRepository.findAll();
        assertThat(facturaCompraList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FacturaCompra.class);
        FacturaCompra facturaCompra1 = new FacturaCompra();
        facturaCompra1.setId(1L);
        FacturaCompra facturaCompra2 = new FacturaCompra();
        facturaCompra2.setId(facturaCompra1.getId());
        assertThat(facturaCompra1).isEqualTo(facturaCompra2);
        facturaCompra2.setId(2L);
        assertThat(facturaCompra1).isNotEqualTo(facturaCompra2);
        facturaCompra1.setId(null);
        assertThat(facturaCompra1).isNotEqualTo(facturaCompra2);
    }
}
