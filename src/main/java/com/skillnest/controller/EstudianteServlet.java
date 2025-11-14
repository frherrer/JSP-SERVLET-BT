package com.skillnest.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import com.skillnest.model.EstudianteDAO;
import com.skillnest.model.Estudiante;

/**
 * Servlet implementation class EstudianteServlet
 * 
 */

@WebServlet("/estudiantes")
public class EstudianteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EstudianteDAO dao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public EstudianteServlet() {
		super();
		dao = new EstudianteDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String accion = request.getParameter("accion");
		if (accion == null || accion.isEmpty())
			accion = "listar";

		switch (accion) {
		case "agregar":
			// muestra formulario agregar
			request.getRequestDispatcher("/agregar.jsp").forward(request, response);
			break;
		case "editar":
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				Estudiante e = dao.readById(id);
				if (e != null) {
					request.setAttribute("estudiante", e);
					request.getRequestDispatcher("/editar.jsp").forward(request, response);
				} else {
					request.setAttribute("msg", "Estudiante no encontrado.");
					listar(request, response);
				}
			} catch (NumberFormatException ex) {
				request.setAttribute("msg", "ID inválido.");
				listar(request, response);
			}
			break;
		case "eliminar":
			// puede ser GET (confirmación) o directamente eliminar si se pasa id
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				boolean ok = dao.delete(id);
				request.setAttribute("msg", ok ? "Estudiante eliminado." : "Error al eliminar.");
			} catch (Exception ex) {
				request.setAttribute("msg", "ID inválido o error.");
			}
			listar(request, response);
			break;
		case "listar":
		default:
			listar(request, response);
		}
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Estudiante> lista = dao.readAll();
		request.setAttribute("listaEstudiantes", lista);
		request.getRequestDispatcher("/listar.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String accion = request.getParameter("accion");
		if (accion == null)
			accion = "";

		switch (accion) {
		case "guardar": // crear
			String nombre = request.getParameter("nombre");
			String correo = request.getParameter("correo");
			String carrera = request.getParameter("carrera");
			if (esValido(nombre, correo, carrera)) {
				Estudiante nuevo = new Estudiante(nombre.trim(), correo.trim(), carrera.trim());
				boolean ok = dao.create(nuevo);
				request.setAttribute("msg", ok ? "Estudiante creado correctamente." : "Error al crear estudiante.");
			} else {
				request.setAttribute("msg", "Datos inválidos. Verifica los campos.");
			}
			listar(request, response);
			break;

		case "actualizar": // editar
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				String n = request.getParameter("nombre");
				String c = request.getParameter("correo");
				String ca = request.getParameter("carrera");
				if (esValido(n, c, ca)) {
					Estudiante est = new Estudiante(id, n.trim(), c.trim(), ca.trim());
					boolean ok2 = dao.update(est);
					request.setAttribute("msg", ok2 ? "Estudiante actualizado." : "Error al actualizar.");
				} else {
					request.setAttribute("msg", "Datos inválidos. Verifica los campos.");
				}
			} catch (NumberFormatException ex) {
				request.setAttribute("msg", "ID inválido.");
			}
			listar(request, response);
			break;

		case "eliminar": // delete por POST
			try {
				int idDel = Integer.parseInt(request.getParameter("id"));
				boolean ok3 = dao.delete(idDel);
				request.setAttribute("msg", ok3 ? "Estudiante eliminado." : "Error al eliminar.");
			} catch (NumberFormatException ex) {
				request.setAttribute("msg", "ID inválido.");
			}
			listar(request, response);
			break;

		default:
			response.sendRedirect(request.getContextPath() + "/estudiantes");
		}
	}

	private boolean esValido(String nombre, String correo, String carrera) {
		if (nombre == null || correo == null || carrera == null)
			return false;
		if (nombre.trim().isEmpty() || correo.trim().isEmpty() || carrera.trim().isEmpty())
			return false;
		// validación básica correo (muy simple)
		return correo.contains("@") && correo.contains(".");
	}

}
