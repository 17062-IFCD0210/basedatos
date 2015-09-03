package com.ipartek.formacion.basedatos.bean;

import java.sql.Timestamp;

public class Persona {
	
	private int id;
	private String nombre;
	private float nota;
	private Timestamp fecha;
	private String telefono;
	
	
	//Constructor basico
	/**
	 * @param nombre
	 */
	public Persona(String nombre) {
		super();
		this.id = -1;
		this.nombre = nombre;
		this.fecha = null;
	}


	//getters y setters
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public float getNota() {
		return nota;
	}


	public void setNota(float nota) {
		this.nota = nota;
	}


	public Timestamp getFecha() {
		return fecha;
	}


	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + ", nota=" + nota
				+ ", fecha=" + fecha + ", telefono=" + telefono + "]";
	}
	

	
	
	

}
