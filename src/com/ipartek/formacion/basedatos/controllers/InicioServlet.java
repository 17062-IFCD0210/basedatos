package com.ipartek.formacion.basedatos.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.basedatos.modelo.DAOPersona;

/**
 * Servlet implementation class InicioServlet
 */
public class InicioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Modelo DAOPersona
	DAOPersona dao = null;

	// parametros
	private String pID;
	private String pAccion;
	private String pFiltro;
	private RequestDispatcher dispatcher;

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

		// Recoger parametros: accion, id, filtro

		pID = request.getParameter("id");
		pAccion = request.getParameter("accion");
		pFiltro = request.getParameter("filtro");

		// En funcion del parametro 'accion' realizar la Accion
		// 0: Listar
		// 1: Detalle
		// 2: Eliminar

		// Detalle
		if ("1".equals(pAccion)) {
			detalle(request, response);
			// Eliminar
		} else if ("2".equals(pAccion)) {
			eliminar(request, response);
			// Listar
		} else {
			listar(request, response);
		}

		// forward
		dispatcher.forward(request, response);

	}

	private void listar(HttpServletRequest request, HttpServletResponse response) {

		ArrayList<Object> alumnos = new ArrayList<Object>();

		try {
			// recoger parametros
			pFiltro = request.getParameter("filtro");

			if ("1".equals(pFiltro)) {
				alumnos = dao.getAprobados();
			} else if ("2".equals(pFiltro)) {
				alumnos = dao.getSuspendidos();
			} else {
				alumnos = dao.getAll();
			}

			// cargar atributos en request
			request.setAttribute("alumnos", alumnos);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage());
		}
		// forward
		dispatcher = request.getRequestDispatcher("index.jsp");
	}

	private void eliminar(HttpServletRequest request,
			HttpServletResponse response) {
		dao.delete(Integer.parseInt(pID));
		// request.setAttribute("accion", "0");
		// request.setAttribute("filtro", "0");
		// dispatcher = request.getRequestDispatcher("inicio");
		listar(request, response);
	}

	private void detalle(HttpServletRequest request,
			HttpServletResponse response) {

		int id = Integer.parseInt(pID);

		if (id != -1) {
			Object alumno = dao.getById(id);
			request.setAttribute("alumno", alumno);
		}
		// forward
		dispatcher = request.getRequestDispatcher("form.jsp");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
