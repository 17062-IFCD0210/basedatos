package com.ipartek.formacion.basedatos.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import com.ipartek.formacion.basedatos.bean.Persona;

/**
 * DAO: Data Access Object
 * Clase especializada en mapear una {@code Persona} contra la Base Datos
 * Dispone de los metodos basicos para realizar las operaciones de CRUD
 * @author Curso
 *
 */
public class DAOPersona implements IDAOPersona{

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
	    	
	    	//mapeo resultSet => ArrayList<Persona>
	    	Persona p = null;	    	
	    	while(rs.next()){
	    		
	    		p = new Persona( rs.getString("nombre") );
	    		p.setId( rs.getInt("id"));
	    		p.setFecha( rs.getTimestamp("fecha"));
	    		p.setTelefono(rs.getString("telefono"));
	    		p.setNota(rs.getFloat("nota"));
	    		
	    		resul.add(p);
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

		try{
			Connection con = DataBaseHelper.getConnection();
			Statement st = con.createStatement();
			Persona p=(Persona)o;
			String pNombre = p.getNombre();
			Float pNota = p.getNota();
			String pTelefono = p.getTelefono();
			Date pFecha = p.getFecha();
	    	String sql = "INSERT INTO `test` (`nombre`, `nota`, `telefono`, `fecha`) VALUES ('" + pNombre + "',"+ pNota + ",'"+ pTelefono +"','"+ pFecha +"');";
	    	System.out.println(p.toString());
	    	//ejecutar insert
	    	if ( st.executeUpdate(sql) != 1){	    		
	    		throw new Exception("No se ha realizado insercion: " + sql);	    		
	    	}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseHelper.closeConnection();
		}
		
		return 0;
	}

	@Override
	public Object getById(int id) {
		Persona p;
		try {
			Connection con = DataBaseHelper.getConnection();    	
	    	Statement st = con.createStatement();
	    	String sql   = "SELECT * FROM `test` WHERE `id` = "+id+";";
	    	ResultSet rs = st.executeQuery (sql);	    	
	    	rs.first();
	    	p = mapeo(rs);
	    	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
    		return false;
		} catch (Exception e) {
			e.printStackTrace();
    		return false;
		}finally{
			DataBaseHelper.closeConnection();
		}
		return p;
	}

	@Override
	public boolean update(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		try {
			Connection con = DataBaseHelper.getConnection();
	    	
	    	Statement st = con.createStatement();
	    	String sql   = "DELETE FROM `test` WHERE `id` = "+id+";";

	    	//ejecutar delete
	    	if ( st.executeUpdate(sql) != 1){	    		
	    		throw new Exception("No se ha realizado eliminacion: " + sql);
	    	}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
    		return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
    		return false;
		}finally{
			DataBaseHelper.closeConnection();
		}
 
		return true;
	}

	@Override
	public ArrayList<Object> getAprobados() {
		ArrayList<Object> resul = new ArrayList<Object>();
		try{
			Connection con = DataBaseHelper.getConnection();
			Statement st = con.createStatement(); 
	    	String sql = "SELECT * FROM `test` WHERE `nota` >= 5";
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
	    	String sql = "SELECT * FROM `test` WHERE `nota` < 5";
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
	
	/**
	 * Mapea un ResultSer a una Persona
	 * @param rs
	 * @return
	 * @throws SQLException 
	 */
	private Persona mapeo(ResultSet rs) throws SQLException{
		Persona p = new Persona( rs.getString("nombre") );
		p.setId( rs.getInt("id"));
		p.setFecha( rs.getTimestamp("fecha"));
		p.setTelefono(rs.getString("telefono"));
		p.setNota(rs.getFloat("nota"));
		return p;
	}
}