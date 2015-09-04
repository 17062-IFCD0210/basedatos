package com.ipartek.formacion.basedatos.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.basedatos.modelo.DAOPersona;

/**
 * Servlet implementation class ListadoServlet
 */
public class ListadoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListadoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		ArrayList<Object> alumnos = new ArrayList<Object>();

		try {
			// recoger parametros
			int pAccion = Integer.parseInt(request.getParameter("accion"));
			DAOPersona dao = new DAOPersona();

			switch (pAccion) {
			case 1:
				alumnos = dao.getAprobados();
				break;
			case 2:
				alumnos = dao.getSuspendidos();
				break;
			}

			// cargar atributos en request
			request.setAttribute("alumnos", alumnos);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage());
		}
		// forward
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
