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
		String sql = null;
		
		try{	
			//Recoger parametros			
			String filtro = request.getParameter("filtro");
			String order = request.getParameter("order");
			String column = request.getParameter("column");
			if("1".equals(filtro)){		//APROBADOS		
				sql = "SELECT * FROM `test` WHERE `nota`>=5 ";
				if ("asc".equals(order)){
					sql = sql + "ORDER BY `"+column+"` ASC;";
				}
				if ("desc".equals(order)){
					sql = sql + "ORDER BY `"+column+"` DESC;";
				}
			} else if("2".equals(filtro)){		//SUSPENDIDOS
				sql = "SELECT * FROM `test` WHERE `nota`<5 ";
				if ("asc".equals(order)){
					sql = sql + "ORDER BY `"+column+"` ASC;";
				}
				if ("desc".equals(order)){
					sql = sql + "ORDER BY `"+column+"` DESC;";
				}
			} else {		//TODOS
				sql = "SELECT * FROM `test` ";
				if ("asc".equals(order)){
					sql = sql + "ORDER BY `"+column+"` ASC;";
				}
				if ("desc".equals(order)){
					sql = sql + "ORDER BY `"+column+"` DESC;";
				}
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
			request.setAttribute("filtro", filtro);
			request.getRequestDispatcher("JDBC.jsp").forward(request, response);
			
		} catch (Exception e) {
			request.setAttribute("msg_error", e.getMessage()+sql);
			
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
