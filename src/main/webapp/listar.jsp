<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.skillnest.model.Estudiante"%>
<%
List<Estudiante> lista = (List<Estudiante>) request.getAttribute("listaEstudiantes");
String msg = (String) request.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista de Estudiantes</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="p-4">
	<div class="container">
		<h1>Gestión de Estudiantes</h1>

		<%
		if (msg != null) {
		%>
		<div class="alert alert-info"><%=msg%></div>
		<%
		}
		%>

		<div class="mb-3">
			<a class="btn btn-success"
				href="<%=request.getContextPath()%>/estudiantes?accion=agregar">Registrar
				Estudiante</a>
		</div>

		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<th>ID</th>
					<th>Nombre completo</th>
					<th>Correo</th>
					<th>Carrera</th>
					<th>Acciones</th>
				</tr>
			</thead>
			<tbody>
				<%
				if (lista != null && !lista.isEmpty()) {
					for (Estudiante e : lista) {
				%>
				<tr>
					<td><%=e.getId()%></td>
					<td><%=e.getNombreCompleto()%></td>
					<td><%=e.getCorreo()%></td>
					<td><%=e.getCarrera()%></td>
					<td><a class="btn btn-primary btn-sm"
						href="<%=request.getContextPath()%>/estudiantes?accion=editar&id=<%=e.getId()%>">Editar</a>
						<a class="btn btn-danger btn-sm"
						href="<%=request.getContextPath()%>/estudiantes?accion=eliminar&id=<%=e.getId()%>"
						onclick="return confirm('¿Eliminar estudiante ID <%=e.getId()%>?');">Eliminar</a>
					</td>
				</tr>
				<%
				}
				} else {
				%>
				<tr>
					<td colspan="5" class="text-center">No hay estudiantes
						registrados.</td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</div>
</body>
</html>