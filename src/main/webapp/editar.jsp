<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.skillnest.model.Estudiante"%>
<%
Estudiante e = (Estudiante) request.getAttribute("estudiante");
if (e == null) {
	response.sendRedirect(request.getContextPath() + "/estudiantes");
	return;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editar Estudiante</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
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
</head>
<body class="p-4">
	<div class="container">
		<h2>Editar Estudiante</h2>
		<form action="<%=request.getContextPath()%>/estudiantes" method="post"
			onsubmit="return validar();">
			<input type="hidden" name="accion" value="actualizar"> <input
				type="hidden" name="id" value="<%=e.getId()%>">
			<div class="mb-3">
				<label>Nombre completo</label> <input id="nombre" name="nombre"
					required class="form-control" maxlength="200"
					value="<%=e.getNombreCompleto()%>">
			</div>
			<div class="mb-3">
				<label>Correo</label> <input id="correo" name="correo" type="email"
					required class="form-control" maxlength="150"
					value="<%=e.getCorreo()%>">
			</div>
			<div class="mb-3">
				<label>Carrera</label> <input id="carrera" name="carrera" required
					class="form-control" maxlength="120" value="<%=e.getCarrera()%>">
			</div>
			<button type="submit" class="btn btn-primary">Actualizar</button>
			<a class="btn btn-secondary"
				href="<%=request.getContextPath()%>/estudiantes">Volver</a>
		</form>
	</div>
</body>
</html>