package com.ipartek.formacion.basedatos.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// recoger parametros

		int pId = Integer.parseInt(request.getParameter("id"));

		// TODO llamar modelo para insercion
		try {
			// abrir conexion
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost/skalada", "root", "");

			// crear SQL
			Statement st = conexion.createStatement();
			String sql = "Delete from test where id=" + pId;
			// ejecutar SQL

			if (st.executeUpdate(sql) != 1) {
				throw new Exception("No se ha realizado el borrado: " + sql);
			}

			// cerrar conexion

			st.close();
			conexion.close();

			// volver a la HOME

			request.getRequestDispatcher("index.jsp")
					.forward(request, response);
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("form.jsp").forward(request, response);
		}

	}

}
