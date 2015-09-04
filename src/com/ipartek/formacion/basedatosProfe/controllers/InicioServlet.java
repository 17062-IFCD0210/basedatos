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
				
			//Elimnar
			}else if ("2".equals(pAccion)){
				eliminar(request,response);
			
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
	
		
		
		
		//Hacer un forward
		
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
		doGet(request, response);
	}

}
