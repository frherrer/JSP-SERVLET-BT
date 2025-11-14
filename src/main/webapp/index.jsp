<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Gestión de Estudiantes</title>

<!-- Bootstrap -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">

</head>

<body class="bg-light">

	<div class="container mt-5">
		<div class="card shadow-lg p-4">
			<h2 class="text-center mb-4">Gestión de Estudiantes</h2>

			<div class="d-flex justify-content-center gap-3">

				<button id="btnListar" class="btn btn-primary btn-lg">
					Listar estudiantes</button>

				<button id="btnAgregar" class="btn btn-success btn-lg">
					Agregar estudiante</button>

			</div>
		</div>
	</div>


	<!-- JavaScript integrado en el mismo archivo -->
	<script>
    document.addEventListener("DOMContentLoaded", () => {

        const btnListar = document.getElementById("btnListar");
        const btnAgregar = document.getElementById("btnAgregar");

        btnListar.addEventListener("click", () => {
            window.location.href = "<%=request.getContextPath()%>/estudiantes?accion=listar";
        });

        btnAgregar.addEventListener("click", () => {
            window.location.href = "<%=request.getContextPath()%>/estudiantes?accion=nuevo";
        });

    });
</script>

</body>
</html>

