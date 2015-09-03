package com.ipartek.formacion.basedatosProfe.controllers;

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

import com.ipartek.formacion.basedatosProfe.bean.Persona;

/**
 * Servlet implementation class InicioServlet
 */
public class InicioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InicioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Persona> alumnos = new ArrayList<Persona>();

		try{
			/*
			 * Al arrancar la web entrará por aquí sin ningún parámetro y ejecutará la select de todos los registros
			 * Ya en la web entrará también por aquí si pulsamos Aprobados o Suspendidos iremos añadiendo
			 *  la cláusula WHERE a la sentecia sql
			 */
			
			String condicion = "";
			
			//Recoger parámetros
			if (request.getParameter("filtro") == null){
				
			}else if ("0".equals(request.getParameter("filtro"))){ //Si hemos pulsado el botón de Aprobados
				condicion = " WHERE `nota` >= 5.0 AND `nota`<= 10.0";
			}else if ("1".equals(request.getParameter("filtro"))){ //Si hemos pulsado el botón de Suspendidos
				condicion = " WHERE `nota` < 5.0 AND `nota`>= 0.0";
			}
			
			//Realizar consulta bbdd
			Class.forName("com.mysql.jdbc.Driver");
	        Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/skalada","root", "");
	        Statement st = conexion.createStatement();
	        String sql = "SELECT * FROM `test`" + condicion;
	        ResultSet rs = st.executeQuery(sql);
	        
	        //Mapeo resultSet => ArrayList<Persona>
	        Persona p = null; //Declaramos una variable
	        while (rs.next()){
	        	p = new Persona( rs.getString("nombre") ); //vamos sobreescribiendo la var con cada diferente registro
	        	p.setId(rs.getInt("id"));
	        	p.setFecha(rs.getTimestamp("fecha"));
	        	p.setTelefono(rs.getString("telefono"));
	        	p.setNota(rs.getFloat("nota"));
	        		        	
	        	alumnos.add(p); //Añadimos uno más al ArrayList
	        }
	        
	        //Cargar atributos en request
	        request.setAttribute("alumnos", alumnos); //Enviamos el ArrayList al HOME
	        
		} catch (Exception e){
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage());
		}
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
		
		
		//Hacer un forward
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
