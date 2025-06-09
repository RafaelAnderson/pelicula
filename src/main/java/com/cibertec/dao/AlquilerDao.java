package com.cibertec.dao;

import com.cibertec.dao.control.JPAUtil;
import com.cibertec.model.Alquiler;
import com.cibertec.model.DetalleAlquiler;
import com.cibertec.model.Pelicula;
import com.cibertec.model.pk.DetalleAlquilerPk;
import jakarta.persistence.EntityManager;

public class AlquilerDao {

    public void insertarConDetalle(Alquiler alquiler, Pelicula pelicula, int cantidad) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager(); // configura tu EntityManager

        try {
            em.getTransaction().begin();
            em.persist(alquiler);
            em.flush();

            DetalleAlquiler detalle = new DetalleAlquiler();
            DetalleAlquilerPk pk = new DetalleAlquilerPk();
            pk.setAlquilerId(alquiler.getId().intValue());
            pk.setPeliculaId(pelicula.getId().intValue());
            detalle.setId(pk);
            detalle.setCantidad(cantidad);

            em.persist(detalle);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
