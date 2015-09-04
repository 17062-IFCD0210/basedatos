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
import com.ipartek.formacion.basedatos.modelo.DAOPersona;

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

		ArrayList<Object> alumnos = new ArrayList<Object>();
		
		try{
			//recoger parametros			
			String filtro = request.getParameter("filtro");
			
			//llamar modelo
			DAOPersona dao = new DAOPersona();
			
			if ( "0".equals(filtro)){
				alumnos = dao.getAprobados();
			}else if ( "1".equals(filtro)){
				alumnos = dao.getSuspendidos();
			}else{
				alumnos = dao.getAll();
			}	
						
			//cargar atributos en request
	    	request.setAttribute("alumnos", alumnos );
	    	
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage() );
		}
		//forward	
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
