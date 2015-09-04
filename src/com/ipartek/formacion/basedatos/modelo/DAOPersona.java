package com.ipartek.formacion.basedatos.modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.ipartek.formacion.basedatos.bean.Persona;

public class DAOPersona {

	
	public ArrayList<Persona> getAll(){
		
		ArrayList<Persona> resul = new ArrayList<Persona>();
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
	
}
