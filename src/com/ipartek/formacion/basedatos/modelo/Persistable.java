package com.ipartek.formacion.basedatos.modelo;

import java.util.ArrayList;

/**
 * Interfaz para permitir guardar, recuperar, modificar y eliminar Beans Soporta
 * las operaciones basicas de CRUD
 *
 * @author Curso
 *
 */
public interface Persistable {

	/**
	 * Persiste el objeto y lo guarda
	 *
	 * @param o
	 *            {@code Object} a guardar
	 * @return {@code int} Identificador del objeto guardado, -1 en caso de
	 *         error
	 */
	int save(Object o);

	/**
	 * Recupera un objeto por su identificador
	 *
	 * @param id
	 *            {@code int} identificador del objeto a recuperar
	 * @return {@code Object} objeto recuperado o null en caso contrario
	 */
	Object getById(int id);

	/**
	 * Recupera una coleccion de objetos
	 *
	 * @return {@code ArrrayList<Object>} Coleccion de objetos, si no existen
	 *         coleccion vacia.
	 */
	ArrayList<Object> getAll();

	/**
	 * Modifica un objeto el cual debe tener un identificador definido
	 *
	 * @param o
	 *            {@code Object} a modificar
	 * @return true si se modifica correctamente, false en caso contrario
	 */
	boolean update(Object o);

	/**
	 * Eliminar un objeto por su identificador
	 *
	 * @param id
	 *            {@code int} identificador del objeto a eliminar
	 * @return true si se elimina correctamente, false en caso contrario
	 */
	boolean delete(int id);

}