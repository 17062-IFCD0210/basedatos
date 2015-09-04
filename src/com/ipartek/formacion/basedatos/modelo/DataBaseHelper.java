package com.ipartek.formacion.basedatos.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DataBaseHelper {
	
	static final public String DRIVER    = "com.mysql.jdbc.Driver";
	static final public String SERVER    = "localhost";
	static final public String DATA_BASE = "skalada";
	static final public String USER      = "root";
	static final public String PASS      = "";
	
	private static Connection con;
	
	public static Connection getConnection() throws Exception{
		
		if ( con == null ){
			Class.forName(DRIVER);
			con = DriverManager.getConnection ("jdbc:mysql://localhost/skalada","root", "");
		}
		return con;
	}
	
	public static boolean closeConnection(){
		boolean resul = false;
		try{
			con.close();
			con=null;
			resul = true;
		}catch ( SQLException e){
			con=null;
			e.printStackTrace();
			resul = false;
		}
		return resul;
	}
	

	
}
