<h1>Controlador Registros</h1>



<%
	//Controlador para recoger parametros del formulario de datos_personales.jsp
	
	
	//recoger parametros de la request
	String p1 = (String)request.getParameter("nombre");
	String p2 = (String)request.getParameter("nota");
	String p3 = (String)request.getParameter("telefono");
	String p4 = (String)request.getParameter("fecha");

	
	//pintar los parametros en el html(en pantalla)
	out.print("<p>Nombre: "		+ p1 + "</p>");	
	out.print("<p>Nota: "	+ p2 + "</p>");
	out.print("<p>Telefono: "	+ p3 + "</p>");
	out.print("<p>Fecha: "		+ p4 + "</p>");


		
%>
