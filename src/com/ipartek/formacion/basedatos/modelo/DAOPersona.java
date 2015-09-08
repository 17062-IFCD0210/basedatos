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
 * Clase especializada en mapear una {@code Persona} contra una BBDD
 * Dispone de los metdos basicos para realizar las operaciones de CRUD
 * @author Lara
 *
 */
public class DAOPersona implements IDAOPersona{

	/**
	 * Recupera todas las Personas, el get all devuelve un ArrayList de personas
	 * @return {@code ArrayList<Persona>} listado personas
	 */
	@Override
	public ArrayList<Object> getAll(){
		
		ArrayList<Object> resul = new ArrayList<Object>();
		try{
			Connection con = DataBaseHelper.getConnection();
			Statement st = con.createStatement(); 
	    	String sql = "SELECT * FROM `test`";
	    	ResultSet rs = st.executeQuery (sql);
	    	  		    	
	    	while(rs.next()){	
	    		resul.add( mapeo(rs) );	
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
		try{
			Connection con = DataBaseHelper.getConnection();
	    	String sql = "INSERT INTO `test` (`nombre`, `nota`, `telefono`) VALUES ( ?, ?, ? );";
	    	PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS ); // Constante que nos devuelve
	    	
	    	Persona p = (Persona)o;
	    	pst.setString (1, p.getNombre() );
			pst.setFloat  (2, p.getNota() );
			pst.setString (3, p.getTelefono() );
	    	
	    	if ( pst.executeUpdate() == 1 ){
	    		ResultSet rsKeys = pst.getGeneratedKeys();
	    		if( rsKeys.next() ){
	    			resul = rsKeys.getInt(1);
	    		}else{
	    			throw new SQLException("No se ha podido generar ID");
	    		}
	    		
	    	}
	    	
		}catch( Exception e ){
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
			Statement st = con.createStatement(); 
	    	String sql = "SELECT * FROM `test` where id=" + id;
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
	
			boolean resul = false;
			Persona p = null;
			
			try {
				Connection con = DataBaseHelper.getConnection();
		    	String sql = "UPDATE `test` SET `nombre`= ? , `nota`= ?, `telefono` = ? WHERE `id`= ? ;";
		    	PreparedStatement pst = con.prepareStatement(sql);
		    	
		    	p = (Persona)o; // Casteamos un objeto a persona
				pst.setString (1, p.getNombre() );
				pst.setFloat  (2, p.getNota() );
				pst.setString (3, p.getTelefono() );
				pst.setInt	  (4, p.getId() );
				
				if ( pst.executeUpdate() == 1){
					resul=true;
				}			
				
			} catch (Exception e) {			
				e.printStackTrace();
			}finally{
				DataBaseHelper.closeConnection();
			}
			return resul;
	}

	@Override
	public boolean delete(int id) {
		boolean resul = false;
		
		try {
			Connection con = DataBaseHelper.getConnection();
			String sql = "DELETE FROM `test` WHERE id= ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			
			if ( pst.executeUpdate() == 1){
				resul=true;
			}			
			
		}catch (Exception e) {			
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
	    	String sql = "SELECT * FROM `test` where `nota` >=5 ";
	    	ResultSet rs = st.executeQuery (sql);
	    	    	
	    	while(rs.next()){
	    		resul.add(mapeo(rs));	
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
	    		resul.add( mapeo(rs) );	
	    	}	
	    	
	    	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseHelper.closeConnection();
		}
		
		return resul;
		
	}
	/**
	 * Mapea un ResultSet a una Persona (Lo hacemos para tener el codigo mas limpio, porque se repite muchas veces)
	 * @param rs
	 * @return
	 * @throws SQLException 
	 */
	private Persona mapeo ( ResultSet rs ) throws SQLException{

		Persona p = new Persona (rs.getString("nombre") );
		p.setId( rs.getInt("id"));
		p.setFecha( rs.getTimestamp("fecha"));
		p.setTelefono(rs.getString("telefono"));
		p.setNota(rs.getFloat("nota"));
		
		return p;

		}
	
}
