package com.ipartek.formacion.basedatos.bean;

import java.sql.Timestamp;

public class Persona {

	
	private int id;
	private String nombre;
	private float nota;
	private String telefono;
	private Timestamp fecha;
	
	/**
	 * @param nombre
	 */
	public Persona(String nombre) {
		super();
		this.id=-1;
		this.nombre = nombre;
		this.fecha=null;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the nota
	 */
	public float getNota() {
		return nota;
	}

	/**
	 * @param nota the nota to set
	 */
	public void setNota(float nota) {
		this.nota = nota;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the fecha
	 */
	public Timestamp getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + ", nota=" + nota
				+ ", telefono=" + telefono + ", fecha=" + fecha + "]";
	}
	
	
	
}
