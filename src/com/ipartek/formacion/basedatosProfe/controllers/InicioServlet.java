package com.ipartek.formacion.basedatosProfe.controllers;

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

import com.ipartek.formacion.basedatosProfe.bean.Persona;
import com.ipartek.formacion.basedatosProfe.modelo.DAOPersona;
import com.ipartek.formacion.basedatosProfe.modelo.DataBaseHelper;
import com.ipartek.formacion.basedatosProfe.util.UtilidadesFecha;
import com.sun.org.apache.bcel.internal.generic.DASTORE;

/**
 * Servlet implementation class InicioServlet
 */
public class InicioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private RequestDispatcher dispatcher = null;
	//Modelo DAOPersona
	DAOPersona dao = null;
	
	//Parámetros
	private String pID;
	private String pAccion;
	private String pFiltro;
       
	@Override //Escribir init y elejir éste
	public void init(ServletConfig config) throws ServletException {
		//Se ejecuta una única vez 
		super.init(config);
		dao = new DAOPersona();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Como no es de tipo submit, no viene de un formulario, será doGet (eliminar, consultar/listar)
		
		ArrayList<Object> alumnos = new ArrayList<Object>();

		try{
			/*
			 * Al arrancar la web entrará por aquí sin ningún parámetro y ejecutará la select de todos los registros
			 * Ya en la web entrará también por aquí si pulsamos Aprobados o Suspendidos iremos añadiendo
			 *  la cláusula WHERE a la sentecia sql
			 */
			
			//Recoger parámetros: acción, id, filtro
			pID = request.getParameter("id");
			pAccion = request.getParameter("accion");
			pFiltro = request.getParameter("filtro");
			
			
			//En función del parámetro 'accion' realizar la Acción
			//0.- Listar
			//1.- Detalle
			//2.- Eliminar
			
			//Detalle
			if ("1".equals(pAccion)){
				detalle(request,response);
				
			//Eliminar
			}else if ("2".equals(pAccion)){
				eliminar(request,response);
			
			//Crear nueva Persona
			}else if ("3".equals(pAccion)){
				nuevo(request, response);
				
			//Listar
			}else{
				listar(request,response);
			}

			//forward
			dispatcher.forward(request, response); //dispatcher variará según lo que haya hecho, listar, insertar, ...
	        
		} catch (Exception e){
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage());
		}
		
	}



	private void nuevo(HttpServletRequest request, HttpServletResponse response) {
		
		dispatcher = request.getRequestDispatcher("form.jsp");
	}

	private void editar(HttpServletRequest request, HttpServletResponse response) {
		
		int id = Integer.valueOf(pID);
		
		Object alumno = dao.getById(id);
		
		if (alumno == null){
			request.setAttribute("msg", "No se ha encontrado el alumno");
			listar(request, response); //Sino se pone como ha cacheado en memoria el index entraría en un bucle infinito
		}else{
			request.setAttribute("alumno", alumno);			
			dispatcher = request.getRequestDispatcher("form.jsp");
		}
		
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) {
		
		ArrayList<Object> alumnos = new ArrayList<Object>();
		
		if (pFiltro == null){
			alumnos = dao.getAll();
		}else if ("0".equals(pFiltro)){ //Si hemos pulsado el botón de Aprobados
			alumnos = dao.getAprobados();
		}else if ("1".equals(pFiltro)){ //Si hemos pulsado el botón de Suspendidos
			alumnos = dao.getSuspendidos();
		}
		
		request.setAttribute("alumnos", alumnos); //Le pasamos un atributo que es la variable alumnos con el nombre 'alumnos'
		
		dispatcher = request.getRequestDispatcher("index.jsp");
	}

	private void eliminar(HttpServletRequest request,
			HttpServletResponse response) {
		
		int id = Integer.valueOf(pID);
		if (dao.delete(id)){
			request.setAttribute("msg", "Registro eliminado con éxito");
		}else{
			request.setAttribute("msg", "No se ha podido eliminar el registro");
		}
		
		listar(request, response); //Sino se pone como ha cacheado en memoria el index entraría en un bucle infinito
		
	}

	private void detalle(HttpServletRequest request,
			HttpServletResponse response) {
		
		int id = Integer.valueOf(pID); //o con Integer.parseInt(ipID ya que es un String)
		Object alumno = dao.getById(id);
		
		request.setAttribute("alumno", alumno);
		dispatcher = request.getRequestDispatcher("form.jsp");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Desde un formulario es de tipo doPost (insertar y modificar registros)
		
		try{
			String sNombre = request.getParameter("nombre");
			String sNota = request.getParameter("nota");
			String sTelefono = request.getParameter("telefono");
			String sFecha = request.getParameter("fecha");
			String sId = request.getParameter("id"); //El campo hidden
			
			//Mapear Persona
			Persona p = new Persona(sNombre);
			p.setId(Integer.parseInt(sId));
			p.setNota(Float.parseFloat(sNota));
			p.setTelefono(sTelefono);
			p.setFecha(UtilidadesFecha.stringToTimeStamp(sFecha));
			
			//TODO fecha
			
			if ("-1".equals(sId)){ //insertar nueva persona
				dao.save(p);
				request.setAttribute("msg", "El nuevo alumno se ha añadido");
			}else if ("-1".equals(sId)){
				if (dao.save(p) == 1){
					request.setAttribute("msg", "El alumno se ha creado con éxito");
				}else{
					request.setAttribute("msg", "El alumno NO se ha podido crear");
				}
			}else{ //Modificar persona
				if (dao.update(p)){
					request.setAttribute("msg", "El alumno se ha modificado con éxito");
				}else{
					request.setAttribute("msg", "El alumno NO se ha modificado para " + p.getNombre());
				}
				
			}
			
			listar(request, response); //que vaya a la index
		
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage());
		}finally{
			listar(request, response); //que vaya a la index
			dispatcher.forward(request, response);
		}

	}

}
