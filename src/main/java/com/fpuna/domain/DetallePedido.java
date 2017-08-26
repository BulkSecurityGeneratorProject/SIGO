package com.fpuna.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DetallePedido.
 */
@Entity
@Table(name = "detalle_pedido")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DetallePedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "cantidad_pedida")
    private Integer cantidadPedida;

    @ManyToOne
    private Pedido pedido;

    @ManyToOne
    private Insumos insumos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidadPedida() {
        return cantidadPedida;
    }

    public DetallePedido cantidadPedida(Integer cantidadPedida) {
        this.cantidadPedida = cantidadPedida;
        return this;
    }

    public void setCantidadPedida(Integer cantidadPedida) {
        this.cantidadPedida = cantidadPedida;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public DetallePedido pedido(Pedido pedido) {
        this.pedido = pedido;
        return this;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Insumos getInsumos() {
        return insumos;
    }

    public DetallePedido insumos(Insumos insumos) {
        this.insumos = insumos;
        return this;
    }

    public void setInsumos(Insumos insumos) {
        this.insumos = insumos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DetallePedido detallePedido = (DetallePedido) o;
        if (detallePedido.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), detallePedido.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DetallePedido{" +
            "id=" + getId() +
            ", cantidadPedida='" + getCantidadPedida() + "'" +
            "}";
    }
}
