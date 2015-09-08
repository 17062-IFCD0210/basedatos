package com.ipartek.formacion.basedatos.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.basedatos.bean.Persona;
import com.ipartek.formacion.basedatos.modelo.DAOPersona;
import com.ipartek.formacion.basedatos.util.UtilidadesFecha;


/**
 * Servlet implementation class InicioServlet
 */
public class InicioServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private RequestDispatcher dispatcher = null;
	
	// Modelo DAOPersona
	DAOPersona dao = null;

	// parametros get
	private String pID;
	private String pAccion;
	private String pFiltro;
	
	// parametros post
	private String pNombre;
	private String pTelefono;
	private float pNota;
	private String pFecha;

	@Override
	public void init(ServletConfig config) throws ServletException {		
		super.init(config);		
		dao = new DAOPersona();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Recoger Parametros: accion, id, filtro
		pID     = request.getParameter("id");
		pAccion = request.getParameter("accion");
		pFiltro = request.getParameter("filtro");
		
		// En funcion del parametro 'accion' realizar la Accion
		// 0: listar
		// 1: Detalle
		// 2: Eliminar
		// 3: Nuevo
		// 4: Detalle_elim
		
		if("1".equals(pAccion)){
			detalle(request, response);	
		}else if("2".equals(pAccion)){		
			eliminar(request, response);
		}else if("3".equals(pAccion)){		
			nuevo(request, response);
		}else if("4".equals(pAccion)){			
			detalle_elim(request, response);
		}else {
			listar(request, response);
		}
		
		dispatcher.forward(request, response);
		
	}

	private void nuevo(HttpServletRequest request, HttpServletResponse response) {
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
		
		int id = Integer.parseInt(pID);
		
		if(dao.delete(id)) {
			request.setAttribute("msg", "Registro Eliminado");
		} else {
			request.setAttribute("msg", "Registro NO Eliminado");
		}
					
		//Listar todos los datos en la tabla
		listar(request, response);
		
		//Volver a la HOME
		request.getRequestDispatcher("inicio");
	}

	private void detalle(HttpServletRequest request,
			HttpServletResponse response) {
		
			int id = Integer.parseInt(pID);
			Object alumno = dao.getById(id);				
			request.setAttribute("alumno", alumno );					
			dispatcher = request.getRequestDispatcher( "form.jsp" );		
		
	}
	
	private void detalle_elim(HttpServletRequest request,
			HttpServletResponse response) {
			
			int id = Integer.parseInt(pID);
			Object alumno = dao.getById(id);				
			request.setAttribute("alumno", alumno );							
			dispatcher = request.getRequestDispatcher( "index.jsp" );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// 5: Accion insertar nuevo
		
		//Recoger Parametros
		pNombre = request.getParameter("nombre");
		pNota = Float.parseFloat(request.getParameter("nota"));
		pTelefono = request.getParameter("telefono");
		pFecha = request.getParameter("fecha");
		pAccion = request.getParameter("accion");
		
		int i = 0;
		
		Persona p = new Persona(pNombre);
		p.setNota(pNota);
		p.setTelefono(pTelefono);
		p.setFecha(UtilidadesFecha.stringToTimestamp(pFecha));
		
		if("5".equals(pAccion)) {
			i = dao.save(p);
			request.setAttribute("msg", "Se ha creado con id" + i);
		} else {
			p.setId(Integer.parseInt(pID));
			dao.update(p);
		}
		
		listar(request, response);
		dispatcher.forward(request, response);
	}

}