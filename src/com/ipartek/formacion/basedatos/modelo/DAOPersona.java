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
 * Case especializada en mapear una (@code Persona) contra la base de datos
 * dispone de los metodos basicos para realizar las operaciones de CRUD
 * @author ARL
 *
 */
public class DAOPersona implements IDAOPersona
{

	/**
	 * recupera todas las Personas y te las rertorna
	 * @return {@code ArrayList<Persona>}listado Personas
	 */
	@Override
	public ArrayList<Object> getAll(){
		
		ArrayList<Object> resul = new ArrayList<Object>();
		try{
			Connection con=DataBaseHelper.getConnection();
			String sql = "SELECT * FROM `test` ";
			PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS );
			
	    	ResultSet rs = pst.executeQuery (sql);
	    	
	    	//mapeo resultSet => ArrayList<Persona>
	    	Persona p = null;	    	
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
	 * agrega una nueva persona a la DDBB y te devuelve el id de la misma
	 * 
	 */
	@Override
	public int save(Object o) {

		String sql="";
		int resul = -1;
		Persona p=null;
		Connection con=null;
		PreparedStatement pst =null;
		try{
			con = DataBaseHelper.getConnection();
			p = (Persona)o;
			
			if(p.getFecha()!=null)
			{sql = "INSERT INTO `test` (`nombre`, `nota`, `telefono`,`fecha`) VALUES ( ?, ?, ?, ?);";}
			else
			{sql = "INSERT INTO `test` (`nombre`, `nota`, `telefono`) VALUES ( ?, ?, ?);";}
			
			pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS );
			pst.setString(1, p.getNombre() );
			pst.setFloat (2, p.getNota());
			pst.setString(3, p.getTelefono() );
			pst.setTimestamp(4, p.getFecha() );
			
			if ( pst.executeUpdate() == 1 ){
				ResultSet rsKeys = pst.getGeneratedKeys();
				if ( rsKeys.next() ){
					resul = rsKeys.getInt(1);
				}else{
					throw new SQLException("No se ha podido generar ID");
				}
			}
			
		}catch( Exception e){
			e.printStackTrace();
		}finally{
			DataBaseHelper.closeConnection();
		}
		
		
		return resul;
	}

	
	/**
	 * Funcion que te muestra los datos del alumno seleccionado
	 */
	@Override
	public Object getById(int id) {
		Object resul = new Object();
		try{
			Connection conexion=DataBaseHelper.getConnection();
			Statement st = conexion.createStatement(); 
	    	String sql = "SELECT * FROM `test`where id="+id;
	    	ResultSet rs = st.executeQuery (sql);
	    	
	    	//mapeo resultSet => ArrayList<Persona>
	    	Persona p = null;	    	
	    	while(rs.next()){
	    		resul=mapeo(rs);
	    	}
	    	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DataBaseHelper.closeConnection();
		}
		
		return resul;
	}

	/**
	 * Funcion que te modifica los datos de un alumno
	 */
	@Override
	public boolean update(Object o) {
		// TODO Auto-generated method stub
		Persona p=(Persona)o;
		boolean modificado=false;
		String sql="";
		try
		{
			Connection conexion=DataBaseHelper.getConnection();
	    	PreparedStatement pst= conexion.prepareStatement(sql);
	    
			if(p.getFecha()!=null)
			{sql="UPDATE `test` SET `nombre`=?, `nota`=?, `telefono`=?, `fecha`=? WHERE  `id`=?;" ;
				pst.setInt(5, p.getId());
				pst.setTimestamp(4, p.getFecha() );
			}
			else
			{
				sql="UPDATE `test` SET `nombre`=?, `nota`=?, `telefono`=? WHERE  `id`=?;" ;
				pst.setInt(4, p.getId());
					
			}
			
			pst.setString(1, p.getNombre());
	    	pst.setFloat(2, p.getNota());
	    	pst.setString(3, p.getTelefono());
	    
	    	if(pst.executeUpdate()==1)
	    	{modificado=true;}	 
	    	else{modificado=false;}
		}
		catch(Exception e)
			{modificado=false;}
		finally{
			DataBaseHelper.closeConnection();
	}
		return modificado;
	}

	
	
	/**
	 * Funcion que te borra el registro seleccionado
	 */
	@Override
	public boolean delete(int id) {
		
		//variable que te controla si se
		//ha borrado correctamente el elemento de la DDBB
		boolean borrado=false; 
		try
		{
		 	String sql="DELETE FROM `test` WHERE `id`=?" ;
			Connection conexion=DataBaseHelper.getConnection();
	    	PreparedStatement pst= conexion.prepareStatement(sql);
	    	pst.setInt(1, id);
	    	if(pst.executeUpdate()==1)
	    	{borrado=true;}	 
	    	else{borrado=false;}
	    	
		}catch(Exception e){e.printStackTrace();
		}finally{DataBaseHelper.closeConnection();}
		return borrado;
	}

	/**
	 * Te muestra un listado de los alumnos aprobados
	 */
	@Override
	public ArrayList<Object> getAprobados() {
		ArrayList<Object>alumnos=new ArrayList<Object>();
		ResultSet rs=null;
		try{
			Connection conexion=DataBaseHelper.getConnection();
	    	Statement st = conexion.createStatement();
	    	String sql="SELECT * FROM `test` WHERE `nota`>=5";
	    	rs=st.executeQuery(sql);
	    	if (rs==null){	    		
	    		throw new Exception("No se puede mostrar correctamente: " + sql);}
	    	else{
	    		while(rs.next()){alumnos.add(mapeo(rs));}		
	    	}
	}catch(Exception e){
		e.printStackTrace();
	}finally{DataBaseHelper.closeConnection();}
		return alumnos;
	}

	/**
	 * Te muestra un listado de los alumnos suspendidos
	 */
	@Override
	public ArrayList<Object> getSuspendidos() {
		
		ArrayList<Object>alumnos=new ArrayList<Object>();
		ResultSet rs=null;
		try{
			Connection conexion=DataBaseHelper.getConnection();
	    	Statement st = conexion.createStatement();
	    	String sql="SELECT * FROM `test` WHERE `nota`<5";
	    	rs=st.executeQuery(sql);
	    	if (rs==null){	    		
	    		throw new Exception("No se puede mostrar correctamente: " + sql);}
	    	else{
	    		while(rs.next()){ alumnos.add(mapeo(rs));}		
	    	}
	    
	}catch(Exception e){
		e.printStackTrace();
	
	}finally{DataBaseHelper.closeConnection();}
		return alumnos;
	}
	
	
	/**
	 * Encapsula un objeto extrayendolo del resulset
	 * @param datos
	 * @return
	 */
	private Persona mapeo(ResultSet rs)throws SQLException
	{
		Persona p=null;
		p = new Persona( rs.getString("nombre") );
		p.setId( rs.getInt("id"));
		p.setFecha( rs.getTimestamp("fecha"));
		p.setTelefono(rs.getString("telefono"));
		p.setNota(rs.getFloat("nota"));		
		return p;
	}
	
	
	
}