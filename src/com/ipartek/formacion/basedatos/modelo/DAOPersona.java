package com.ipartek.formacion.basedatos.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ipartek.formacion.basedatos.bean.Persona;

/**
 * DAO: Data Access Object
 * Clase especializada en mapear una {@code Persona} contra la Base Datos
 * dispone de los metodos basicos para realizar las operaciones de CRUD
 * @author Curso
 *
 */
public class DAOPersona implements IDAOPersona {

	/**
	 * Recupera todas las Personas
	 * @return {@code ArrayList<Persona>} listado personas
	 */
	@Override
	public ArrayList<Object> getAll(){		
		
		ArrayList<Object> resul = new ArrayList<Object>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try{
			Connection con = DataBaseHelper.getConnection();
			String sql = "SELECT * FROM `test` ";
			pst = con.prepareStatement(sql);
	    	rs = pst.executeQuery();
	    	
	    	//mapeo resultSet => ArrayList<Persona>	    	   	
	    	while(rs.next()){
	    		resul.add(mapeo(rs));
	    	}	
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			try {
				if(rs != null){
					rs.close();
				}
				if(pst != null){
					pst.close();
				}
				DataBaseHelper.closeConnection();			
			}catch(Exception e){
				e.printStackTrace();
			}
		}		
		return resul;		
	}
	
	@Override
	public int save(Object o) {
		int resul = -1;
		Persona p = (Persona)o;	
		PreparedStatement pst = null;
		ResultSet rsKeys = null;
		try{
			Connection con = DataBaseHelper.getConnection();
			String sql = "INSERT INTO `test` (`nombre`, `nota`, `telefono`) VALUES (?,?,?)";
			pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, p.getNombre());
			pst.setFloat(2, p.getNota());
			pst.setString(3, p.getTelefono());			
	    	if ( pst.executeUpdate() != 1 ){
				throw new Exception("No se ha realizado la insercion");
			} else {				
//				//Codigo para obtener el ultimo id de la tabla
//				sql = "SELECT MAX(ID) FROM `test`";
//				pst = con.prepareStatement(sql);
//				ResultSet rs ;
//				rs = pst.executeQuery();
//				rs.next();
//				resul = rs.getInt(1);				
				
				rsKeys = pst.getGeneratedKeys();
				if (rsKeys.next()) {
					resul = rsKeys.getInt(1);
				} else {
					throw new Exception("No se ha podido generar ID");
				}
			}	    		    		
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			try {
				if(rsKeys != null){
					rsKeys.close();
				}
				if(pst != null){
					pst.close();
				}
				DataBaseHelper.closeConnection();			
			}catch(Exception e){
				e.printStackTrace();
			}			
		}		
		return resul;
	}

	@Override
	public Object getById(int id) {
		
		Object resul = new Object();
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try{
			Connection con = DataBaseHelper.getConnection();
			String sql = "SELECT * FROM `test` WHERE `id`=?;";
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
	    	rs = pst.executeQuery();
	    	
	    	//mapeo resultSet => ArrayList<Persona>	    	   	
	    	while(rs.next()){
	    		resul = mapeo(rs);
	    	}	
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			try {
				if(rs != null){
					rs.close();
				}
				if(pst != null){
					pst.close();
				}
				DataBaseHelper.closeConnection();			
			}catch(Exception e){
				e.printStackTrace();
			}
		}		
		return resul;		
	}

	@Override
	public boolean update(Object o) {
		boolean resul = false;
		Persona p = (Persona) o;
		PreparedStatement pst = null;
		try{
			Connection con = DataBaseHelper.getConnection();
			String sql = "UPDATE `test` SET `nombre`= ? , `nota`= ? , `telefono`= ? , `fecha`= ? WHERE `id`= ? ;";
			pst = con.prepareStatement(sql);
			pst.setString(1, p.getNombre());
			pst.setFloat(2, p.getNota());
			pst.setString(3, p.getTelefono());
			pst.setTimestamp(4, p.getFecha());
			pst.setInt(5, p.getId());
			
	    	if ( pst.executeUpdate() != 1 ){
				throw new Exception("No se ha podido editar");
			}			
	    	resul = true;	    		
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			try {
				if(pst != null){
					pst.close();
				}
				DataBaseHelper.closeConnection();			
			}catch(Exception e){
				e.printStackTrace();
			}
		}		
		return resul;
	}

	@Override
	public boolean delete(int id) {
		boolean resul = false;
		PreparedStatement pst = null;
		try{
			Connection con = DataBaseHelper.getConnection();
			String sql = "DELETE FROM `test` WHERE id= ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			
			if ( pst.executeUpdate() == 1 ){
				resul=true;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(pst != null){
					pst.close();
				}
				DataBaseHelper.closeConnection();			
			}catch(Exception e){
				e.printStackTrace();
			}
		}		
		return resul;
	}

	@Override
	public ArrayList<Object> getAprobados() {
		
		ArrayList<Object> resul = new ArrayList<Object>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try{
			Connection con = DataBaseHelper.getConnection();
			String sql = "SELECT * FROM `test` WHERE `nota`>=5 ";
			pst = con.prepareStatement(sql);	    	
	    	rs = pst.executeQuery();
	    	
	    	//mapeo resultSet => ArrayList<Persona>	    	   	
	    	while(rs.next()){
	    		resul.add(mapeo(rs));
	    	}	   	
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			try {
				if(rs != null){
					rs.close();
				}
				if(pst != null){
					pst.close();
				}
				DataBaseHelper.closeConnection();			
			}catch(Exception e){
				e.printStackTrace();
			}
		}		
		return resul;		
	}

	@Override
	public ArrayList<Object> getSuspendidos() {
		ArrayList<Object> resul = new ArrayList<Object>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try{
			Connection con = DataBaseHelper.getConnection();
			String sql = "SELECT * FROM `test` WHERE `nota`<5 ";
			pst = con.prepareStatement(sql); 	    	
	    	rs = pst.executeQuery ();
	    	
	    	//mapeo resultSet => ArrayList<Persona>	    	   	
	    	while(rs.next()){
	    		resul.add(mapeo(rs));
	    	}	 
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			try {
				if(rs != null){
					rs.close();
				}
				if(pst != null){
					pst.close();
				}
				DataBaseHelper.closeConnection();			
			}catch(Exception e){
				e.printStackTrace();
			}
		}		
		return resul;		
	}
	
	/**
	 * Mapea un ResultSet a Persona
	 * @param rs
	 * @return
	 * @throws SQLException 
	 */
	private Persona mapeo (ResultSet rs) throws SQLException{
		Persona resul = null;    
		
		resul = new Persona( rs.getString("nombre") );
		resul.setId( rs.getInt("id"));
		resul.setNota(rs.getFloat("nota"));	    		
		resul.setTelefono(rs.getString("telefono"));
		resul.setFecha( rs.getTimestamp("fecha"));	    		
    		 
		return resul;
	}
	
}
