<!DOCTYPE html>
<%@page import="com.ipartek.formacion.basedatos.bean.Persona"%>
<%@page import="com.ipartek.formacion.basedatos.Constantes"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<html lang="es">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<title>Base Datos BBDD</title>
	
	<!-- Bootstrap -->
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/cover.css" rel="stylesheet">
	
	<link rel="stylesheet" href="fonts/style.css">
	
	<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>
  
<body>

	<div class="site-wrapper">
		<div class="site-wrapper-inner">
			<div class="cover-container">
				<div class="masthead clearfix">
					<div class="inner">
						<h3 class="masthead-brand">Base Datos</h3>
						<nav>
							<ul class="nav masthead-nav">
								<li><a href="index.jsp">Home</a></li>
								<li><a href="listar">JDBC</a></li>
								<li><a href="inicio_persona?accion=<%=Constantes.ACCION_LISTAR%>">Patron DAO</a></li>
								<li><a href="#">Hibernate</a></li>
							</ul>
						</nav>
					</div>
				</div>

				<div class="inner cover">
										
					<h1 class="cover-heading">Insertar/Modificar registro</h1>
					
					<%
						//Mostrar mensajes
						if (request.getAttribute("msg_error") != null){
							out.print("<div class='msg_error'>");
							out.print("<h2>" + request.getAttribute("msg_error") + "</h2>");
							out.print("</div><br>");
						}		
											
					%>
															
					<form action="inicio_persona" method="post">
						
						<%
							Persona p = (Persona)request.getAttribute("alumno");
						
							String type;
							if (p.getFecha() != null){
								type = "text";
							} else {
								type = "hidden";
							}
						%>												
						<input type="hidden" name="id" value="<%=p.getId()%>">						
						<label for="nombre">Nombre:</label>
						<input type="text" name="nombre" value="<%=p.getNombre()%>"
						required="required" autofocus="autofocus" tabindex="1">
						<br><br>
						<label for="nota">Nota:</label>
						<input type="text" name="nota" value="<%=p.getNota()%>" 
						required="required" tabindex="2">
						<br><br>
						<label for="telefono">Telefono:</label>
						<input type="text" name="telefono" value="<%=p.getTelefono()%>" 
						tabindex="3" pattern="[0-9]{9,15}">								
						<br><br>
						<input type="<%=type %>" name="fecha" value="<%=p.getFecha()%>" 
						tabindex="3" placeholder="AAAA-MM-DD HH:MM:SS.SSS">								
						<br><br>					
						<input type="submit" class="btn-lg" value="<%=request.getAttribute("metodo")%>">
						
					</form>
					
				</div>

				<div class="mastfoot">
					<div class="inner">
						<p>Documentación <a href="http://www.formacion.ipartek.com/campus/" target="_blank">Ipartek Campus</a></p>
						<p>Codigo Fuente <a href="https://github.com/" target="_blank">GITHUB</a></p>
					</div>
				</div>
			</div>
		</div>
	</div>
    


	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="js/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>