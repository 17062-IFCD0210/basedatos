package com.ipartek.formacion.basedatos.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

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
	//Modelo DAOPersona
	DAOPersona dao = null;
	
	//parametros
	private String pAccion;
	private String pID; 
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
		
		//Recoger parametros: accion, id, filtro
		pID = request.getParameter("id");
		pAccion = request.getParameter("accion");
		pFiltro = request.getParameter("filtro");
		
		//En funcion del parametro 'accion' realizar la accion
		// 0: Listar
		// 1: Detalle
		// 2: Eliminar

		//Detalle
		if("1".equals(pAccion)){
			detalle(request, response);
			
		//Eliminar
		}else if("2".equals(pAccion)){
			eliminar(request, response);
			
		//Listar
		}else {
			listar(request, response);
		}
		
		//forward
		dispatcher.forward(request, response);
				
		
	/*	
		
		ArrayList<Object> alumnos = new ArrayList<Object>();
		
		try{
			//recoger parametros
			String filtro = request.getParameter("filtro");
			
			//llamar al modelo
			DAOPersona dao = new DAOPersona();
			if("0".equals(filtro)){
				alumnos=dao.getAprobados();
			}else if ("1".equals(filtro)){
				alumnos=dao.getSuspendidos();
			}else{
				alumnos = dao.getAll();
			}
	      	
			//cargar atributos en request
	      	request.setAttribute("alumnos", alumnos);
	      	
		}catch ( Exception e){			
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage() );
		}	      	
		//forward
		request.getRequestDispatcher("index.jsp").forward(request, response);
*/			
	}

	

	private void listar(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Object> alumnos = new ArrayList<Object>();
		
		if("0".equals(pFiltro)){
			alumnos=dao.getAprobados();
		}else if ("1".equals(pFiltro)){
			alumnos=dao.getSuspendidos();
		}else{
			alumnos = dao.getAll();
		}
      	
		//cargar atributos en request
      	request.setAttribute("alumnos", alumnos);
      	
      	dispatcher = request.getRequestDispatcher("index.jsp");
      	
	}

	private void eliminar(HttpServletRequest request,
			HttpServletResponse response) {
		
		int id = Integer.parseInt(pID);
		dao.delete(id);
		//dispatcher = request.getRequestDispatcher("inicio?accion=0");
		listar(request, response);
		
	}

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
		
		String sql=null;
		try{
			//recoger parametros
			pID=request.getParameter("id");
			System.out.println(pID);
			String pNombre = request.getParameter("nombre");
			float pNota = Float.parseFloat(request.getParameter("nota"));
			String pTelefono= request.getParameter("telefono");
			Date pFecha;
			if(request.getParameter("fecha")!=null){
				//pFecha = Date.parse(request.getParameter("fecha"));
			}			
			
	    	Persona p = null;	    	
    		p = new Persona( pNombre );
    		//p.setFecha(pFecha);
    		p.setTelefono(pTelefono);
    		p.setNota(pNota);

    		if ("-1".equals(pID)){
    			dao.save(p);
    		}else{
    			dao.update(p);
    		}
			
    		dispatcher = request.getRequestDispatcher("inicio?accion=0");
			
		}catch ( Exception e){
			
			request.setAttribute("msg", e.getMessage() + "SQL: "+sql);
			dispatcher = request.getRequestDispatcher("form.jsp");
		}
		//forward
		listar(request,response);
		//dispatcher.forward(request, response);		
	}

}
