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
			//recoger parametros
			int pID = Integer.parseInt(request.getParameter("id"));
		
			//TODO llamar modelo para inserccion
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost:3306/skalada","root", "");
			
			Statement st = conexion.createStatement();
			
			String sql = "SELECT `nombre`, `nota`, `telefono` FROM `test` WHERE  `id`=" + pID + ";";
						
			ResultSet rs = st.executeQuery(sql);
			
			rs.next();
			request.setAttribute("nombre", rs.getString("nombre") );
			request.setAttribute("nota", rs.getString("nota") );
			request.setAttribute("telefono", rs.getString("telefono") );
			
			conexion.close();
			
			//Ir a form_mod
			request.getRequestDispatcher("form_mod.jsp").forward(request, response);
						
		} catch (Exception e){
			request.setAttribute("msg_error", e.getMessage());
			request.getRequestDispatcher("JDBC.jsp").forward(request, response);
		} 
	}

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
			
			String sql = "UPDATE `test` SET `nombre`='hola' WHERE `id`=0;";
						
			if ( st.executeUpdate(sql) != 1 ){
				throw new Exception("No se ha podido editar");
			}
			
			conexion.close();
			
			//Volver a la JDBC
			request.setAttribute("msg_ok", "Registro insertado");
			request.getRequestDispatcher("JDBC.jsp").forward(request, response);
			
		} catch (Exception e){
			request.setAttribute("msg_error", e.getMessage());
			request.getRequestDispatcher("form.jsp").forward(request, response);
		}
	}

}