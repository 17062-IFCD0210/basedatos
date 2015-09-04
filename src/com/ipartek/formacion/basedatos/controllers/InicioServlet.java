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

import sun.rmi.server.Dispatcher;

import com.ipartek.formacion.basedatos.bean.Persona;
import com.ipartek.formacion.basedatos.modelo.DAOPersona;



/**
 * Servlet implementation class InicioServlet
 */
public class InicioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RequestDispatcher dispatcher = null;
	//modelo DAOPerson
	DAOPersona dao= null;
	
	//paramatros
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//recoger parametros: accion, id, filtro
		pID          = request.getParameter("id");
		pAccion      = request.getParameter("accion");
		pFiltro    = request.getParameter("filtro");
		
		if("1".equals(pAccion)){
			//detalle
			detalle(request, response);
			
		}else if("2".equals(pAccion)){
			//eliminar
			eliminar(request, response);
			
		}else if("3".equals(pAccion)){
			//insetar
			insertar(request, response);
			
		}else{
			//listar
			listar(request, response);
		}
		dispatcher.forward(request, response);
		
		
	}		
		


	private void insertar(HttpServletRequest request,
			HttpServletResponse response) {
		
		int id = Integer.valueOf(pID);
		Object alumno = dao.getById(id);
	
		request.setAttribute("alumnos", alumno );
		
		
		dispatcher = request.getRequestDispatcher( "form.jsp" );
	}


	private void listar(HttpServletRequest request, HttpServletResponse response) {
		
		ArrayList<Object> alumnos = new ArrayList<Object>();
		if ( "0".equals(pFiltro)){
			alumnos = dao.getAprobados();
		}else if ( "1".equals(pFiltro)){
			alumnos = dao.getSuspendidos();
		}else{
			alumnos = dao.getAll();
		}	
		request.setAttribute("alumnos", alumnos );
		
		dispatcher = request.getRequestDispatcher( "index.jsp" );
		
	}


	private void eliminar(HttpServletRequest request,
			HttpServletResponse response) {
		ArrayList<Object> alumno = new ArrayList<Object>();
		int id = Integer.valueOf(pID);
		dao.delete(id);
		alumno= dao.getAll();
		
	
		request.setAttribute("alumnos", alumno );
		
		dispatcher = request.getRequestDispatcher( "index.jsp" );
		
	}


	private void detalle(HttpServletRequest request,
			HttpServletResponse response) {
		
		
		int id = Integer.valueOf(pID);
		Object alumno = dao.getById(id);
	
		request.setAttribute("alumnos", alumno );
		
		dispatcher = request.getRequestDispatcher( "form.jsp" );
		
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	
	}

}
