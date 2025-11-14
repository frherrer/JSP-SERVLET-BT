package com.skillnest.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
	private static ConexionDB instancia;
	private Connection conexion;

	private final String URL = "jdbc:mysql://localhost:3306/colegio";
	private final String USUARIO = "root";
	private final String CONTRASENA = "Admin123.";

	private ConexionDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
			System.out.println("Conexion DB OK");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public static ConexionDB getInstancia() {
		if (instancia == null) {
			instancia = new ConexionDB();
		}
		return instancia;
	}

	public Connection getConexion() {
		return conexion;
	}
}
