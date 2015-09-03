package com.ipartek.formacion.basedatos.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditarServlet
 */
public class EditarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			// Recoger parametros
				String sId = request.getParameter("id");
				int pId = Integer.parseInt(sId);
				
			// Llamar modelo para eliminacion
				Class.forName("com.mysql.jdbc.Driver");
		    	Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/skalada","root", "");
				
		    	Statement st = conexion.createStatement();
		    	String sql = "DELETE FROM `test` WHERE `id`=" + pId + "";
		    	
		    	if ( st.executeUpdate(sql) != 1 ){
		    		
		    		throw new Exception("No se ha realizado eliminiacion: " + sql);
		    	}
		    	
		    	conexion.close();
		    	
			// Volver a la home
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
		}catch (Exception e){
			request.setAttribute( "msg", e.getMessage() );
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
