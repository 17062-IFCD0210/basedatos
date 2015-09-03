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

import com.ipartek.formacion.basedatos.bean.Persona;

/**
 * Servlet implementation class EditarServlet
 */
public class EditarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int pId;
       
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
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//Recoger parametros			
			pId = Integer.parseInt(request.getParameter("id"));
			
			//Abrir conexion
			Class.forName("com.mysql.jdbc.Driver");
	    	Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/skalada","root", "");
	    	
	    	//Crear SQL
	    	Statement st = conexion.createStatement();
	    	String sql = "SELECT * FROM `test` WHERE id=" + pId;

	    	
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
	    	}
	    	
	    	
	    	//Cargar atributos en request
	    	request.setAttribute("alumno", p);
	    	
			//Cerrar conexion
			conexion.close();
			
			//Forward
			request.getRequestDispatcher("form.jsp").forward(request, response);
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
		try {
			//Recoger parámetros
			String pNombre = request.getParameter("nombre");
			float pNota = Float.parseFloat(request.getParameter("nota"));
			String pTelefono = request.getParameter("telefono");
			String pFecha = request.getParameter("fecha");
			
			//Abrir conexion
			Class.forName("com.mysql.jdbc.Driver");
	    	Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/skalada","root", "");
	    	
	    	//Crear SQL
	    	Statement st = conexion.createStatement();
	    	String sql = "UPDATE test SET nombre='" + pNombre + "', nota=" + pNota + ", telefono='" + pTelefono + "', fecha='" + pFecha + "' WHERE id=" + pId + ";"; 
	    	
			//Ejecutar SQL
	    	if(st.executeUpdate(sql) != 1) {
	    		throw new Exception("No se ha realizado actualizacion " + sql);
	    	}
	    	
			//Cerrar conexion
	    	
	    	conexion.close();
			
			//Volver a la HOME
			request.getRequestDispatcher("inicio").forward(request, response);
		} catch(Exception e) {
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("form.jsp").forward(request, response);
		}
	}

}
