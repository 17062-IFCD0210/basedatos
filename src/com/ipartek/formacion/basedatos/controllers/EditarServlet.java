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
		String sql=null;
		try{		
			//Busca en la BD el registro a editar
			//recoger parametros
			int pId = Integer.parseInt(request.getParameter("id"));		
			
			//TODO llamar modelo para inserccion
			Class.forName("com.mysql.jdbc.Driver");
	    	Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/skalada","root", "");
	    	
	    	Statement st = conexion.createStatement();
	    	sql = "SELECT * FROM `test` WHERE `id`="+pId+";";
	   		ResultSet rs = st.executeQuery (sql);

	   		//mapeo resulSet ==> Persona
	      	Persona p = null;
	      	rs.first();
      		p = new Persona(rs.getString("nombre"));
      		p.setId(rs.getInt("id"));
      		p.setNota(rs.getFloat("nota"));
      		p.setTelefono(rs.getString("telefono"));
      		p.setFecha(rs.getTimestamp("fecha"));
	      	System.out.println(p.toString());
			//cargar atributos en request
	      	request.setAttribute("alumno", p);
			request.getRequestDispatcher("form.jsp").forward(request, response);
			
		}catch ( Exception e){
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage()+ "SQL: "+sql );
			request.getRequestDispatcher("inicio").forward(request, response);
		}			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
