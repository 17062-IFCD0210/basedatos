package com.ipartek.formacion.basedatos.modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ipartek.formacion.basedatos.bean.Persona;

/**
 * DAO: Data Access Object
 * Clase especializada en mapear una {@code Persona} contra la base de datos
 * Dispone de los metodos basicos para realizar las operaciones de CRUD
 * @author Curso
 *
 */
public class DAOPersona implements IDAOPersona{

	/**
	 * Recupera todas las Personas
	 * @return {@code ArrayList<Persona>} listado Personas
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
	    	while(rs.next()) {
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
	public int save(Object o) {
		try{
			Persona p = (Persona) o;
			Connection con = DataBaseHelper.getConnection();
			Statement st = con.createStatement(); 
	    	String sql = "INSERT INTO `test` (`nombre`, `nota`, `telefono`, `fecha`) VALUES ('" + p.getNombre() + "', " + p.getNota() + ", '" + p.getTelefono() + "', '" + p.getFecha() + "');";
	    	if(st.executeUpdate(sql) != 1) {
	    		throw new Exception("No se ha realizado insercion " + sql);
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
		Persona p = null;
		try{
			Connection con = DataBaseHelper.getConnection();
			Statement st = con.createStatement(); 
	    	String sql = "SELECT * FROM `test` WHERE id=" + id + ";";
	    	ResultSet rs = st.executeQuery (sql);
	    	
	    	//mapeo resultSet => ArrayList<Persona>	    	
	    	while(rs.next()) {
	    		p = mapeo(rs);
	    	}	
	    	
	    	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseHelper.closeConnection();
		}
		
		return p;
	}

	@Override
	public boolean update(Object o) {
		try{
			Persona p = (Persona) o;
			Connection con = DataBaseHelper.getConnection();
			Statement st = con.createStatement(); 
	    	String sql = "UPDATE test SET nombre='" + p.getNombre() + "', nota=" + p.getNota() + ", telefono='" + p.getTelefono() + "', fecha='" + p.getFecha() + "' WHERE id=" + p.getId() + ";";
	    	if(st.executeUpdate(sql) != 1) {
	    		throw new Exception("No se ha realizado actualizacion " + sql);
	    	}
	    		    	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseHelper.closeConnection();
		}
		
		return true;
	}

	@Override
	public boolean delete(int id) {
		try{
			Connection con = DataBaseHelper.getConnection();
			Statement st = con.createStatement(); 
	    	String sql = "DELETE FROM test WHERE id=" + id + ";";
	    	if(st.executeUpdate(sql) != 1) {
	    		throw new Exception("No se ha realizado eliminacion " + sql);
	    	}
	    	
	    	
		}catch(Exception e){
			e.printStackTrace();
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
	    	String sql = "SELECT * FROM `test` WHERE nota>=5;";
	    	ResultSet rs = st.executeQuery (sql);
	    	
	    	//mapeo resultSet => ArrayList<Persona>    	
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
	    	String sql = "SELECT * FROM `test` WHERE nota<5;";
	    	ResultSet rs = st.executeQuery (sql);
	    	
	    	//mapeo resultSet => ArrayList<Persona>   	
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
	 * Mapea un ResultSet a una Persona
	 * @param rs
	 * @return
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
