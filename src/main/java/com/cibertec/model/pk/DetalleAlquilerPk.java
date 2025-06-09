package com.cibertec.model.pk;

import jakarta.persistence.Embeddable;

@Embeddable
public class DetalleAlquilerPk {

    private int alquilerId;
    private int peliculaId;

    public DetalleAlquilerPk() {}

    public DetalleAlquilerPk(int alquilerId, int peliculaId) {
        this.alquilerId = alquilerId;
        this.peliculaId = peliculaId;
    }

    public int getAlquilerId() {
        return alquilerId;
    }

    public void setAlquilerId(int alquilerId) {
        this.alquilerId = alquilerId;
    }

    public int getPeliculaId() {
        return peliculaId;
    }

    public void setPeliculaId(int peliculaId) {
        this.peliculaId = peliculaId;
    }
}
