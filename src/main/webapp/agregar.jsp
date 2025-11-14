<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<head>
    <meta charset="UTF-8">
    <title>Registrar Estudiante</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    
</head>
<body class="p-4">
    <div class="container">
        <h2>Registrar Estudiante</h2>
        <form action="<%=request.getContextPath()%>/estudiantes" method="post" onsubmit="return validar();">
            <input type="hidden" name="accion" value="guardar">
            <div class="mb-3">
                <label>Nombre completo</label>
                <input id="nombre" name="nombre" required class="form-control" maxlength="200">
            </div>
            <div class="mb-3">
                <label>Correo</label>
                <input id="correo" name="correo" type="email" required class="form-control" maxlength="150">
            </div>
            <div class="mb-3">
                <label>Carrera</label>
                <input id="carrera" name="carrera" required class="form-control" maxlength="120">
            </div>
            <button type="submit" class="btn btn-primary">Guardar</button>
            <a class="btn btn-secondary" href="<%=request.getContextPath()%>/estudiantes">Volver</a>
        </form>
    </div>
</body>
<script>
        function validar() {
            var nombre = document.getElementById("nombre").value.trim();
            var correo = document.getElementById("correo").value.trim();
            var carrera = document.getElementById("carrera").value.trim();
            if (!nombre || !correo || !carrera) {
                alert("Completa todos los campos.");
                return false;
            }
            if (correo.indexOf('@') < 0 || correo.indexOf('.') < 0) {
                alert("Correo inválido.");
                return false;
            }
            return true;
        }
    </script>
</html>