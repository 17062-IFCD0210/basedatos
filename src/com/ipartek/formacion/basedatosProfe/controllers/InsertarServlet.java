package com.ipartek.formacion.basedatosProfe.controllers;

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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			//Recoger parámetros
			String pNombre = request.getParameter("nombre");
			Float  pNota   = Float.valueOf( request.getParameter("nota") ); //Si casca lo recoje la excepción. Se debería controlar con javascript lo que se introduce antes de submitar
			String pTelefono = request.getParameter("telefono");

			//TODO llamar modelo para inserción
			Class.forName("com.mysql.jdbc.Driver");
	        Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/skalada","root", "");
			
	        Statement st = conexion.createStatement();
	        String sql = "INSERT INTO `test` (`nombre`, `nota`, `telefono`, `fecha`) VALUES ('" + pNombre + "'," + pNota + ",'" + pTelefono + "', '2015-09-02 10:53:51');";
	        
	        if (st.executeUpdate(sql) != 1){ //Si es diferente de 1 es que no se ha insertado ese único registro
	        	throw new Exception("No se ha realizado la inserción: " + sql);
	        }
	        
	        conexion.close();
			//Volver a la HOME
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}catch  (Exception e){ //Si falla que vuelva al form. Sólo se pueden enviar Atributos, parámetros no
			request.setAttribute("msg", e.getMessage()); //Preparo un mensaje para mostrar al usuario en el formulario
			request.getRequestDispatcher("form.jsp").forward(request, response);
		}
	}

}
