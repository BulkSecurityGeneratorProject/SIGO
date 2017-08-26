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
 * A Proveedor.
 */
@Entity
@Table(name = "proveedor")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Proveedor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "razon_social")
    private String razonSocial;

    @Column(name = "ruc")
    private String ruc;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "correoelectronico")
    private String correoelectronico;

    @Column(name = "telefono")
    private String telefono;

    @OneToMany(mappedBy = "proveedor")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Pedido> pedidos = new HashSet<>();

    @OneToMany(mappedBy = "proveedor")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<FacturaCompra> facturaCompras = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public Proveedor razonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
        return this;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRuc() {
        return ruc;
    }

    public Proveedor ruc(String ruc) {
        this.ruc = ruc;
        return this;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getDireccion() {
        return direccion;
    }

    public Proveedor direccion(String direccion) {
        this.direccion = direccion;
        return this;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreoelectronico() {
        return correoelectronico;
    }

    public Proveedor correoelectronico(String correoelectronico) {
        this.correoelectronico = correoelectronico;
        return this;
    }

    public void setCorreoelectronico(String correoelectronico) {
        this.correoelectronico = correoelectronico;
    }

    public String getTelefono() {
        return telefono;
    }

    public Proveedor telefono(String telefono) {
        this.telefono = telefono;
        return this;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Set<Pedido> getPedidos() {
        return pedidos;
    }

    public Proveedor pedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
        return this;
    }

    public Proveedor addPedido(Pedido pedido) {
        this.pedidos.add(pedido);
        pedido.setProveedor(this);
        return this;
    }

    public Proveedor removePedido(Pedido pedido) {
        this.pedidos.remove(pedido);
        pedido.setProveedor(null);
        return this;
    }

    public void setPedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Set<FacturaCompra> getFacturaCompras() {
        return facturaCompras;
    }

    public Proveedor facturaCompras(Set<FacturaCompra> facturaCompras) {
        this.facturaCompras = facturaCompras;
        return this;
    }

    public Proveedor addFacturaCompra(FacturaCompra facturaCompra) {
        this.facturaCompras.add(facturaCompra);
        facturaCompra.setProveedor(this);
        return this;
    }

    public Proveedor removeFacturaCompra(FacturaCompra facturaCompra) {
        this.facturaCompras.remove(facturaCompra);
        facturaCompra.setProveedor(null);
        return this;
    }

    public void setFacturaCompras(Set<FacturaCompra> facturaCompras) {
        this.facturaCompras = facturaCompras;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Proveedor proveedor = (Proveedor) o;
        if (proveedor.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), proveedor.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Proveedor{" +
            "id=" + getId() +
            ", razonSocial='" + getRazonSocial() + "'" +
            ", ruc='" + getRuc() + "'" +
            ", direccion='" + getDireccion() + "'" +
            ", correoelectronico='" + getCorreoelectronico() + "'" +
            ", telefono='" + getTelefono() + "'" +
            "}";
    }
}
