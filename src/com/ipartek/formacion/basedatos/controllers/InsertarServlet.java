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
 * Servlet implementation class InsertarServlet
 */
public class InsertarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			//recoger parametros
			String pNombre = request.getParameter("nombre");
			float pNota = Float.parseFloat(request.getParameter("nota"));
			String pTelefono = request.getParameter("telefono");
			
			//TODO llamar modelo para inserccion
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost:3306/skalada","root", "");
			
			Statement st = conexion.createStatement();
			//String sql = "INSERT INTO `test` (`nombre`, `nota`) VALUES ('" + pNombre + "', 5.00);";
			String sql = "INSERT INTO `test` (`nombre`, `nota`, `telefono`) VALUES ('" + pNombre + "', " + pNota + ", '" + pTelefono + "');";
			
			
			if ( st.executeUpdate(sql) != 1 ){
				throw new Exception("No se ha realizado insercion");
			}
			
			conexion.close();
			
			//Volver a la HOME
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} catch (Exception e){
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("form.jsp").forward(request, response);
		}
	}

}
