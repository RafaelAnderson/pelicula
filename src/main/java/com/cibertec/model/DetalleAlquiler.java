package com.cibertec.model;

import com.cibertec.model.pk.DetalleAlquilerPk;
import jakarta.persistence.*;

@Entity
public class DetalleAlquiler {

    @EmbeddedId
    private DetalleAlquilerPk id;

    private int cantidad;

    public DetalleAlquiler() {
    }

    public DetalleAlquiler(DetalleAlquilerPk id, int cantidad) {
        this.id = id;
        this.cantidad = cantidad;
    }

    public DetalleAlquilerPk getId() {
        return id;
    }

    public void setId(DetalleAlquilerPk id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
