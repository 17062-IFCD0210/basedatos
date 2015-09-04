package com.ipartek.formacion.basedatos.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.basedatos.Constantes;
import com.ipartek.formacion.basedatos.bean.Persona;
import com.ipartek.formacion.basedatos.modelo.DAOPersona;


/**
 * Servlet implementation class InicioPersonaServlet
 */
public class InicioPersonaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private RequestDispatcher dispatcher = null;
	private DAOPersona dao = null;
	private Persona persona = null;
	
	//parametros
	private int pAccion = Constantes.ACCION_LISTAR;
	private int pFiltro;
	private int pID;
	private String pNombre;
	private float pNota;
	private String pFecha;
	
    
    /**
     * Este metodo se ejecuta solo la primera vez que se llama al servlet
     * Se suele usar para crear el modelo (DAO)
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	dao = new DAOPersona();    	
    }
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InicioPersonaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//recoger parametros
		getParameters(request,response);
		
		//realizar accion solicitada
		switch (pAccion) {
		case Constantes.ACCION_NUEVO:
			nuevo(request,response);
			break;
		case Constantes.ACCION_DETALLE:
			detalle(request,response);
			break;
		case Constantes.ACCION_ELIMINAR:
			eliminar(request,response);
			break;
		default:
			listar(request,response);
			break;
		}
			
		dispatcher.forward(request, response);
	}
	
	private void getParameters(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			pAccion = Integer.parseInt(request.getParameter("accion"));	
			
			if(request.getParameter("id") != null && !"".equalsIgnoreCase(request.getParameter("id"))){
				pID = Integer.parseInt(request.getParameter("id"));
			}
			
			if(request.getParameter("filtro") != null && !"".equalsIgnoreCase(request.getParameter("filtro"))){
				pID = Integer.parseInt(request.getParameter("filtro"));
			}
			
		} catch(Exception e){
			e.printStackTrace();
		}		
	}
	
	/**
	 * Obtiene todas las vias del modelo y carga dispatch con index.jsp
	 * @see backoffice/pages/via/index.jsp
	 * @param request
	 * @param response
	 */
	private void listar(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("alumnos", dao.getAll());
		dispatcher = request.getRequestDispatcher("DAO.jsp");		
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) {
		if(dao.delete(pID)){
			request.setAttribute("msg-danger", "Se a eliminadocorrectamente");
		} else {
			request.setAttribute("msg-warning", "Error al eliminar[id(" + pID + ")]");
		}
		listar(request, response);
	}

	private void nuevo(HttpServletRequest request, HttpServletResponse response) {
		persona = new Persona("");
		request.setAttribute("alumno", persona);
		request.setAttribute("metodo", "Guardar");
		dispatcher = request.getRequestDispatcher("form.jsp");		
	}
	
	private void detalle(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("alumno", dao.getById(pID));
		request.setAttribute("metodo", "Modificar");
		dispatcher = request.getRequestDispatcher("form_mod.jsp");		
	}
	
	
	
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
