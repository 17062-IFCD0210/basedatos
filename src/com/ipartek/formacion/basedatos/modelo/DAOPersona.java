package com.ipartek.formacion.basedatos.modelo;

import java.sql.Connection;
import java.sql.ResultSet;
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
	public ArrayList<Object> getAll(){		
		
		ArrayList<Object> resul = new ArrayList<Object>();
		
		try{
			Connection con = DataBaseHelper.getConnection();
			Statement st = con.createStatement(); 
	    	String sql = "SELECT * FROM `test` ";
	    	ResultSet rs = st.executeQuery (sql);
	    	
	    	//mapeo resultSet => ArrayList<Persona>
	    	Persona p = null;	    	
	    	while(rs.next()){
	    		
	    		p = new Persona( rs.getString("nombre") );
	    		p.setId( rs.getInt("id"));
	    		p.setNota(rs.getFloat("nota"));	    		
	    		p.setTelefono(rs.getString("telefono"));
	    		p.setFecha( rs.getTimestamp("fecha"));
	    		
	    		resul.add(p);
	    	}		    	
	    	
		} catch (Exception e){
			e.printStackTrace();
		} finally {
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Object> getAprobados() {
		
		ArrayList<Object> resul = new ArrayList<Object>();
		
		try{
			Connection con = DataBaseHelper.getConnection();
			Statement st = con.createStatement(); 
	    	String sql = "SELECT * FROM `test` WHERE `nota`>=5 ";
	    	ResultSet rs = st.executeQuery (sql);
	    	
	    	//mapeo resultSet => ArrayList<Persona>
	    	Persona p = null;	    	
	    	while(rs.next()){
	    		
	    		p = new Persona( rs.getString("nombre") );
	    		p.setId( rs.getInt("id"));
	    		p.setNota(rs.getFloat("nota"));	    		
	    		p.setTelefono(rs.getString("telefono"));
	    		p.setFecha( rs.getTimestamp("fecha"));
	    		
	    		resul.add(p);
	    	}		    	
	    	
		} catch (Exception e){
			e.printStackTrace();
		} finally {
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
	    	String sql = "SELECT * FROM `test` WHERE `nota`<5 ";
	    	ResultSet rs = st.executeQuery (sql);
	    	
	    	//mapeo resultSet => ArrayList<Persona>
	    	Persona p = null;	    	
	    	while(rs.next()){
	    		
	    		p = new Persona( rs.getString("nombre") );
	    		p.setId( rs.getInt("id"));
	    		p.setNota(rs.getFloat("nota"));	    		
	    		p.setTelefono(rs.getString("telefono"));
	    		p.setFecha( rs.getTimestamp("fecha"));
	    		
	    		resul.add(p);
	    	}		    	
	    	
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			DataBaseHelper.closeConnection();
		}
		
		return resul;		
	}
	
}
