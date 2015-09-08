package com.ipartek.formacion.basedatosProfe.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
			Statement st = con.createStatement(); //Se puede inyectar SQL. No es seguro. Usar PreparedStatement
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
		
		int resul = -1;
		Persona p = null;
		
		try{
			Connection con = DataBaseHelper.getConnection();
			String sql = "INSERT INTO `test` (`nombre`, `nota`, `telefono`) VALUES (?, ?, ?);";
			PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); //Ejecuta la sql y devuelve la key generada, useasé un 1
			p = (Persona)o;
			pst.setString(1, p.getNombre());
			pst.setFloat(2, p.getNota());
			pst.setString(3, p.getTelefono());
			
			if (pst.executeUpdate() == 1){//Si ha creado un nuevo registro
				//Para devolver la id
				ResultSet rsKeys = pst.getGeneratedKeys(); //Guardamos en un ResulSet la tabla que el método getGeneratedKeys() ha generado del nuevo registro
				if (rsKeys.next()){ //si hay registros
					resul = rsKeys.getInt(1); //coge el valor de la primera columna del nuevo registro generado
				}else{
					throw new SQLException("No se ha podido generar ID");
				}
			}
			
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			DataBaseHelper.closeConnection();
		}

		return resul;
	}

	@Override
	public Object getById(int id) {
		
		Object resul = new Object();
		
		try{
			Connection con = DataBaseHelper.getConnection();
			Statement st = con.createStatement(); //Se puede inyectar SQL. No es seguro. Usar PreparedStatement
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
		
		boolean resul = false;
		
		try{
			Connection con = DataBaseHelper.getConnection();
			String sql = "UPDATE `test` SET `nombre`= ? , `nota` = ? , `telefono` = ? WHERE `id`= ? ;";
			PreparedStatement pst = con.prepareStatement(sql); //No permite la inyección de SQL. Es más seguro y es más rápido
			//Cojo los parámetros y se los envío a la sql en el orden requerido en la propia sql
			Persona p = (Persona)o; //Casteo
			pst.setString(1, p.getNombre());
			pst.setFloat(2, p.getNota());
			pst.setString(3, p.getTelefono());
			pst.setInt(4, p.getId());
			
			if (pst.executeUpdate() == 1){
				resul = true;
			}
			
			}catch (Exception e){ //Si falla que vuelva al form. Sólo se pueden enviar Atributos, parámetros no
				e.printStackTrace();
			}finally{
				DataBaseHelper.closeConnection();
			}
		
		return resul;
		
	}

	@Override
	public boolean delete(int id) {
		
		boolean resul = false;
		
		try{
			
	        Connection conexion = DataBaseHelper.getConnection();
	        //String sql = "DELETE FROM `test` WHERE  `id`=" + id;
	        
	        String sql = "DELETE FROM `test` WHERE  `id`= ?";
	        PreparedStatement pst = conexion.prepareStatement(sql); //No permite la inyección de SQL y es más rápido
	        pst.setInt(1,  id);
	        
	        if (pst.executeUpdate() == 1){
	        	resul = true;
	        }

		}catch  (Exception e){ //Si falla que vuelva al form. S�lo se pueden enviar Atributos, par�metros no
			e.printStackTrace();
		}finally{
			DataBaseHelper.closeConnection();
		}
		
		return resul;
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
			Statement st = con.createStatement(); //Se puede inyectar SQL. No es seguro. Usar PreparedStatement
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
			Statement st = con.createStatement(); //Se puede inyectar SQL. No es seguro. Usar PreparedStatement
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