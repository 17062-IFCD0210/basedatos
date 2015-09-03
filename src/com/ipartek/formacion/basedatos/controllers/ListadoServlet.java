package com.ipartek.formacion.basedatos.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ListadoServlet
 */
public class ListadoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListadoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//Recoger parametro accion
			int pAccion = Integer.parseInt(request.getParameter("accion"));
			
			//Abrir conexion
			Class.forName("com.mysql.jdbc.Driver");
	    	Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/skalada","root", "");
	    	
	    	//Crear SQL
	    	Statement st = conexion.createStatement();
	    	String sqlSus = "SELECT * FROM `test` WHERE nota < 5;";
	    	String sqlApr = "SELECT * FROM `test` WHERE nota >= 5;";
	    	String sqlTodos = "SELECT * FROM `test`;";
	    	
			//Ejecutar SQL
	    	switch (pAccion) {
	    		case 1:
	    			ResultSet rs = st.executeQuery (sqlApr);
	    		break;
	    		case 2:
	    			ResultSet rs2 = st.executeQuery (sqlSus);
	    		break;
	    		case 3:
	    			ResultSet rs3 = st.executeQuery (sqlApr);
	    		break;
	    	}
	    	
	    	
	    	
			//Cerrar conexion
	    	
	    	conexion.close();
			
			//Volver a la HOME
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} catch(Exception e) {
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("form.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}