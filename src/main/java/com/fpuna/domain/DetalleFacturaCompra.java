package com.fpuna.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DetalleFacturaCompra.
 */
@Entity
@Table(name = "detalle_factura_compra")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DetalleFacturaCompra implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "cantidad_recibida")
    private Integer cantidadRecibida;

    @ManyToOne
    private Insumos insumos;

    @ManyToOne
    private FacturaCompra facturaCompra;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidadRecibida() {
        return cantidadRecibida;
    }

    public DetalleFacturaCompra cantidadRecibida(Integer cantidadRecibida) {
        this.cantidadRecibida = cantidadRecibida;
        return this;
    }

    public void setCantidadRecibida(Integer cantidadRecibida) {
        this.cantidadRecibida = cantidadRecibida;
    }

    public Insumos getInsumos() {
        return insumos;
    }

    public DetalleFacturaCompra insumos(Insumos insumos) {
        this.insumos = insumos;
        return this;
    }

    public void setInsumos(Insumos insumos) {
        this.insumos = insumos;
    }

    public FacturaCompra getFacturaCompra() {
        return facturaCompra;
    }

    public DetalleFacturaCompra facturaCompra(FacturaCompra facturaCompra) {
        this.facturaCompra = facturaCompra;
        return this;
    }

    public void setFacturaCompra(FacturaCompra facturaCompra) {
        this.facturaCompra = facturaCompra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DetalleFacturaCompra detalleFacturaCompra = (DetalleFacturaCompra) o;
        if (detalleFacturaCompra.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), detalleFacturaCompra.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DetalleFacturaCompra{" +
            "id=" + getId() +
            ", cantidadRecibida='" + getCantidadRecibida() + "'" +
            "}";
    }
}
