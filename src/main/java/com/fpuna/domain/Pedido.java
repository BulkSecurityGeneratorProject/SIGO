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
 * A Pedido.
 */
@Entity
@Table(name = "pedido")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "estado")
    private String estado;

    @ManyToOne
    private Proveedor proveedor;

    @OneToMany(mappedBy = "pedido")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<FacturaCompra> facturaCompras = new HashSet<>();

    @OneToMany(mappedBy = "pedido")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DetallePedido> detallePedidos = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public Pedido cantidad(Integer cantidad) {
        this.cantidad = cantidad;
        return this;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getEstado() {
        return estado;
    }

    public Pedido estado(String estado) {
        this.estado = estado;
        return this;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public Pedido proveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
        return this;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Set<FacturaCompra> getFacturaCompras() {
        return facturaCompras;
    }

    public Pedido facturaCompras(Set<FacturaCompra> facturaCompras) {
        this.facturaCompras = facturaCompras;
        return this;
    }

    public Pedido addFacturaCompra(FacturaCompra facturaCompra) {
        this.facturaCompras.add(facturaCompra);
        facturaCompra.setPedido(this);
        return this;
    }

    public Pedido removeFacturaCompra(FacturaCompra facturaCompra) {
        this.facturaCompras.remove(facturaCompra);
        facturaCompra.setPedido(null);
        return this;
    }

    public void setFacturaCompras(Set<FacturaCompra> facturaCompras) {
        this.facturaCompras = facturaCompras;
    }

    public Set<DetallePedido> getDetallePedidos() {
        return detallePedidos;
    }

    public Pedido detallePedidos(Set<DetallePedido> detallePedidos) {
        this.detallePedidos = detallePedidos;
        return this;
    }

    public Pedido addDetallePedido(DetallePedido detallePedido) {
        this.detallePedidos.add(detallePedido);
        detallePedido.setPedido(this);
        return this;
    }

    public Pedido removeDetallePedido(DetallePedido detallePedido) {
        this.detallePedidos.remove(detallePedido);
        detallePedido.setPedido(null);
        return this;
    }

    public void setDetallePedidos(Set<DetallePedido> detallePedidos) {
        this.detallePedidos = detallePedidos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pedido pedido = (Pedido) o;
        if (pedido.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pedido.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Pedido{" +
            "id=" + getId() +
            ", cantidad='" + getCantidad() + "'" +
            ", estado='" + getEstado() + "'" +
            "}";
    }
}
