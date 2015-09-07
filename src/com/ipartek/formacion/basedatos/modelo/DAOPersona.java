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
		
		try{
			Connection con = DataBaseHelper.getConnection();
			String sql = "SELECT * FROM `test` ";
			PreparedStatement pst = con.prepareStatement(sql);
	    	ResultSet rs = pst.executeQuery();
	    	
	    	//mapeo resultSet => ArrayList<Persona>	    	   	
	    	while(rs.next()){
	    		resul.add(mapeo(rs));
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
		int resul = -1;
		Persona p = (Persona) o;		
		try{
			Connection con = DataBaseHelper.getConnection();
			String sql = "insert into `test` (`nombre`, `nota`, `telefono`) values (?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, p.getNombre());
			pst.setFloat(2, p.getNota());
			pst.setString(3, p.getTelefono());
			
	    	if ( pst.executeUpdate() != 1 ){
				throw new Exception("No se ha realizado insercion");
			}
	    	resul = 1;	    		
		} catch (Exception e){
			e.printStackTrace();
		} finally {
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
	    	String sql = "SELECT * FROM `test` WHERE `id`="+id;
	    	ResultSet rs = st.executeQuery (sql);
	    	
	    	//mapeo resultSet => ArrayList<Persona>	    	   	
	    	while(rs.next()){
	    		resul = mapeo(rs);
	    	}	
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			DataBaseHelper.closeConnection();
		}		
		return resul;		
	}

	@Override
	public boolean update(Object o) {
		boolean resul = false;
		Persona p = (Persona) o;		
		try{
			Connection con = DataBaseHelper.getConnection();
			Statement st = con.createStatement(); 
			String sql = "UPDATE `test` SET `nombre`='" + p.getNombre() + "', `nota`=" + p.getNota() + ", `telefono`='" + p.getTelefono() + "', `fecha`='" + p.getFecha() + "' WHERE `id`=" + p.getId() + ";";
	    	if ( st.executeUpdate(sql) != 1 ){
				throw new Exception("No se ha podido editar");
			}			
	    	resul = true;	    		
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			DataBaseHelper.closeConnection();
		}		
		return resul;
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
				resul=true;
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
	    	String sql = "SELECT * FROM `test` WHERE `nota`>=5 ";
	    	ResultSet rs = st.executeQuery (sql);
	    	
	    	//mapeo resultSet => ArrayList<Persona>	    	   	
	    	while(rs.next()){
	    		resul.add(mapeo(rs));
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
	    	while(rs.next()){
	    		resul.add(mapeo(rs));
	    	}	 
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			DataBaseHelper.closeConnection();
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
