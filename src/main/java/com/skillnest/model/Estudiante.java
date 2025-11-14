package com.skillnest.model;

public class Estudiante {
	private int id;
	private String nombreCompleto;
	private String correo;
	private String carrera;

	public Estudiante() {
	}

	public Estudiante(int id, String nombreCompleto, String correo, String carrera) {
		this.id = id;
		this.nombreCompleto = nombreCompleto;
		this.correo = correo;
		this.carrera = carrera;
	}

	public Estudiante(String nombreCompleto, String correo, String carrera) {
		this.nombreCompleto = nombreCompleto;
		this.correo = correo;
		this.carrera = carrera;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}
}
