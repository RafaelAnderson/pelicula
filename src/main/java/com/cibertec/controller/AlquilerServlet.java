package com.cibertec.controller;

import com.cibertec.dao.AlquilerDao;
import com.cibertec.dao.ClienteDao;
import com.cibertec.dao.PeliculaDao;
import com.cibertec.enums.EstadoAlquiler;
import com.cibertec.model.Alquiler;
import com.cibertec.model.Cliente;
import com.cibertec.model.Pelicula;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "Alquiler", urlPatterns = { "/Alquiler" })
public class AlquilerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    ClienteDao clienteDao = new ClienteDao();
    PeliculaDao peliculaDao = new PeliculaDao();

    public AlquilerServlet() {
        super();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cliente> clientes = clienteDao.listar();
        List<Pelicula> peliculas = peliculaDao.listar();
        request.setAttribute("clientes", clientes);
        request.setAttribute("peliculas", peliculas);

        String opcion = "";
        if (request.getParameter("opcion") != null) opcion = request.getParameter("opcion");
        if (opcion.equals("guardar")) {
            this.guardar(request, response);
        } else {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    protected void guardar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long idCliente = Long.parseLong(request.getParameter("clienteId"));
            Long idPelicula = Long.parseLong(request.getParameter("peliculaId"));
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));
            int total = Integer.parseInt(request.getParameter("total"));

            Cliente cliente = new Cliente(idCliente, "", "");
            Pelicula pelicula = peliculaDao.buscarPorId(idPelicula);

            if (pelicula == null) {
                request.setAttribute("error", "Película no encontrada.");
                return;
            } else if (pelicula.getStock() < cantidad) {
                request.setAttribute("error", "Stock insuficiente. Disponible: " + pelicula.getStock());
                request.getRequestDispatcher("index.jsp").forward(request, response);
                return;
            } else {
                Alquiler alquiler = new Alquiler();
                alquiler.setCliente(cliente);
                alquiler.setFecha(LocalDate.now());
                alquiler.setTotal(total);
                alquiler.setEstado(EstadoAlquiler.ACTIVO);

                AlquilerDao alquilerDao = new AlquilerDao();
                alquilerDao.insertarConDetalle(alquiler, pelicula, cantidad);

                pelicula.setStock(pelicula.getStock() - cantidad);
                peliculaDao.actualizar(pelicula);

                request.setAttribute("mensaje", "Alquiler registrado correctamente.");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }

        } catch (NumberFormatException e) {
            request.setAttribute("error", "Datos de entrada inválidos. Asegúrate de que todos los campos numéricos sean válidos.");
            System.err.println("NumberFormatException in doPost: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            request.setAttribute("error", "Error inesperado al procesar el alquiler: " + e.getMessage());
            System.err.println("General Exception in doPost: " + e.getMessage());
            e.printStackTrace();
        }

        List<Cliente> clientes = clienteDao.listar();
        List<Pelicula> peliculas = peliculaDao.listar();
        request.setAttribute("clientes", clientes);
        request.setAttribute("peliculas", peliculas);

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
