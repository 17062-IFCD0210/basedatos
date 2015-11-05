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
		Statement st = null;
		
		try{
			Connection con = DataBaseHelper.getConnection();
			st = con.createStatement(); //Se puede inyectar SQL. No es seguro. Usar PreparedStatement
	    	String sql = "SELECT * FROM `test`";
	    	ResultSet rs = st.executeQuery (sql);
	    	
	    	//mapeo resultSet => ArrayList<Persona>
	    	while(rs.next()){
	    		resul.add(mapear(rs));
	    	}	
	    	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if (st != null){st.close();}
				DataBaseHelper.closeConnection();
			}catch(Exception e){e.printStackTrace();}
		}
		
		return resul;
		
	}

	@Override
	public int save(Object o) {
		
		int resul = -1;
		PreparedStatement pst = null;
		ResultSet rsKeys = null;
		String sql = "";
		Persona p = null;
		
		if ( o != null ){
			try{
				p = (Persona)o;
				Connection con = DataBaseHelper.getConnection();
				if (p.getFecha() != null){
					sql = "INSERT INTO `test` (`nombre`, `nota`, `telefono`, `fecha`) VALUES (?, ?, ?, ?);";
				}else{ //Si es null que se encargue MySQL de generar la fecha actual que es c�mo lo hemos predefinido
					sql = "INSERT INTO `test` (`nombre`, `nota`, `telefono`) VALUES (?, ?, ?);";
				}
				pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); //Ejecuta la sql y devuelve la key generada, useasé un 1
				p = (Persona)o;
				pst.setString(1, p.getNombre());
				pst.setFloat(2, p.getNota());
				pst.setString(3, p.getTelefono());
				if (p.getFecha() != null){
					pst.setTimestamp(4, p.getFecha());
				}
				
				if (pst.executeUpdate() == 1){//Si ha creado un nuevo registro
					//Para devolver la id
					rsKeys = pst.getGeneratedKeys(); //Guardamos en un ResulSet la tabla que el método getGeneratedKeys() ha generado del nuevo registro
					if (rsKeys.next()){ //si hay registros
						resul = rsKeys.getInt(1); //coge el valor de la primera columna del nuevo registro generado
					}else{
						throw new SQLException("No se ha podido generar ID");
					}
				}
				
			}catch (Exception e){
				e.printStackTrace();
			}finally{
				try{
					if (rsKeys != null){rsKeys.close();}
					if (pst != null){pst.close();}
					DataBaseHelper.closeConnection();
				}catch(Exception e){e.printStackTrace();}
			}
		}
		return resul;
	}

	@Override
	public Object getById(int id) {
		
		Object resul = new Object();
		Statement st = null;
		
		try{
			Connection con = DataBaseHelper.getConnection();
			st = con.createStatement(); //Se puede inyectar SQL. No es seguro. Usar PreparedStatement
	    	String sql = "SELECT * FROM `test` WHERE id=" + id;
	    	ResultSet rs = st.executeQuery (sql);
	    	
	    	//mapeo resultSet => Persona
	    	while(rs.next()){
	    		resul = mapear(rs);
	    	}	
	    	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if (st != null){st.close();}
				DataBaseHelper.closeConnection();
			}catch(Exception e){e.printStackTrace();}
		}
		
		return resul;
	}

	@Override
	public boolean update(Object o) {
		
		boolean resul = false;
		String sql = "";
		Persona p = null;
		PreparedStatement pst = null;
		
		if ( o != null ){
			try{
				p = (Persona)o; //Casteo
				Connection con = DataBaseHelper.getConnection();
				
				if (p.getFecha() != null){
					sql = "UPDATE `test` SET `nombre`= ? , `nota` = ? , `telefono` = ? , `fecha` = ? WHERE `id`= ? ;";
				}else{
					sql = "UPDATE `test` SET `nombre`= ? , `nota` = ? , `telefono` = ? WHERE `id`= ? ;";
				}
				
				pst = con.prepareStatement(sql); //No permite la inyección de SQL. Es más seguro y es más rápido
				//Cojo los parámetros y se los envío a la sql en el orden requerido en la propia sql
				
				pst.setString(1, p.getNombre());
				pst.setFloat(2, p.getNota());
				pst.setString(3, p.getTelefono());
				if (p.getFecha() != null){
					pst.setTimestamp(4, p.getFecha());
					pst.setInt(5, p.getId());
				}else{
					pst.setInt(4, p.getId());
				}
				
				
				if (pst.executeUpdate() == 1){
					resul = true;
				}
				
				}catch (Exception e){ //Si falla que vuelva al form. Sólo se pueden enviar Atributos, parámetros no
					e.printStackTrace();
				}finally{
					try{
						if (pst != null){pst.close();}
						DataBaseHelper.closeConnection();
					}catch(Exception e){e.printStackTrace();}
				}
		}
		return resul;
		
	}

	@Override
	public boolean delete(int id) {
		
		boolean resul = false;
		PreparedStatement pst = null;
		
		try{
			
	        Connection conexion = DataBaseHelper.getConnection();
	        //String sql = "DELETE FROM `test` WHERE  `id`=" + id;
	        
	        String sql = "DELETE FROM `test` WHERE  `id`= ?";
	        pst = conexion.prepareStatement(sql); //No permite la inyección de SQL y es más rápido
	        pst.setInt(1,  id);
	        
	        if (pst.executeUpdate() == 1){
	        	resul = true;
	        }

		}catch  (Exception e){ //Si falla que vuelva al form. S�lo se pueden enviar Atributos, par�metros no
			e.printStackTrace();
		}finally{
			try{
				if (pst != null){pst.close();}
				DataBaseHelper.closeConnection();
			}catch(Exception e){e.printStackTrace();}
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
		Statement st = null;
		
		try{
			Connection con = DataBaseHelper.getConnection();
			st = con.createStatement(); //Se puede inyectar SQL. No es seguro. Usar PreparedStatement
	    	String sql = "SELECT * FROM `test` WHERE `nota` >= 5.0 ";
	    	ResultSet rs = st.executeQuery (sql);
	    	
	    	//mapeo resultSet => ArrayList<Persona>
	    	while(rs.next()){
	    		resul.add(mapear(rs));
	    	}	
	    	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if (st != null){st.close();}
				DataBaseHelper.closeConnection();
			}catch(Exception e){e.printStackTrace();}
		}
		
		return resul;
			
	}

	@Override
	public ArrayList<Object> getSuspendidos() {
		
		ArrayList<Object> resul = new ArrayList<Object>();
		Statement st = null;
		
		try{
			Connection con = DataBaseHelper.getConnection();
			st = con.createStatement(); //Se puede inyectar SQL. No es seguro. Usar PreparedStatement
	    	String sql = "SELECT * FROM `test`  WHERE `nota` < 5.0 ";
	    	ResultSet rs = st.executeQuery (sql);
	    	
	    	//mapeo resultSet => ArrayList<Persona>
	    	while(rs.next()){
	    		resul.add(mapear(rs));
	    	}	

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if (st != null){st.close();}
				DataBaseHelper.closeConnection();
			}catch(Exception e){e.printStackTrace();}
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