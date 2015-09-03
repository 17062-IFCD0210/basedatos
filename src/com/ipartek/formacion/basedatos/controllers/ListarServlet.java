package com.ipartek.formacion.basedatos.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.basedatos.bean.Persona;

/**
 * Servlet implementation class ListarServlet
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
		
		ArrayList<Persona> alumnos = new ArrayList<Persona>();
		
		try{	
			//Recoger parametros
			String sql;
			int filtro;
			if(request.getParameter("filtro") != null ){
				filtro = Integer.parseInt(request.getParameter("filtro"));
				if (filtro == 1){
					sql = "SELECT * FROM `test`WHERE `nota`>=5;";
				} else {
					sql = "SELECT * FROM `test` WHERE `nota`<5;";
				}
			} else {
				sql = "SELECT * FROM `test`;";
			}
			
			//Realizar consulta BBDD
			Class.forName("com.mysql.jdbc.Driver"); 		
			Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost:3306/skalada","root", "");
			Statement s = conexion.createStatement(); 
			
			ResultSet rs = s.executeQuery (sql);
			
			//mapeo resultSet => Arraylist<Persona>
			Persona p = null;
			
			while (rs.next()) 
			{ 
				p = new Persona(rs.getString("nombre"));				
				p.setId(rs.getInt("id"));
				p.setNota(rs.getFloat("nota"));	
				p.setTelefono(rs.getString("telefono"));
				p.setFecha(rs.getTimestamp("fecha"));
				alumnos.add(p);
			}
		
			conexion.close();
			
			//Cargar atributos en request
			request.setAttribute("alumnos", alumnos);
			request.getRequestDispatcher("JDBC.jsp").forward(request, response);
			
		} catch (Exception e) {
			request.setAttribute("msg_error", e.getMessage());
			request.getRequestDispatcher("JDBC.jsp").forward(request, response);
		}
		
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
