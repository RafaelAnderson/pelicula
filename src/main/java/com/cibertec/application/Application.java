package com.cibertec.application;

import com.cibertec.dao.ClienteDao;
import com.cibertec.dao.PeliculaDao;
import com.cibertec.model.Cliente;
import com.cibertec.model.Pelicula;

public class Application {
    public static void main(String[] args) {
        ClienteDao clienteDao = new ClienteDao();
        clienteDao.guardar(new Cliente("Rafael Ponte", "rafaelponte@gmail.com"));
        clienteDao.guardar(new Cliente("John Doe", "johndoe@gmail.com"));
        clienteDao.guardar(new Cliente("Fredd Farias", "fredd_farias@gmail.com"));

        PeliculaDao peliculaDao = new PeliculaDao();
        peliculaDao.guardar(new Pelicula("La monja", "Terror", 5));
        peliculaDao.guardar(new Pelicula("Marvel Avengers Infinitive War", "Ciencia ficción", 10));
        peliculaDao.guardar(new Pelicula("Ratatouille", "Animación", 6));
    }
}
