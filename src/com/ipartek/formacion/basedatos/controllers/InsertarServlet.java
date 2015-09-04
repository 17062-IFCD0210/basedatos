package com.ipartek.formacion.basedatos.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.basedatos.bean.Persona;
import com.ipartek.formacion.basedatos.modelo.DAOPersona;

/**
 * Servlet implementation class InsertarServlet
 */
public class InsertarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sql=null;
		try{
			//recoger parametros
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

    		DAOPersona dao = new DAOPersona();
			dao.save(p);
			
			//Volver a la HOME
			request.getRequestDispatcher("inicio").forward(request, response);
			
		}catch ( Exception e){
			
			request.setAttribute("msg", e.getMessage() + "SQL: "+sql);
			request.getRequestDispatcher("form.jsp").forward(request, response);
		}
		
	}

}
