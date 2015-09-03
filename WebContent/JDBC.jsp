<!DOCTYPE html>
<%@page import="com.ipartek.formacion.basedatos.bean.Persona"%>
<%@page import="java.util.ArrayList"%>

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
								<li class="active"><a href="listar">JDBC</a></li>
								<li><a href="#">Patron DAO</a></li>
								<li><a href="#">Hibernate</a></li>
							</ul>
						</nav>
					</div>
				</div>

				<div class="inner cover">
					<h1 class="cover-heading">JDBC - Java Database Connectivity</h1>
					<div>
						<%
							//Mostrar mensajes
							if (request.getAttribute("msg_error") != null){
								out.print("<div class='msg_error'>");
								out.print("<h2>" + request.getAttribute("msg_error") + "</h2>");
								out.print("</div>");
							}
							if (request.getAttribute("msg_ok") != null){
								out.print("<div class='msg_ok'>");
								out.print("<h2>" + request.getAttribute("msg_ok") + "</h2>");
								out.print("</div>");
							}
						%>								
											
						<div class="resultado" id="tabla">
							<table>
								<thead>
									<tr>
										<th>NOMBRE</th>
										<th>NOTA</th>
										<th>TELEFONO</th>
										<th>FECHA</th>
										<th>Opc.</th>
									</tr>
								</thead>
								<tbody>
								<%
									//recuperar atributo de listado personas
									ArrayList<Persona> alumnos = (ArrayList<Persona>)request.getAttribute("alumnos");
									
									if(alumnos == null){
										alumnos = new ArrayList<Persona>();
									}
									
									Persona p = null;
									for(int i = 0; i < alumnos.size(); i++){
										p = alumnos.get(i);										
								%>								
									<tr>
										<td><%=p.getNombre()%></td>
										<td><%=p.getNota()%></td>
										<td><%=p.getTelefono()%></td>
										<td><%=p.getFecha()%></td>
										<td><a href="eliminar?id=<%=p.getId()%>"><span class='icon-trashcan red'></span></a> <a href="editar?id=<%=p.getId()%>"><span class='icon-edit blue'></span></a></td>
									</tr>										
								<%
									}	//END for									
								%>
								</tbody>			
								<tfoot>
									<tr>
										<td colspan="6">Mostrando: <%=alumnos.size()%></td>
									</tr>
								</tfoot>
							</table>							
						</div> <!-- END .resultado -->
												
					</div>
					
					<a href="form.jsp"><button class="btn-lg">Nuevo Registro</button></a>
					
					<div>						
						<a href="listar?filtro=1"><button class="btn-lg">Aprobados</button></a>
						<a href="listar?filtro=2"><button class="btn-lg">Suspendidos</button></a>
						<a href="listar"><button class="btn-lg">Todos</button></a>					
					</div>
									
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