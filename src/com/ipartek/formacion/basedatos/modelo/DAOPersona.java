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
		Statement st = null;
		ResultSet rs = null;
		
		try{
			Connection con = DataBaseHelper.getConnection();
			st = con.createStatement(); 
	    	String sql = "SELECT * FROM `test`";
	    	rs = st.executeQuery (sql);
	    	  		    	
	    	while(rs.next()){	
	    		resul.add( mapeo(rs) );	
	    	}		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs != null){	rs.close();}
				if (st != null){st.close();}	
				DataBaseHelper.closeConnection();
			}catch (Exception e){e.printStackTrace();}
		}
		return resul;	
	}
			

	@Override
	public int save(Object o) {
		int resul = -1;
		String sql ="";
		PreparedStatement pst = null;
		ResultSet rsKeys = null;
		try{
			Persona p = (Persona)o;
			Connection con = DataBaseHelper.getConnection();
	    	if( p.getFecha() != null){
	    		sql = "INSERT INTO `test` (`nombre`, `nota`, `telefono`, `fecha`) VALUES ( ?, ?, ?, ? );";
	    	}else{
	    		sql = "INSERT INTO `test` (`nombre`, `nota`, `telefono`) VALUES ( ?, ?, ? );";
	    	}
	    	
	    	pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS ); // Constante que nos devuelve
	    	
	    	
	    	pst.setString (1, p.getNombre() );
			pst.setFloat  (2, p.getNota() );
			pst.setString (3, p.getTelefono() );	
			if (p.getFecha() !=null){
					pst.setTimestamp (4, p.getFecha() );
			}
	    	
	    	if ( pst.executeUpdate() == 1 ){
	    			rsKeys = pst.getGeneratedKeys();
	    			if( rsKeys.next() ){
	    				resul = rsKeys.getInt(1);
	    			}else{
	    				throw new SQLException("No se ha podido generar ID");
	    		}
	    		
	    	}
	    	
		}catch( Exception e ){
			e.printStackTrace();
		}finally{
				try{
					if (rsKeys != null){rsKeys.close();}
					if (pst != null){pst.close();}
					DataBaseHelper.closeConnection();
				}catch (Exception e){
					e.printStackTrace();
				}
		}	
		return resul;
	}

	@Override
	public Object getById(int id) {
		
		Object resul = new Object();
		Statement st = null;
		ResultSet rs = null;
		try{
			Connection con = DataBaseHelper.getConnection();
			st = con.createStatement(); 
	    	String sql = "SELECT * FROM `test` where id=" + id;
	    	rs = st.executeQuery (sql);
	    		    	
	    	while(rs.next()){    		
	    		resul = mapeo(rs);	
	    	}	
	    	    	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs != null){rs.close();}
				if (st != null){st.close();}
				DataBaseHelper.closeConnection();
				
			}catch (Exception e){e.printStackTrace();}
	
		}
		return resul;	
	}
			
		
	@Override
	public boolean update(Object o) {
	
			boolean resul = false;
			Persona p = null;
			String sql ="";
			PreparedStatement pst = null;
			
			try {
		    	p = (Persona)o; // Casteamos un objeto a persona
				Connection con = DataBaseHelper.getConnection();
				if (p.getFecha() !=null){
					sql = "UPDATE `test` SET `nombre`= ? , `nota`= ?, `telefono` = ?, `fecha` = ? WHERE `id`= ? ;";
				}else{
					sql = "UPDATE `test` SET `nombre`= ? , `nota`= ?, `telefono` = ? WHERE `id`= ? ;";
				}					
				pst = con.prepareStatement(sql);
		    	
				pst.setString (1, p.getNombre() );
				pst.setFloat  (2, p.getNota() );
				pst.setString (3, p.getTelefono() );
				
				if(p.getFecha() !=null ){
					pst.setTimestamp (4, p.getFecha() );
					pst.setInt	  (5, p.getId() );
				}else{
					pst.setInt	  (4, p.getId() );
				}
				
				if ( pst.executeUpdate() == 1){
					resul=true;
				}			
				
			} catch (Exception e) {			
				e.printStackTrace();
			}finally{
				try{
					if ( pst!=null){pst.close();}
					DataBaseHelper.closeConnection();
				}catch(Exception e){ e.printStackTrace();}	
			}
			return resul;
		}

	@Override
	public boolean delete(int id) {
		boolean resul = false;
		PreparedStatement pst = null;
		
		try {
			Connection con = DataBaseHelper.getConnection();
			String sql = "DELETE FROM `test` WHERE id= ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			
			if ( pst.executeUpdate() == 1){
				resul=true;
			}			
			
		}catch (Exception e) {			
			e.printStackTrace();
		}finally{
			try{
				if ( pst!=null){pst.close();}
				DataBaseHelper.closeConnection();
			}catch(Exception e){ e.printStackTrace();}	
		}		
		return resul;
	}
	
	@Override
	public ArrayList<Object> getAprobados() {
		ArrayList<Object> resul = new ArrayList<Object>();
		Statement st = null;
		ResultSet rs = null;
		try{
			Connection con = DataBaseHelper.getConnection();
			st = con.createStatement(); 
	    	String sql = "SELECT * FROM `test` where `nota` >=5 ";
	    	rs = st.executeQuery (sql);
	    	    	
	    	while(rs.next()){
	    		resul.add(mapeo(rs));	
	    	}	
	    
	    	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if ( rs != null){ rs.close();}
				if ( st != null){ st.close();}
				DataBaseHelper.closeConnection();
			}catch(Exception e){e.printStackTrace();}	
		}
		
		return resul;
	}

	@Override
	public ArrayList<Object> getSuspendidos() {
		ArrayList<Object> resul = new ArrayList<Object>();
		Statement st = null;
		ResultSet rs  = null;
		
		try{
			Connection con = DataBaseHelper.getConnection();
			st = con.createStatement(); 
	    	String sql = "SELECT * FROM `test` where `nota` < 5 ";
	    	rs = st.executeQuery (sql);
	    	   	
	    	while(rs.next()){
	    		resul.add( mapeo(rs) );	
	    	}	
	    	
	    	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if ( rs != null){ rs.close();}
				if ( st != null){ st.close();}
				DataBaseHelper.closeConnection();
			}catch(Exception e){e.printStackTrace();}	
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
