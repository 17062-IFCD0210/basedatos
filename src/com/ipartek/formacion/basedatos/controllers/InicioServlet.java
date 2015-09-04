package com.ipartek.formacion.basedatos.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.basedatos.bean.Persona;
import com.ipartek.formacion.basedatos.modelo.DAOPersona;

/**
 * Servlet implementation class InicioServlet
 */
public class InicioServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private RequestDispatcher dispatcher = null;
	
	//modelo DAOPersona
	DAOPersona dao = null;
	
	// Parametros
	private String pID;
	private String pAccion;
	private String pFiltro;
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao = new DAOPersona();
	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,
					HttpServletResponse response) throws ServletException, IOException {
		
		
		// Recoger Parametros: accion, id, filtro
		pID		= request.getParameter("id");
		pAccion	= request.getParameter("accion");
		pFiltro	= request.getParameter("filtro");
		
		// En funcion del Parametro 'accion' realizar la Accion
		// 0: listar
		// 1: Detalle
		// 2: Eliminar
		
		
		// Detalle
		if("1".equals(pAccion)){
			detalle(request, response);
			
		// Eliminar
		}else if("2".equals(pAccion)){
			eliminar(request, response);
			
		// Listar
		}else{
			listar(request, response);
		}
		// Forward
		dispatcher.forward(request, response);
	}
	
	/************************************
	 ***			 LISTAR		      ***
	 ************************************/

	private void listar(HttpServletRequest request, HttpServletResponse response) {
		
			ArrayList<Object> alumnos = new ArrayList<Object>();
			
			if ("0".equals(pFiltro)){
				alumnos = dao.getAprobados();
			}else if ("1".equals(pFiltro)){
				alumnos = dao.getSuspendidos();
			}else{
				alumnos = dao.getAll();
			}
			
			request.setAttribute("alumnos", alumnos);
			
			dispatcher = request.getRequestDispatcher("index.jsp");
		
	}

	/***************************************
	 ***		 	ELIMINAR	  		 
	 * @throws IOException 
	 * @throws ServletException ***
	 ***************************************/

	private void eliminar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			//Recoger parametros
			int pId = Integer.parseInt(request.getParameter("id"));
			
			//Abrir conexion
			Class.forName("com.mysql.jdbc.Driver");
	    	Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/skalada","root", "");
	    	
	    	//Crear SQL
	    	Statement st = conexion.createStatement();
	    	String sql = "DELETE FROM test WHERE id=" + pId + ";";
	    	
			//Ejecutar SQL
	    	if(st.executeUpdate(sql) != 1) {
	    		throw new Exception("No se ha realizado eliminacion " + sql);
	    	}
	    	
			//Cerrar conexion
	    	
	    	conexion.close();
			
			//Volver a la HOME
			request.getRequestDispatcher("inicio").forward(request, response);
		} catch(Exception e) {
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
	}
	
	/*****************************************
	 ***		 	DETALLE	 			   ***
	 *****************************************/

	private void detalle(HttpServletRequest request,
			HttpServletResponse response) {
		
			int id = Integer.parseInt(pID);
			Object alumno = dao.getById(id);	
			request.setAttribute("alumno", alumno);
			dispatcher = request.getRequestDispatcher("form.jsp");
		
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
