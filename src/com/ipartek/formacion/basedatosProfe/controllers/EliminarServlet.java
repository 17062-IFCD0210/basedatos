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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			//Recoger par�metros
			String pID = request.getParameter("id");

			//TODO llamar modelo para inserci�n
			Class.forName("com.mysql.jdbc.Driver");
	        Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/skalada","root", "");
			
	        Statement st = conexion.createStatement();
	        String sql = "DELETE FROM `test` WHERE  `id`=" + pID;
	        
	        if (st.executeUpdate(sql) != 1){ //Si es diferente de 1 es que no se ha insertado ese �nico registro
	        	throw new Exception("No se ha realizado el borrado: " + sql);
	        }
	        
	        conexion.close();
			//Volver a la HOME
	        request.setAttribute("msg","El registro " + pID + " ha sido borrado satisfactoriamente");
			request.getRequestDispatcher("index.jsp").forward(request, response); //Te devuelve a la HOME con un mensaje
			
		}catch  (Exception e){ //Si falla que vuelva al form. S�lo se pueden enviar Atributos, par�metros no
			request.setAttribute("msg", e.getMessage()); //Preparo un mensaje para mostrar al usuario en el formulario
			request.getRequestDispatcher("form.jsp").forward(request, response);
		}
	}

}
