package com.skillnest.model;

import com.skillnest.util.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO {
	private Connection conn;

	public EstudianteDAO() {
		conn = ConexionDB.getInstancia().getConexion();
	}

	public boolean create(Estudiante e) {
		String sql = "INSERT INTO estudiantes (nombre_completo, correo, carrera) VALUES (?, ?, ?)";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, e.getNombreCompleto());
			ps.setString(2, e.getCorreo());
			ps.setString(3, e.getCarrera());
			int rows = ps.executeUpdate();
			return rows > 0;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public List<Estudiante> readAll() {
		List<Estudiante> lista = new ArrayList<>();
		String sql = "SELECT id, nombre_completo, correo, carrera FROM estudiantes ORDER BY id";
		try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				Estudiante e = new Estudiante(rs.getInt("id"), rs.getString("nombre_completo"), rs.getString("correo"),
						rs.getString("carrera"));
				lista.add(e);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return lista;
	}

	public Estudiante readById(int id) {
		String sql = "SELECT id, nombre_completo, correo, carrera FROM estudiantes WHERE id = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return new Estudiante(rs.getInt("id"), rs.getString("nombre_completo"), rs.getString("correo"),
							rs.getString("carrera"));
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public boolean update(Estudiante e) {
		String sql = "UPDATE estudiantes SET nombre_completo = ?, correo = ?, carrera = ? WHERE id = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, e.getNombreCompleto());
			ps.setString(2, e.getCorreo());
			ps.setString(3, e.getCarrera());
			ps.setInt(4, e.getId());
			return ps.executeUpdate() > 0;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public boolean delete(int id) {
		String sql = "DELETE FROM estudiantes WHERE id = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, id);
			return ps.executeUpdate() > 0;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}
}
