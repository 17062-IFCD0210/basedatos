package com.ipartek.formacion.basedatosProfe.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ipartek.formacion.basedatosProfe.bean.Persona;

/**
 * Clase especializada en mapear una {@code Persona contra la Bse de Datos
 * Dispone de los métodos básicos para realizar las operaciones de CRUD (recuperar una Persona por su Id, modificar, eliminar
 */

public class DAOPersona implements IDAOPersona{

	/**
	 * Recupera todas las Personas
	 * @return {@code ArrayLiust<Persona>} listado Personas
	 */
	@Override
	public ArrayList<Object> getAll(){
		
		ArrayList<Object> resul = new ArrayList<Object>();
		try{
			Connection con = DataBaseHelper.getConnection();
			Statement st = con.createStatement(); 
	    	String sql = "SELECT * FROM `test`";
	    	ResultSet rs = st.executeQuery (sql);
	    	
	    	//mapeo resultSet => ArrayList<Persona>
	    	while(rs.next()){
	    		resul.add(mapear(rs));
	    	}	
	    	
	    	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseHelper.closeConnection();
		}
		
		return resul;
		
	}

	@Override
	public int save(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getById(int id) {
		
		Object resul = new Object();
		
		try{
			Connection con = DataBaseHelper.getConnection();
			Statement st = con.createStatement(); 
	    	String sql = "SELECT * FROM `test` WHERE id=" + id;
	    	ResultSet rs = st.executeQuery (sql);
	    	
	    	//mapeo resultSet => Persona
	    	while(rs.next()){
	    		resul = mapear(rs);
	    	}	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseHelper.closeConnection();
		}
		
		return resul;
	}

	@Override
	public boolean update(Object o) {
		
		
		boolean modificado = false;
		try{
			//TODO llamar modelo para inserción
			Class.forName("com.mysql.jdbc.Driver");
	        Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/skalada","root", "");
			
	        Statement st = conexion.createStatement();
	        String sql = ""; //"UPDATE `test` SET `nombre`='" + pNombre + "',`nota`=" + pNota + ",`telefono`='" + pTelefono + "' WHERE  `id`=" + pID + ";";
	        
	        if (st.executeUpdate(sql) != 1){ //Si es diferente de 1 es que no se ha insertado ese único registro
	        	throw new Exception("No se ha realizado la inserción: " + sql);
	        }
	        
	        conexion.close();
	        modificado = true;
		}catch  (Exception e){ //Si falla que vuelva al form. Sólo se pueden enviar Atributos, parámetros no

		}finally{
			DataBaseHelper.closeConnection();
		}
		
		return modificado;
	}

	@Override
	public boolean delete(int id) {
		
		boolean borrado = false;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
	        Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/skalada","root", "");
			
	        Statement st = conexion.createStatement();
	        String sql = "DELETE FROM `test` WHERE  `id`=" + id;
	        
	        if (st.executeUpdate(sql) != 1){ //Si es diferente de 1 es que no se ha insertado ese �nico registro
	        	throw new Exception("No se ha realizado el borrado: " + sql);
	        }else{
	        	borrado = true;
	        }
	        
	        conexion.close();
		}catch  (Exception e){ //Si falla que vuelva al form. S�lo se pueden enviar Atributos, par�metros no
			
		}finally{
			DataBaseHelper.closeConnection();
		}
		
		return borrado;
	}

	/**
	 * Recupera todas las Personas que han aprobado
	 * @return {@code ArrayLiust<Persona>} listado Personas
	 */
	@Override
	public ArrayList<Object> getAprobados() {

		ArrayList<Object> resul = new ArrayList<Object>();
		try{
			Connection con = DataBaseHelper.getConnection();
			Statement st = con.createStatement(); 
	    	String sql = "SELECT * FROM `test` WHERE `nota` >= 5.0 ";
	    	ResultSet rs = st.executeQuery (sql);
	    	
	    	//mapeo resultSet => ArrayList<Persona>
	    	while(rs.next()){
	    		resul.add(mapear(rs));
	    	}	
	    	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseHelper.closeConnection();
		}
		
		return resul;
			
	}

	@Override
	public ArrayList<Object> getSuspendidos() {
		
		ArrayList<Object> resul = new ArrayList<Object>();
		try{
			Connection con = DataBaseHelper.getConnection();
			Statement st = con.createStatement(); 
	    	String sql = "SELECT * FROM `test`  WHERE `nota` < 5.0 ";
	    	ResultSet rs = st.executeQuery (sql);
	    	
	    	//mapeo resultSet => ArrayList<Persona>
	    	while(rs.next()){
	    		resul.add(mapear(rs));
	    	}	

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseHelper.closeConnection();
		}
		
		return resul;

	}
	
	/**
	 * Mapea un ResultSet a una Persona
	 * @param rs
	 * @return
	 * @throws SQLException 
	 */
	private Persona mapear(ResultSet rs) throws SQLException{

		Persona p = new Persona( rs.getString("nombre") );
		p.setId( rs.getInt("id"));
		p.setFecha( rs.getTimestamp("fecha"));
		p.setTelefono(rs.getString("telefono"));
		p.setNota(rs.getFloat("nota"));
		
		return p;

	}
	
	
}