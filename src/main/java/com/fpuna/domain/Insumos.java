package com.fpuna.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Insumos.
 */
@Entity
@Table(name = "insumos")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Insumos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "marca")
    private String marca;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "stock")
    private Integer stock;

    @OneToMany(mappedBy = "insumos")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DetallePedido> detallePedidos = new HashSet<>();

    @OneToMany(mappedBy = "insumos")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DetalleFacturaCompra> detalleFacturas = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public Insumos marca(String marca) {
        this.marca = marca;
        return this;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Insumos descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public Insumos categoria(String categoria) {
        this.categoria = categoria;
        return this;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public Insumos observaciones(String observaciones) {
        this.observaciones = observaciones;
        return this;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getCodigo() {
        return codigo;
    }

    public Insumos codigo(String codigo) {
        this.codigo = codigo;
        return this;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getStock() {
        return stock;
    }

    public Insumos stock(Integer stock) {
        this.stock = stock;
        return this;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Set<DetallePedido> getDetallePedidos() {
        return detallePedidos;
    }

    public Insumos detallePedidos(Set<DetallePedido> detallePedidos) {
        this.detallePedidos = detallePedidos;
        return this;
    }

    public Insumos addDetallePedido(DetallePedido detallePedido) {
        this.detallePedidos.add(detallePedido);
        detallePedido.setInsumos(this);
        return this;
    }

    public Insumos removeDetallePedido(DetallePedido detallePedido) {
        this.detallePedidos.remove(detallePedido);
        detallePedido.setInsumos(null);
        return this;
    }

    public void setDetallePedidos(Set<DetallePedido> detallePedidos) {
        this.detallePedidos = detallePedidos;
    }

    public Set<DetalleFacturaCompra> getDetalleFacturas() {
        return detalleFacturas;
    }

    public Insumos detalleFacturas(Set<DetalleFacturaCompra> detalleFacturaCompras) {
        this.detalleFacturas = detalleFacturaCompras;
        return this;
    }

    public Insumos addDetalleFactura(DetalleFacturaCompra detalleFacturaCompra) {
        this.detalleFacturas.add(detalleFacturaCompra);
        detalleFacturaCompra.setInsumos(this);
        return this;
    }

    public Insumos removeDetalleFactura(DetalleFacturaCompra detalleFacturaCompra) {
        this.detalleFacturas.remove(detalleFacturaCompra);
        detalleFacturaCompra.setInsumos(null);
        return this;
    }

    public void setDetalleFacturas(Set<DetalleFacturaCompra> detalleFacturaCompras) {
        this.detalleFacturas = detalleFacturaCompras;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Insumos insumos = (Insumos) o;
        if (insumos.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), insumos.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Insumos{" +
            "id=" + getId() +
            ", marca='" + getMarca() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", categoria='" + getCategoria() + "'" +
            ", observaciones='" + getObservaciones() + "'" +
            ", codigo='" + getCodigo() + "'" +
            ", stock='" + getStock() + "'" +
            "}";
    }
}
