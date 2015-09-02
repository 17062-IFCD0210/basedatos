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
 * Servlet implementation class EliminarServlet
 */
public class EliminarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			//recoger parametros
			int pID = Integer.parseInt(request.getParameter("id"));
		
			//TODO llamar modelo para inserccion
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost:3306/skalada","root", "");
			
			Statement st = conexion.createStatement();
			
			String sql = "DELETE FROM `test` WHERE `id`=" + pID + ";";
						
			if ( st.executeUpdate(sql) != 1 ){
				throw new Exception("No se ha podido eliminar");
			}
			
			conexion.close();
			
			//Volver a la JDBC
			request.setAttribute("msg_ok", "Registro eliminado");
			request.getRequestDispatcher("JDBC.jsp").forward(request, response);
						
		} catch (Exception e){
			request.setAttribute("msg_error", e.getMessage());
			request.getRequestDispatcher("JDBC.jsp").forward(request, response);
		} 
	}

}
