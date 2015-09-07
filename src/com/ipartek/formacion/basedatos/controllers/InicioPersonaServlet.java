package com.ipartek.formacion.basedatos.controllers;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	private int pID = -1;
	private String pNombre;
	private float pNota;
	private String pTelefono;
	private Timestamp pFecha;
	
    
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
				pFiltro = Integer.parseInt(request.getParameter("filtro"));
			} else {
				pFiltro = 0;
			}			
		} catch(Exception e){
			e.printStackTrace();
		}		
	}
	

	private void listar(HttpServletRequest request, HttpServletResponse response) {
		if (pFiltro == 1){
			request.setAttribute("alumnos", dao.getAprobados());
		} else if (pFiltro == 2){
			request.setAttribute("alumnos", dao.getSuspendidos());
		} else {
			request.setAttribute("alumnos", dao.getAll());
		}
		
		dispatcher = request.getRequestDispatcher("DAO.jsp");		
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) {
		if(dao.delete(pID)){
			request.setAttribute("msg_ok", "Se ha eliminado correctamente");
		} else {
			request.setAttribute("msg_error", "Error al eliminar[id(" + pID + ")]");
		}
		listar(request, response);
	}

	private void nuevo(HttpServletRequest request, HttpServletResponse response) {
		persona = new Persona("");
		persona.setId(-1);
		request.setAttribute("alumno", persona);
		request.setAttribute("metodo", "Guardar");
		dispatcher = request.getRequestDispatcher("formulario.jsp");		
	}
	
	private void detalle(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("alumno", dao.getById(pID));
		request.setAttribute("metodo", "Modificar");
		dispatcher = request.getRequestDispatcher("formulario.jsp");		
	}
	


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//recoger parametros del formulario
		getParametersForm(request);
		
		//Crear Objeto Via
		crearObjetoPersona();
		
		//Guardar/Modificar Objeto Via
		if (pID == -1){
			if( dao.save(persona) != -1){	
				request.setAttribute("msg_ok", "Registro a&ntilde;adido");
			} else {
				request.setAttribute("msg_error", "Error al guardar el registro");
			}
		} else {
			if(dao.update(persona)){
				request.setAttribute("msg_ok", "Registro modificado correctamente");
			} else {
				request.setAttribute("msg_error", "Error al modificar registro");
			}
		}
		
		listar(request,response);
		
		dispatcher.forward(request, response);
		
	}
	
	/**
	 * Crea un Objeto {@code Via} Con los parametros recibidos
	 */
	private void crearObjetoPersona() {
		persona = new Persona(pNombre);
		persona.setId(pID);
		persona.setNota(pNota);
		persona.setTelefono(pTelefono);
		persona.setFecha(pFecha);
	}


	/**
	* Recoger los parametros enviados desde el formulario
	* @see backoffice\pages\vias\form.jsp
	* @param request
	*/
	private void getParametersForm(HttpServletRequest request) {
	
		try {
			pID = Integer.parseInt(request.getParameter("id"));
			pNombre = request.getParameter("nombre");
			if(request.getParameter("nota") != null && !"".equals(request.getParameter("nota"))){
				pNota = Float.parseFloat(request.getParameter("nota"));
			} else {
				pNota = 0;
			}		
			pTelefono = request.getParameter("telefono");		
			if(request.getParameter("fecha") != null){	
				pFecha = parse_string_timestamp(request);
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	
	}
	
	public Timestamp parse_string_timestamp(HttpServletRequest request) throws ParseException {
		String sFecha = request.getParameter("fecha");		
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
	    Date parsedTimeStamp = dateFormat.parse(sFecha);
	    Timestamp timestamp = new Timestamp(parsedTimeStamp.getTime());
	    return timestamp;
	}

}
