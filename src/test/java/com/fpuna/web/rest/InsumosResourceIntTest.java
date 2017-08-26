package com.fpuna.web.rest;

import com.fpuna.SigoApp;

import com.fpuna.domain.Insumos;
import com.fpuna.repository.InsumosRepository;
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
 * Test class for the InsumosResource REST controller.
 *
 * @see InsumosResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SigoApp.class)
public class InsumosResourceIntTest {

    private static final String DEFAULT_MARCA = "AAAAAAAAAA";
    private static final String UPDATED_MARCA = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    private static final String DEFAULT_CATEGORIA = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORIA = "BBBBBBBBBB";

    private static final String DEFAULT_OBSERVACIONES = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVACIONES = "BBBBBBBBBB";

    private static final String DEFAULT_CODIGO = "AAAAAAAAAA";
    private static final String UPDATED_CODIGO = "BBBBBBBBBB";

    private static final Integer DEFAULT_STOCK = 1;
    private static final Integer UPDATED_STOCK = 2;

    @Autowired
    private InsumosRepository insumosRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restInsumosMockMvc;

    private Insumos insumos;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        InsumosResource insumosResource = new InsumosResource(insumosRepository);
        this.restInsumosMockMvc = MockMvcBuilders.standaloneSetup(insumosResource)
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
    public static Insumos createEntity(EntityManager em) {
        Insumos insumos = new Insumos()
            .marca(DEFAULT_MARCA)
            .descripcion(DEFAULT_DESCRIPCION)
            .categoria(DEFAULT_CATEGORIA)
            .observaciones(DEFAULT_OBSERVACIONES)
            .codigo(DEFAULT_CODIGO)
            .stock(DEFAULT_STOCK);
        return insumos;
    }

    @Before
    public void initTest() {
        insumos = createEntity(em);
    }

    @Test
    @Transactional
    public void createInsumos() throws Exception {
        int databaseSizeBeforeCreate = insumosRepository.findAll().size();

        // Create the Insumos
        restInsumosMockMvc.perform(post("/api/insumos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(insumos)))
            .andExpect(status().isCreated());

        // Validate the Insumos in the database
        List<Insumos> insumosList = insumosRepository.findAll();
        assertThat(insumosList).hasSize(databaseSizeBeforeCreate + 1);
        Insumos testInsumos = insumosList.get(insumosList.size() - 1);
        assertThat(testInsumos.getMarca()).isEqualTo(DEFAULT_MARCA);
        assertThat(testInsumos.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testInsumos.getCategoria()).isEqualTo(DEFAULT_CATEGORIA);
        assertThat(testInsumos.getObservaciones()).isEqualTo(DEFAULT_OBSERVACIONES);
        assertThat(testInsumos.getCodigo()).isEqualTo(DEFAULT_CODIGO);
        assertThat(testInsumos.getStock()).isEqualTo(DEFAULT_STOCK);
    }

    @Test
    @Transactional
    public void createInsumosWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = insumosRepository.findAll().size();

        // Create the Insumos with an existing ID
        insumos.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInsumosMockMvc.perform(post("/api/insumos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(insumos)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Insumos> insumosList = insumosRepository.findAll();
        assertThat(insumosList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllInsumos() throws Exception {
        // Initialize the database
        insumosRepository.saveAndFlush(insumos);

        // Get all the insumosList
        restInsumosMockMvc.perform(get("/api/insumos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(insumos.getId().intValue())))
            .andExpect(jsonPath("$.[*].marca").value(hasItem(DEFAULT_MARCA.toString())))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION.toString())))
            .andExpect(jsonPath("$.[*].categoria").value(hasItem(DEFAULT_CATEGORIA.toString())))
            .andExpect(jsonPath("$.[*].observaciones").value(hasItem(DEFAULT_OBSERVACIONES.toString())))
            .andExpect(jsonPath("$.[*].codigo").value(hasItem(DEFAULT_CODIGO.toString())))
            .andExpect(jsonPath("$.[*].stock").value(hasItem(DEFAULT_STOCK)));
    }

    @Test
    @Transactional
    public void getInsumos() throws Exception {
        // Initialize the database
        insumosRepository.saveAndFlush(insumos);

        // Get the insumos
        restInsumosMockMvc.perform(get("/api/insumos/{id}", insumos.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(insumos.getId().intValue()))
            .andExpect(jsonPath("$.marca").value(DEFAULT_MARCA.toString()))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION.toString()))
            .andExpect(jsonPath("$.categoria").value(DEFAULT_CATEGORIA.toString()))
            .andExpect(jsonPath("$.observaciones").value(DEFAULT_OBSERVACIONES.toString()))
            .andExpect(jsonPath("$.codigo").value(DEFAULT_CODIGO.toString()))
            .andExpect(jsonPath("$.stock").value(DEFAULT_STOCK));
    }

    @Test
    @Transactional
    public void getNonExistingInsumos() throws Exception {
        // Get the insumos
        restInsumosMockMvc.perform(get("/api/insumos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInsumos() throws Exception {
        // Initialize the database
        insumosRepository.saveAndFlush(insumos);
        int databaseSizeBeforeUpdate = insumosRepository.findAll().size();

        // Update the insumos
        Insumos updatedInsumos = insumosRepository.findOne(insumos.getId());
        updatedInsumos
            .marca(UPDATED_MARCA)
            .descripcion(UPDATED_DESCRIPCION)
            .categoria(UPDATED_CATEGORIA)
            .observaciones(UPDATED_OBSERVACIONES)
            .codigo(UPDATED_CODIGO)
            .stock(UPDATED_STOCK);

        restInsumosMockMvc.perform(put("/api/insumos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedInsumos)))
            .andExpect(status().isOk());

        // Validate the Insumos in the database
        List<Insumos> insumosList = insumosRepository.findAll();
        assertThat(insumosList).hasSize(databaseSizeBeforeUpdate);
        Insumos testInsumos = insumosList.get(insumosList.size() - 1);
        assertThat(testInsumos.getMarca()).isEqualTo(UPDATED_MARCA);
        assertThat(testInsumos.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testInsumos.getCategoria()).isEqualTo(UPDATED_CATEGORIA);
        assertThat(testInsumos.getObservaciones()).isEqualTo(UPDATED_OBSERVACIONES);
        assertThat(testInsumos.getCodigo()).isEqualTo(UPDATED_CODIGO);
        assertThat(testInsumos.getStock()).isEqualTo(UPDATED_STOCK);
    }

    @Test
    @Transactional
    public void updateNonExistingInsumos() throws Exception {
        int databaseSizeBeforeUpdate = insumosRepository.findAll().size();

        // Create the Insumos

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restInsumosMockMvc.perform(put("/api/insumos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(insumos)))
            .andExpect(status().isCreated());

        // Validate the Insumos in the database
        List<Insumos> insumosList = insumosRepository.findAll();
        assertThat(insumosList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteInsumos() throws Exception {
        // Initialize the database
        insumosRepository.saveAndFlush(insumos);
        int databaseSizeBeforeDelete = insumosRepository.findAll().size();

        // Get the insumos
        restInsumosMockMvc.perform(delete("/api/insumos/{id}", insumos.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Insumos> insumosList = insumosRepository.findAll();
        assertThat(insumosList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Insumos.class);
        Insumos insumos1 = new Insumos();
        insumos1.setId(1L);
        Insumos insumos2 = new Insumos();
        insumos2.setId(insumos1.getId());
        assertThat(insumos1).isEqualTo(insumos2);
        insumos2.setId(2L);
        assertThat(insumos1).isNotEqualTo(insumos2);
        insumos1.setId(null);
        assertThat(insumos1).isNotEqualTo(insumos2);
    }
}
