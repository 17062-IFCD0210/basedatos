package com.ipartek.formacion.basedatos.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.basedatos.bean.Persona;

/**
 * Servlet implementation class InicioServlet
 */
public class InicioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InicioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Persona> alumnos = new ArrayList<Persona>();
		
		try{	
			//recoger parametros
			
			//realizar consulta bbdd
			
			Class.forName("com.mysql.jdbc.Driver");
	    	Connection con = DriverManager.getConnection ("jdbc:mysql://localhost/skalada","root", "");
	    	Statement st = con.createStatement(); 
	    	String sql = "SELECT * FROM `test`";
	    	ResultSet rs = st.executeQuery (sql);
	    	
	    	//mapeo resultSet => ArrayList<Persona>
	    	Persona p = null;   	
	    	while( rs.next() ){
	    		//creamos una nueva persona y la meteremos en el arrayList Persona( alumnos.add(p) )
	    		p = new Persona( rs.getString("nombre") );
	    		
	    		p.setId( rs.getInt("id"));
	    		p.setFecha( rs.getTimestamp("fecha"));
	    		p.setTelefono( rs.getString("telefono"));
	    		p.setNota( rs.getFloat("nota"));
	    		
	    		alumnos.add(p);
	    	}
	    	
			//cargar atributos en request
	    	request.setAttribute("alumnos", alumnos);
	    	
	    	
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage() );
		}	
		//forward,vamos a la index
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
