package com.ipartek.formacion.basedatos.modelo;

import java.util.ArrayList;

import com.ipartek.formacion.basedatos.bean.Persona;

public interface IDAOPersona extends Persistable {

	/**
	 * Listado de todas las personas con nota igual mayor que 5
	 * @return
	 */
	ArrayList<Object> getAprobados();
	
	/**
	 * Listado de todas las personas con nota menor que 5
	 * @return
	 */
	ArrayList<Object> getSuspendidos();
	
}
