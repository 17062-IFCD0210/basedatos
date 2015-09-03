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
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Persona> alumnos = new ArrayList<Persona>();
		try {
			//Recoger parametros
			String pFiltro = request.getParameter("filtro");
			
			
			
			//Abrir conexion
			Class.forName("com.mysql.jdbc.Driver");
	    	Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/skalada","root", "");
	    	
	    	//Crear SQL
	    	Statement st = conexion.createStatement();
	    	String sql = null;
	    	if(pFiltro != null) {
	    		switch(pFiltro) {
		    		case "0":
		    			sql = "SELECT * FROM `test` WHERE nota>=5;";
		    		break;
		    		case "1":
		    			sql = "SELECT * FROM `test` WHERE nota<5;";
		    		break;
	    		}
	    	} else {
	    		sql = "SELECT * FROM `test`;";
	    	}

	    	
			//Ejecutar SQL
	    	ResultSet rs = st.executeQuery (sql);
	    	
	    	//Mapeo resultSet => ArrayList<Persona>
	    	Persona p = null;
	    	while(rs.next()) {
	    		p = new Persona(rs.getString("nombre"));
	    		p.setId(rs.getInt("id"));
	    		p.setNombre(rs.getString("nombre"));
	    		p.setNota(rs.getFloat("nota"));
	    		p.setTelefono(rs.getString("telefono"));
	    		p.setFecha(rs.getTimestamp("fecha"));
	    		alumnos.add(p);
	    	}
	    	
	    	
	    	//Cargar atributos en request
	    	request.setAttribute("alumnos", alumnos);
	    	
			//Cerrar conexion
			conexion.close();
			
			//Forward
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage());
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
