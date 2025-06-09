package com.cibertec.dao;

import com.cibertec.dao.control.JPAUtil;
import com.cibertec.model.Cliente;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ClienteDao {

    public void guardar(Cliente cliente) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();
        em.close();
    }

    public List<Cliente> listar() {
        try (EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            return em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
        }
    }
}
