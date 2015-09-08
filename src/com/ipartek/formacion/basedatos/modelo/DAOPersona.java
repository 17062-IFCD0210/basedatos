package com.ipartek.formacion.basedatos.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		PreparedStatement pst = null;
		ArrayList<Object> resul = new ArrayList<Object>();
		try{
			Connection con = DataBaseHelper.getConnection();
			String sql = "SELECT * FROM `test` ";
			pst = con.prepareStatement(sql); 
	    	ResultSet rs = pst.executeQuery ();
	    	
	    	//mapeo resultSet => ArrayList<Persona>	    	
	    	while(rs.next()) {
	    		resul.add(mapeo(rs));
	    	}	
	    	
	    	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(pst != null) {
					pst.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DataBaseHelper.closeConnection();
		}
		
		return resul;
		
	}

	@Override
	public int save(Object o) {
		int resul = -1;
		PreparedStatement pst = null;
		ResultSet rsKeys = null;
		try{
			Persona p = (Persona) o;
			Connection con = DataBaseHelper.getConnection();
			String sql = "INSERT INTO `test` (`nombre`, `nota`, `telefono`, `fecha`) VALUES (?,?,?,?);";
			pst = con.prepareStatement(sql);
			
			pst.setString(1, p.getNombre());
			pst.setFloat(2, p.getNota());
			pst.setString(3, p.getTelefono());
			pst.setTimestamp(4, p.getFecha());
	    	
	    	if(pst.executeUpdate() == 1) {
	    		rsKeys = pst.getGeneratedKeys();
	    		if(rsKeys.next()) {
	    			resul = rsKeys.getInt(1);
	    		} else {
	    			throw new Exception("No se ha realizado insercion " + sql);
	    		}
	    	}    	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(rsKeys != null) {
					rsKeys.close();
				}
				if(pst != null) {
					pst.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DataBaseHelper.closeConnection();
		}
		return resul;
	}

	@Override
	public Object getById(int id) {
		Persona p = null;
		PreparedStatement pst = null;
		try{
			Connection con = DataBaseHelper.getConnection();
			String sql = "SELECT * FROM `test` WHERE id= ?";
			pst = con.prepareStatement(sql);
			
			pst.setInt(1, id);
			
	    	ResultSet rs = pst.executeQuery ();
	    	
	    	//mapeo resultSet => ArrayList<Persona>	    	
	    	while(rs.next()) {
	    		p = mapeo(rs);
	    	}	
	    	
	    	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(pst != null) {
					pst.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DataBaseHelper.closeConnection();
		}
		
		return p;
	}

	@Override
	public boolean update(Object o) {
		PreparedStatement pst = null;
		boolean resul = false;
		try{
			Persona p = (Persona) o;
			Connection con = DataBaseHelper.getConnection();
			String sql = "UPDATE test SET nombre= ?, nota= ?, telefono=?, fecha=? WHERE id=?;";
			pst = con.prepareStatement(sql);
			
			pst.setString(1, p.getNombre());
			pst.setFloat(2, p.getNota());
			pst.setString(3, p.getTelefono());
			pst.setTimestamp(4, p.getFecha());
			pst.setInt(5, p.getId());
			
	    	if(pst.executeUpdate() == 1) {
	    		resul = true;
	    	}
	    		    	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(pst != null) {
					pst.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DataBaseHelper.closeConnection();
		}
		
		return resul;
	}

	@Override
	public boolean delete(int id) {
		PreparedStatement pst = null;
		boolean resul = false;
		try {
			Connection con = DataBaseHelper.getConnection();
			String sql = "DELETE FROM `test` WHERE id = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			
			if(pst.executeUpdate() == 1) {
	    		resul = true;
	    	}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pst != null) {
					pst.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DataBaseHelper.closeConnection();
		}
		
		return resul;
	}

	@Override
	public ArrayList<Object> getAprobados() {
		PreparedStatement pst = null;
		ArrayList<Object> resul = new ArrayList<Object>();
		try{
			Connection con = DataBaseHelper.getConnection();
	    	String sql = "SELECT * FROM `test` WHERE nota>=?;";
	    	pst = con.prepareStatement(sql); 
	    	
	    	pst.setFloat(1, 5f);
	    	
	    	ResultSet rs = pst.executeQuery();
	    	
	    	//mapeo resultSet => ArrayList<Persona>    	
	    	while(rs.next()){
	    		resul.add(mapeo(rs));
	    	}	
	    	
	    	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(pst != null) {
					pst.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DataBaseHelper.closeConnection();
		}
		
		return resul;
	}

	@Override
	public ArrayList<Object> getSuspendidos() {
		PreparedStatement pst = null;
		ArrayList<Object> resul = new ArrayList<Object>();
		try{
			Connection con = DataBaseHelper.getConnection();
	    	String sql = "SELECT * FROM `test` WHERE nota<?;";
	    	pst = con.prepareStatement(sql); 
	    	
	    	pst.setFloat(1, 5f);
	    	
	    	ResultSet rs = pst.executeQuery();
	    	
	    	//mapeo resultSet => ArrayList<Persona>    	
	    	while(rs.next()){
	    		resul.add(mapeo(rs));
	    	}	
	    	
	    	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(pst != null) {
					pst.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
