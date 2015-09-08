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
		
			//Recoger parametros
			int id = Integer.parseInt( pID );
			
			if ( dao.delete( id ) ){
				request.setAttribute("msg", "Eliminado con exito");
			}else{
				request.setAttribute("msg", "No se ha podido Eliminar");
			}
			
			listar(request, response);
			
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
		try{
		//recoger parametros copiar los names de la form.jsp para no confuncirnos
		String sNombre 		= request.getParameter("nombre");
		String sNota 		= request.getParameter("nota");
		String sTelefono 	= request.getParameter("telefono");
		String sFecha 		= request.getParameter("fecha");
		String sID			= request.getParameter("id");
		
		//mapear persona
		Persona p = new Persona(sNombre);
		p.setId(Integer.parseInt(sID) );
		p.setNota(Float.parseFloat(sNota) );
		p.setTelefono(sTelefono);
		//TODO fecha
			
		
		//crear nueva persona
		if( "-1".equals(sID) ){
			dao.save(p);
			request.setAttribute("msg", "Nueva persona creada");
		//modificar persona
		}else{
			
			if (dao.update(p) ){
			request.setAttribute("msg", p.getNombre() + " Persona modificada con exito");
			}else{
				request.setAttribute("msg", p.getNombre() + " No se ha realizado la modificacion");
			}
		}
		}catch (Exception e){
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage() );
		}finally{
			//volver index
			listar(request, response);
		}
		
	
	}

}
