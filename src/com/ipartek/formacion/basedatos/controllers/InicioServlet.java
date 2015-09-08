package com.ipartek.formacion.basedatos.controllers;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

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
		// 3: Insertar nuevo
		// 4: Actualizar

		if ("1".equals(pAccion)) {
			// Detalle
			detalle(request, response);
		} else if ("2".equals(pAccion)) {
			// Eliminar
			eliminar(request, response);
		} else {
			// Listar
			listar(request, response);
		}

		// forward
		dispatcher.forward(request, response);

	}

	private void actualizar(HttpServletRequest request,
			HttpServletResponse response) {

		boolean resul = false;
		String id = request.getParameter("id");
		String pNombre = request.getParameter("nombre");
		float pNota = Float.parseFloat(request.getParameter("nota"));
		String pTelefono = request.getParameter("telefono");
		String pFechaString = request.getParameter("fecha").replace("T", " ");
		Timestamp pFecha = null;

		if (pFechaString != "") {
			pFecha = Timestamp.valueOf(pFechaString.replace("T", " "));
		}

		Persona p = new Persona(pNombre);
		p.setId(Integer.valueOf(id));
		if (pFecha != null) {
			p.setFecha(pFecha);
		}
		p.setNota(pNota);
		p.setTelefono(pTelefono);

		resul = dao.update(p);
		if (resul) {
			request.setAttribute("msg", "Se ha actualizado correctamente");
			request.setAttribute("tipo", "success");
		} else {
			request.setAttribute("msg", "Ha ocurrido un error al actualizar");
			request.setAttribute("tipo", "danger");
		}
		listar(request, response);

	}

	private void insertar(HttpServletRequest request,
			HttpServletResponse response) {

		try {

			int newId = 0;
			String pNombre = request.getParameter("nombre");
			float pNota = Float.parseFloat(request.getParameter("nota"));
			String pTelefono = request.getParameter("telefono");
			String pFechaString = request.getParameter("fecha").replace("T",
					" ");
			if (!pFechaString.isEmpty()) {
				pFechaString = pFechaString.concat(":00");
			}
			Timestamp pFecha = null;

			if (pFechaString != "") {
				pFecha = Timestamp.valueOf(pFechaString);
			}

			Persona p = new Persona(pNombre);
			if (pFecha != null) {
				p.setFecha(pFecha);
			}
			p.setNota(pNota);
			p.setTelefono(pTelefono);
			newId = dao.save(p);
			if (newId != -1) {
				request.setAttribute("msg", "Creado nuevo registro(" + newId
						+ ")");
				request.setAttribute("tipo", "success");
			} else {
				request.setAttribute("msg", "No se ha creado el nuevo registro");
				request.setAttribute("tipo", "danger");
			}

		} catch (Exception e) {
			request.setAttribute("msg", "No se ha creado el nuevo registro");
			request.setAttribute("tipo", "danger");
		}
		listar(request, response);
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
		if (dao.delete(Integer.parseInt(pID))) {
			request.setAttribute("msg", "Se ha borrado correctamente");
			request.setAttribute("tipo", "success");
		} else {
			request.setAttribute("msg", "Se ha producido un error al borrar");
			request.setAttribute("tipo", "danger");
		}
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

		pID = request.getParameter("id");
		pAccion = request.getParameter("accion");

		if ("3".equals(pAccion)) {
			// Insertar nuevo
			insertar(request, response);
		} else if ("4".equals(pAccion)) {
			// Actualizar
			actualizar(request, response);
		}

		dispatcher.forward(request, response);

	}

}
