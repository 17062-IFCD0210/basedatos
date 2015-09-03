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
 * Servlet implementation class ActualizarServlet
 */
public class ActualizarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActualizarServlet() {
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
		String sql=null;		
		try{
			//recoger parametros
			int pId= Integer.parseInt(request.getParameter("id"));
			String pNombre = request.getParameter("nombre");
			float pNota = Float.parseFloat(request.getParameter("nota"));
			String pTelefono= request.getParameter("telefono");
			String pFecha = request.getParameter("fecha");
			
			//TODO llamar modelo para atualizacion
			Class.forName("com.mysql.jdbc.Driver");
	    	Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/skalada","root", "");
	    	
	    	Statement st = conexion.createStatement();
	    	sql   = "UPDATE `test` SET `nombre`='" + pNombre + "', `nota`='" + pNota + "', `telefono`='" + pTelefono + "', `fecha`='"+ pFecha+"' WHERE `id`="+ pId + ";";
	    	System.out.println(sql);
	    	//ejecutar insert
	    	if ( st.executeUpdate(sql) != 1){	    		
	    		throw new Exception("No se ha realizado actualizacion: " + sql);	    		
	    	}
	    	
			
	    	conexion.close();
			
			//Volver a la HOME
			request.getRequestDispatcher("inicio").forward(request, response);
			
		}catch ( Exception e){
			
			request.setAttribute("msg", e.getMessage()+ "SQL: "+sql );
			request.getRequestDispatcher("form.jsp").forward(request, response);
		}
	}

}
