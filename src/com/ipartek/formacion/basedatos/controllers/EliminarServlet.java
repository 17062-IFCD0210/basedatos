package com.ipartek.formacion.basedatos.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.basedatos.modelo.DAOPersona;

/**
 * Servlet implementation class EliminarServlet
 */
public class EliminarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			//recoger parametros
			int pId = Integer.parseInt(request.getParameter("id"));
			
			DAOPersona dao = new DAOPersona();
			dao.delete(pId);
			
			//Volver a la HOME
			request.getRequestDispatcher("inicio").forward(request, response);
			
		}catch ( Exception e){
			
			request.setAttribute("msg", e.getMessage() );
			request.getRequestDispatcher("form.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
