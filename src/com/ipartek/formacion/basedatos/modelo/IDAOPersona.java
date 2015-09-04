package com.ipartek.formacion.basedatos.modelo;

import java.util.ArrayList;

import com.ipartek.formacion.basedatos.bean.Persona;

/**
 * Interfaz especi
 * @author Curso
 *
 */
public interface IDAOPersona extends Persistable {
	
	/**
	 * Listado de todas las personas con nota igual o mayor que 5
	 * @return {@code ArrayList<Persona>}
	 */
	ArrayList<Object> getAprobados();
	
	/**
	 * Listado de todas las personas con nota menor que 5
	 * @return {@code ArrayList<Persona>}
	 */
	ArrayList<Object> getSuspendidos();
	
}
