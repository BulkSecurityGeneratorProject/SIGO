package com.fpuna.web.rest;

import com.fpuna.SigoApp;

import com.fpuna.domain.DetalleFacturaCompra;
import com.fpuna.repository.DetalleFacturaCompraRepository;
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
 * Test class for the DetalleFacturaCompraResource REST controller.
 *
 * @see DetalleFacturaCompraResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SigoApp.class)
public class DetalleFacturaCompraResourceIntTest {

    private static final Integer DEFAULT_CANTIDAD_RECIBIDA = 1;
    private static final Integer UPDATED_CANTIDAD_RECIBIDA = 2;

    @Autowired
    private DetalleFacturaCompraRepository detalleFacturaCompraRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDetalleFacturaCompraMockMvc;

    private DetalleFacturaCompra detalleFacturaCompra;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DetalleFacturaCompraResource detalleFacturaCompraResource = new DetalleFacturaCompraResource(detalleFacturaCompraRepository);
        this.restDetalleFacturaCompraMockMvc = MockMvcBuilders.standaloneSetup(detalleFacturaCompraResource)
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
    public static DetalleFacturaCompra createEntity(EntityManager em) {
        DetalleFacturaCompra detalleFacturaCompra = new DetalleFacturaCompra()
            .cantidadRecibida(DEFAULT_CANTIDAD_RECIBIDA);
        return detalleFacturaCompra;
    }

    @Before
    public void initTest() {
        detalleFacturaCompra = createEntity(em);
    }

    @Test
    @Transactional
    public void createDetalleFacturaCompra() throws Exception {
        int databaseSizeBeforeCreate = detalleFacturaCompraRepository.findAll().size();

        // Create the DetalleFacturaCompra
        restDetalleFacturaCompraMockMvc.perform(post("/api/detalle-factura-compras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(detalleFacturaCompra)))
            .andExpect(status().isCreated());

        // Validate the DetalleFacturaCompra in the database
        List<DetalleFacturaCompra> detalleFacturaCompraList = detalleFacturaCompraRepository.findAll();
        assertThat(detalleFacturaCompraList).hasSize(databaseSizeBeforeCreate + 1);
        DetalleFacturaCompra testDetalleFacturaCompra = detalleFacturaCompraList.get(detalleFacturaCompraList.size() - 1);
        assertThat(testDetalleFacturaCompra.getCantidadRecibida()).isEqualTo(DEFAULT_CANTIDAD_RECIBIDA);
    }

    @Test
    @Transactional
    public void createDetalleFacturaCompraWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = detalleFacturaCompraRepository.findAll().size();

        // Create the DetalleFacturaCompra with an existing ID
        detalleFacturaCompra.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDetalleFacturaCompraMockMvc.perform(post("/api/detalle-factura-compras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(detalleFacturaCompra)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<DetalleFacturaCompra> detalleFacturaCompraList = detalleFacturaCompraRepository.findAll();
        assertThat(detalleFacturaCompraList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDetalleFacturaCompras() throws Exception {
        // Initialize the database
        detalleFacturaCompraRepository.saveAndFlush(detalleFacturaCompra);

        // Get all the detalleFacturaCompraList
        restDetalleFacturaCompraMockMvc.perform(get("/api/detalle-factura-compras?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(detalleFacturaCompra.getId().intValue())))
            .andExpect(jsonPath("$.[*].cantidadRecibida").value(hasItem(DEFAULT_CANTIDAD_RECIBIDA)));
    }

    @Test
    @Transactional
    public void getDetalleFacturaCompra() throws Exception {
        // Initialize the database
        detalleFacturaCompraRepository.saveAndFlush(detalleFacturaCompra);

        // Get the detalleFacturaCompra
        restDetalleFacturaCompraMockMvc.perform(get("/api/detalle-factura-compras/{id}", detalleFacturaCompra.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(detalleFacturaCompra.getId().intValue()))
            .andExpect(jsonPath("$.cantidadRecibida").value(DEFAULT_CANTIDAD_RECIBIDA));
    }

    @Test
    @Transactional
    public void getNonExistingDetalleFacturaCompra() throws Exception {
        // Get the detalleFacturaCompra
        restDetalleFacturaCompraMockMvc.perform(get("/api/detalle-factura-compras/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDetalleFacturaCompra() throws Exception {
        // Initialize the database
        detalleFacturaCompraRepository.saveAndFlush(detalleFacturaCompra);
        int databaseSizeBeforeUpdate = detalleFacturaCompraRepository.findAll().size();

        // Update the detalleFacturaCompra
        DetalleFacturaCompra updatedDetalleFacturaCompra = detalleFacturaCompraRepository.findOne(detalleFacturaCompra.getId());
        updatedDetalleFacturaCompra
            .cantidadRecibida(UPDATED_CANTIDAD_RECIBIDA);

        restDetalleFacturaCompraMockMvc.perform(put("/api/detalle-factura-compras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDetalleFacturaCompra)))
            .andExpect(status().isOk());

        // Validate the DetalleFacturaCompra in the database
        List<DetalleFacturaCompra> detalleFacturaCompraList = detalleFacturaCompraRepository.findAll();
        assertThat(detalleFacturaCompraList).hasSize(databaseSizeBeforeUpdate);
        DetalleFacturaCompra testDetalleFacturaCompra = detalleFacturaCompraList.get(detalleFacturaCompraList.size() - 1);
        assertThat(testDetalleFacturaCompra.getCantidadRecibida()).isEqualTo(UPDATED_CANTIDAD_RECIBIDA);
    }

    @Test
    @Transactional
    public void updateNonExistingDetalleFacturaCompra() throws Exception {
        int databaseSizeBeforeUpdate = detalleFacturaCompraRepository.findAll().size();

        // Create the DetalleFacturaCompra

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDetalleFacturaCompraMockMvc.perform(put("/api/detalle-factura-compras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(detalleFacturaCompra)))
            .andExpect(status().isCreated());

        // Validate the DetalleFacturaCompra in the database
        List<DetalleFacturaCompra> detalleFacturaCompraList = detalleFacturaCompraRepository.findAll();
        assertThat(detalleFacturaCompraList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteDetalleFacturaCompra() throws Exception {
        // Initialize the database
        detalleFacturaCompraRepository.saveAndFlush(detalleFacturaCompra);
        int databaseSizeBeforeDelete = detalleFacturaCompraRepository.findAll().size();

        // Get the detalleFacturaCompra
        restDetalleFacturaCompraMockMvc.perform(delete("/api/detalle-factura-compras/{id}", detalleFacturaCompra.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DetalleFacturaCompra> detalleFacturaCompraList = detalleFacturaCompraRepository.findAll();
        assertThat(detalleFacturaCompraList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DetalleFacturaCompra.class);
        DetalleFacturaCompra detalleFacturaCompra1 = new DetalleFacturaCompra();
        detalleFacturaCompra1.setId(1L);
        DetalleFacturaCompra detalleFacturaCompra2 = new DetalleFacturaCompra();
        detalleFacturaCompra2.setId(detalleFacturaCompra1.getId());
        assertThat(detalleFacturaCompra1).isEqualTo(detalleFacturaCompra2);
        detalleFacturaCompra2.setId(2L);
        assertThat(detalleFacturaCompra1).isNotEqualTo(detalleFacturaCompra2);
        detalleFacturaCompra1.setId(null);
        assertThat(detalleFacturaCompra1).isNotEqualTo(detalleFacturaCompra2);
    }
}
