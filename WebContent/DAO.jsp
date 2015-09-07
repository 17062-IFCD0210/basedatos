<!DOCTYPE html>
<%@page import="com.ipartek.formacion.basedatos.Constantes"%>
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
	
	<!-- DataTables CSS -->
    <link href="css/jquery.dataTables.min.css" rel="stylesheet">
	
	
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
								<li class="active"><a href="inicio_persona?accion=<%=Constantes.ACCION_LISTAR%>">Patron DAO</a></li>
								<li><a href="#">Hibernate</a></li>
							</ul>
						</nav>
					</div>
				</div>

				<div class="inner cover">
					<h1 class="cover-heading">Patron DAO - Data Access Object</h1>
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
											
						<div class="resultado">
							<table id="tabla" class="display" cellspacing="0" width="100%">
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
										<td><a><span class='icon-trashcan red' data-toggle="modal" data-target="#myModal<%=p.getId()%>"></span></a><a href="inicio_persona?accion=<%=Constantes.ACCION_DETALLE%>&id=<%=p.getId()%>"><span class='icon-edit blue'></span></a></td>
									</tr>	
									<!-- Ventana Modal -->
										<div class="modal fade col-md-6 col-md-offset-3" id="myModal<%=p.getId()%>" role="dialog">
										<div class="modal-dialog">
									  
									    <!-- Modal content-->
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal">&times;</button>
														<h2 class="modal-title text-center text-danger"><i class="fa fa-exclamation-triangle"></i> ELIMINAR REGISTRO</h2>
									  			</div>
									  			<div class="modal-body">
									    			<span class="black">¿Estas seguro de que deseas eliminar el registro?</span> 
									  			</div>
									  			<div class="modal-footer">						    			
									    			<a href="inicio_persona?accion=<%=Constantes.ACCION_ELIMINAR%>&id=<%=p.getId()%>" id ="boton_eliminar" class="btn btn-danger">Eliminar</a>
									      			<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
									      		</div>
									    	</div> <!-- END Modal content-->
									  	</div>
									</div> <!-- END Ventana Modal -->									
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
					
					<a href="inicio_persona?accion=<%=Constantes.ACCION_NUEVO%>"><button class="btn-lg">Nuevo Registro</button></a>
					
					<div>	
						<br>					
						<a href="inicio_persona?accion=<%=Constantes.ACCION_LISTAR%>&filtro=1"><button class="btn-lg">Aprobados</button></a>
						<a href="inicio_persona?accion=<%=Constantes.ACCION_LISTAR%>&filtro=2"><button class="btn-lg">Suspendidos</button></a>
						<a href="inicio_persona?accion=<%=Constantes.ACCION_LISTAR%>"><button class="btn-lg">Todos</button></a>					
					</div>
									
				</div>

				<div class="mastfoot">
					<div class="inner">
						<p>DocumentaciÃ³n <a href="http://www.formacion.ipartek.com/campus/" target="_blank">Ipartek Campus</a></p>
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
	
	<!-- DataTables JavaScript -->
    <script src="js/jquery.dataTables.min.js"></script>
	<!-- Enganche para DataTable -->
    <script>
    $(document).ready(function() {
        $('#tabla').DataTable({
        });
    });
    </script>
</body>
</html>