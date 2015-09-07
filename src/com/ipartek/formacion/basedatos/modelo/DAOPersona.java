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
 * Clase especializada que mapea una {@code Persona} contra la Base Datos.
 * Dispone de los metodos basicos para realizar las operaciones de CRUD
 * @author ur00
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
		try{
			Connection con = DataBaseHelper.getConnection();
			Statement st = con.createStatement(); 
	    	String sql = "SELECT * FROM `test` ";
	    	ResultSet rs = st.executeQuery (sql);
	    	
	    	while(rs.next()){	    		    		
	    		resul.add( mapeo(rs));    		
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
		int result = -1;
		
		try{
			Connection con = DataBaseHelper.getConnection();
			String sql = "INSERT INTO `test` (`nombre`, `nota`, `telefono`, `fecha`) VALUES (?, ?, ?, ?);";
			
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setCharacterStream(1, );
			pst.setFloat(2, );
			pst.setCharacterStream(3, );
			pst.setTimestamp(4, );
			
	    	if ( pst.executeUpdate() == 1 ){
	    		result = 1;
	    	} 
	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseHelper.closeConnection();
		}
		
		return result;
	}

	@Override
	public Object getById(int id) {
		
		Object resul = new Object();
		try{
			Connection con = DataBaseHelper.getConnection();
			Statement st = con.createStatement(); 
	    	String sql = "SELECT * FROM `test` WHERE id=" + id;
	    	ResultSet rs = st.executeQuery (sql);
	    	
	    	while(rs.next()){	    		    		
	    		resul = mapeo(rs);    		
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
		int result = -1;
		
		try{
			Connection con = DataBaseHelper.getConnection();
			String sql = "UPDATE `test` SET `nombre`=?, `nota`=?, `telefono`=?, `fecha`=? WHERE  `id`=?;";
			
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setCharacterStream(1, reader);
			pst.setFloat(2, x);
			pst.setCharacterStream(3, reader);
			pst.setTimestamp(4, x);
			
	    	if ( pst.executeUpdate() == 1 ){
	    		result = 1;
	    	} 
	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseHelper.closeConnection();
		}
		
		return result;
	}

	@Override
	public boolean delete(int id) {
	
		boolean resul = false;
		try{
			Connection con = DataBaseHelper.getConnection();
			String sql = "delete from `test` where id= ?";
			
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			
	    	if ( pst.executeUpdate() == 1 ){
	    		resul = true;
	    	} 
	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseHelper.closeConnection();
		}
		
		return resul;

	}

	@Override
	public ArrayList<Object> getAprobados() {
		ArrayList<Object> resul = new ArrayList<Object>();
		try{
			Connection con = DataBaseHelper.getConnection();
			Statement st = con.createStatement(); 
	    	String sql = "SELECT * FROM `test` where `nota` >= 5 ";
	    	ResultSet rs = st.executeQuery (sql);
	    	
	    	while(rs.next()){	    		    		
	    		resul.add( mapeo(rs));    		
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
	    	String sql = "SELECT * FROM `test` where `nota` < 5 ";
	    	ResultSet rs = st.executeQuery (sql);
	    	
	    			    	
	    	while(rs.next()){	    		    		
	    		resul.add( mapeo(rs));    		
	    	}	
	    	
	    	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseHelper.closeConnection();
		}
		
		return resul;
	}
	
	/**
	 * Mapea un ResulSet a una Persona
	 * @param rs
	 * @return
	 * @throws SQLException 
	 */
	private Persona mapeo ( ResultSet rs ) throws SQLException{
		
		Persona p = new Persona( rs.getString("nombre") );
		p.setId( rs.getInt("id"));
		p.setFecha( rs.getTimestamp("fecha"));
		p.setTelefono(rs.getString("telefono"));
		p.setNota(rs.getFloat("nota"));		
		
		return p;
	}
	
}