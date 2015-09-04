package com.ipartek.formacion.basedatosProfe.modelo;

import java.util.ArrayList;

import com.ipartek.formacion.basedatosProfe.bean.Persona;

public interface IDAOPersona extends Persistable{

	/**
	 * Listado de todas las personas con nota igual o mayor que 5
	 * @return
	 */
	ArrayList<Object> getAprobados();
	
	/**
	 * Listado de todas las persona con nota menor que 5
	 * @return
	 */
	ArrayList<Object> getSuspendidos();
	
}
