package com.ipartek.formacion.basedatosProfe.controllers;

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
public class ListarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarServlet() {
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
		
		if (Integer.valueOf(request.getParameter("accion")) == 0){
			//Consultamos la tabla test y cogemos los datos del registro
			try{
				//Recoger parámetros
				String pID = request.getParameter("id");
	
				//TODO llamar modelo para inserción
				Class.forName("com.mysql.jdbc.Driver");
		        Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/skalada","root", "");
				
		        Statement st = conexion.createStatement();
		        String sql = "select * from test";
		        
		        ResultSet rs = st.executeQuery (sql);
		        request.setAttribute("rs", rs); //Preparo el recordset para enviarlo al formulario y lo muestre por pantalla
	
		        conexion.close();
				//Volver a la HOME
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}catch  (Exception e){ //Si falla que vuelva al form. Sólo se pueden enviar Atributos, parámetros no
				request.setAttribute("msg", e.getMessage()); //Preparo un mensaje para mostrar al usuario en el formulario
				request.getRequestDispatcher("form.jsp").forward(request, response);
			}
		}else if (Integer.valueOf(request.getParameter("accion")) == 1){
			//Editamos el registro
			try{
				//Recoger parámetros
				String pID = request.getParameter("id");
				String pNombre = request.getParameter("nombre");
				Float pNota = Float.valueOf(request.getParameter("nota"));
				String pTelefono = request.getParameter("telefono");
	
				//TODO llamar modelo para inserción
	
				//Volver a la HOME
				request.getRequestDispatcher("form.jsp").forward(request, response);
			}catch  (Exception e){ //Si falla que vuelva al form. Sólo se pueden enviar Atributos, parámetros no
				request.setAttribute("msg", e.getMessage()); //Preparo un mensaje para mostrar al usuario en el formulario
				request.getRequestDispatcher("form.jsp").forward(request, response);
			}
		}
	}
}
