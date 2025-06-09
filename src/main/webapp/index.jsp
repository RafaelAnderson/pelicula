<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <title>Registrar Alquiler</title>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
            crossorigin="anonymous"/>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"/>
</head>
<body>

<div class="container">
    <h1>Alquilar película</h1>

    <c:if test="${ not empty error }">
        <div class="alert alert-danger" role="alert">
                ${error}
        </div>
    </c:if>

    <form action="Alquiler?opcion=guardar" method="post" class="needs-validation" novalidate>
        <div class="row">
            <!-- Sección cliente -->
            <div class="col-md-3">
                <label>Cliente</label>
                <select id="clienteSelect" name="clienteId" class="form-select" required>
                    <c:forEach var="c" items="${clientes}">
                        <option value="${c.id}">${c.nombre}</option>
                    </c:forEach>
                </select>
            </div>

            <!-- Sección pelicula -->
            <div class="col-md-3">
                <label>Pelicula</label>
                <select id="peliculaSelect" name="peliculaId" class="form-select" required>
                    <c:forEach var="p" items="${peliculas}">
                        <option value="${p.id}">${p.titulo}</option>
                    </c:forEach>
                </select>
            </div>

            <!-- Sección cantidad -->
            <div class="col-md-3">
                <label>Cantidad</label>
                <input type="number" class="form-control" name="cantidad" min="1" required>
            </div>

            <!-- Sección precio alquiler -->
            <div class="col-md-3">
                <label>Precio</label>
                <input type="number" class="form-control" name="precio" value="10" readonly>
            </div>
        </div>

        <!-- Sección total -->
        <div class="row">
            <div class="col-md-3">
                <label>Total (S/)</label>
                <input type="text" id="total" name="total" class="form-control" readonly>
            </div>
        </div>

        <div class="row">
            <div class="mt-3 col-md-3 row">
                <button type="submit" class="btn btn-outline-primary">Registrar Alquiler</button>
            </div>
        </div>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/jquery.validate.min.js" ></script>

<script type="text/javascript">
    const precioPorPelicula = 10;

    $(document).ready(function () {
        $("input[name='cantidad']").on('input', function () {
            const cantidad = parseInt($(this).val()) || 0;
            const total = cantidad * precioPorPelicula;
            $("#total").val(total);
        });
    });
</script>

<script type="text/javascript" >
    (function () {
        'use strict'
        var forms = document.querySelectorAll('.needs-validation')
        Array.prototype.slice.call(forms)
            .forEach(function (form) {
                form.addEventListener('submit', function (event) {
                    if (!form.checkValidity()) {
                        event.preventDefault()
                        event.stopPropagation()
                    }
                    form.classList.add('was-validated')
                }, false)
            })
    })();
</script>
</body>
</html>
