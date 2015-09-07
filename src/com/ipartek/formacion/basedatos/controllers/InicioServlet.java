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
		if(dao.delete(id)){
			request.setAttribute("msg", "Persona eliminada");
		}else{
			request.setAttribute("msg", "No se ha podido ELIMINAR");
		};
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
		
		String sql=null;
		try{
			//recoger parametros
			String sNombre=request.getParameter("nombre");
			String sNota=request.getParameter("nota");		
			String sTelefono=request.getParameter("telefono");
			String sID=request.getParameter("id");
			
			Persona p = new Persona(sNombre);   		
    		//p.setFecha(Date.parse(sFecha));
    		p.setTelefono(sTelefono);
    		p.setNota(Float.parseFloat(sNota));
			p.setId(Integer.parseInt(sID));
			//crear nueva persona
    		if ("-1".equals(sID)){
    			if(dao.save(p)==-1){
    				request.setAttribute("msg", "No se ha podido crear");
    			}else{
    				request.setAttribute("msg", "Persona creada");    				
    			};
    			
    		//modificar persona
    		}else{
    			p.setId(Integer.parseInt(pID));
    			if(dao.update(p)){
    				System.out.println("Persona modificada");
    				request.setAttribute("msg", "Persona modificada");
    			}else{
    				request.setAttribute("msg", "No se ha podido modificar");
    			};
    		}
			
/*			
			pID=request.getParameter("id");
			System.out.println(pID);
			String pNombre = request.getParameter("nombre");
			float pNota = Float.parseFloat(request.getParameter("nota"));
			String pTelefono= request.getParameter("telefono");
			//falta la fecha
			Persona p = null;	    	
    		p = new Persona( pNombre );   		
    		//p.setFecha(Date.parse(sFecha));
    		p.setTelefono(pTelefono);
    		p.setNota(pNota);

    		if ("-1".equals(pID)){
    			dao.save(p);
    		}else{
    			p.setId(Integer.parseInt(pID));
    			if(dao.update(p)){
    				request.setAttribute("msg", "Persona modificada");
    			}else{
    				request.setAttribute("msg", "No se ha podido modificar");
    			};
    		}
	*/		
		}catch ( Exception e){
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage() + "SQL: "+sql);
		}finally{
			//volver a index
			listar(request,response);
		}
		doGet(request, response);
	}

}
