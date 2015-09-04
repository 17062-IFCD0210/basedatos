package com.ipartek.formacion.basedatos.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase especializada en trabajar con la BBDD
 * La usaran los DAOs para abrir y cerrar conexiones
 * @author Lara
 *
 */

public class DataBaseHelper {
	
	//parametros configuracion BBDD
	static final public String DRIVER    = "com.mysql.jdbc.Driver";
	static final public String SERVER    = "localhost";
	static final public String DATA_BASE = "skalada";
	static final public String USER      = "root";
	static final public String PASS      = "";
	
	//conexion, lo necesitamos para crear los statments y luego ejecutar la consulta
	private static Connection con;
	
	/**
	 * Retornar la conexion abierta
	 * implementa un patron singleton
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception{
		
		if ( con == null ){
			Class.forName(DRIVER);
			//TODO cambiar parametro
			con = DriverManager.getConnection ("jdbc:mysql://localhost/skalada","root", "");
		}
		return con;
	}
	
	/**
	 * Cierra la conexion, cuidado porque al cerrar una conexion con el metodo .close() no la pone a NULL
	 * @return
	 */
	public static boolean closeConnection(){
		boolean resul = false;
		try{
			con.close();
			con=null;
			resul = true;
		}catch ( SQLException e){
			con=null;
			e.printStackTrace();
			resul = false;
		}
		return resul;
	}
	
	/**
	 * Crea la BBDD, ejecutando un Script
	 */
	void crear(){
		
	}
	
	/**
	 * Elimina la BBDD con sentencia DROP
	 */
	void eliminar(){
		
	}
	
	/**
	 * Crea las tablas:
	 * <ol>
	 * 	<li>test</li>
	 * </ol>
	 */
	void crearTablas(){
		
	}
	
	/**
	 * Insertar en las tablas un juego de datos para testear 
	 */
	void insertarDatosPrueba(){
		
	}

	
}