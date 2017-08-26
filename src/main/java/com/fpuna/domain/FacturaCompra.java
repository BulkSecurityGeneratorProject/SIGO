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
 * A FacturaCompra.
 */
@Entity
@Table(name = "factura_compra")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FacturaCompra implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "costo")
    private Integer costo;

    @ManyToOne
    private Proveedor proveedor;

    @ManyToOne
    private Pedido pedido;

    @OneToMany(mappedBy = "facturaCompra")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DetalleFacturaCompra> detalleFacturas = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCosto() {
        return costo;
    }

    public FacturaCompra costo(Integer costo) {
        this.costo = costo;
        return this;
    }

    public void setCosto(Integer costo) {
        this.costo = costo;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public FacturaCompra proveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
        return this;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public FacturaCompra pedido(Pedido pedido) {
        this.pedido = pedido;
        return this;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Set<DetalleFacturaCompra> getDetalleFacturas() {
        return detalleFacturas;
    }

    public FacturaCompra detalleFacturas(Set<DetalleFacturaCompra> detalleFacturaCompras) {
        this.detalleFacturas = detalleFacturaCompras;
        return this;
    }

    public FacturaCompra addDetalleFactura(DetalleFacturaCompra detalleFacturaCompra) {
        this.detalleFacturas.add(detalleFacturaCompra);
        detalleFacturaCompra.setFacturaCompra(this);
        return this;
    }

    public FacturaCompra removeDetalleFactura(DetalleFacturaCompra detalleFacturaCompra) {
        this.detalleFacturas.remove(detalleFacturaCompra);
        detalleFacturaCompra.setFacturaCompra(null);
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
        FacturaCompra facturaCompra = (FacturaCompra) o;
        if (facturaCompra.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), facturaCompra.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FacturaCompra{" +
            "id=" + getId() +
            ", costo='" + getCosto() + "'" +
            "}";
    }
}
