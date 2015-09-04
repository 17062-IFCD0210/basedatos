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
 * Servlet implementation class ListarServlet
 */
public class ListarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Object> alumnos = new ArrayList<Object>();
		
		try{	
			//Recoger parametros			
			String filtro = request.getParameter("filtro");
			String order = request.getParameter("order");
			String column = request.getParameter("column");
			
			DAOPersona dao = new DAOPersona();
			
			if("1".equals(filtro)){		//APROBADOS		
				alumnos = dao.getAprobados();	
//				if ("asc".equals(order)){
//					sql = sql + "ORDER BY `"+column+"` ASC;";
//				}
//				if ("desc".equals(order)){
//					sql = sql + "ORDER BY `"+column+"` DESC;";
//				}
			} else if("2".equals(filtro)){		//SUSPENDIDOS
				alumnos = dao.getSuspendidos();
//				if ("asc".equals(order)){
//					sql = sql + "ORDER BY `"+column+"` ASC;";
//				}
//				if ("desc".equals(order)){
//					sql = sql + "ORDER BY `"+column+"` DESC;";
//				}
			} else {		//TODOS
				alumnos = dao.getAll();	
//				if ("asc".equals(order)){
//					sql = sql + "ORDER BY `"+column+"` ASC;";
//				}
//				if ("desc".equals(order)){
//					sql = sql + "ORDER BY `"+column+"` DESC;";
//				}
			}
			
			
						
			
			//Cargar atributos en request
			request.setAttribute("alumnos", alumnos);
			request.setAttribute("filtro", filtro);
			request.getRequestDispatcher("JDBC.jsp").forward(request, response);
			
		} catch (Exception e) {
			request.setAttribute("msg_error", e.getMessage());			
			request.getRequestDispatcher("JDBC.jsp").forward(request, response);
		}
		
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
