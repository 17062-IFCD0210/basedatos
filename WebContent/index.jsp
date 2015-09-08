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
    <link href="css/basedatos.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
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
              <h3 class="masthead-brand">Conexion Base Datos</h3>
              <nav>
                <ul class="nav masthead-nav">
                  <li class="active"><a href="#">Home</a></li>
                  <li class=""><a href="#">JDBC</a></li>
                  <li><a href="#">Patron DAO</a></li>
                  <li><a href="#">Hibernate</a></li>
                </ul>
              </nav>
            </div>
          </div>

		


          <div class="inner cover">
            <h1 class="cover-heading">Aprende Base Datos</h1>
            <% //Mostrar mensaje
              	if(request.getAttribute("msg") != null) {
              		out.print("<h4>" + request.getAttribute("msg") + "</h4>");
              	}
              
              %>
            <ul class="blanco">
            	<li>Servidor: <small>localhost</small></li>
            	<li>Base de Datos: <small>skalada</small></li>
            	<li>Puerto: <small>3306</small></li>
            	<li>Usuario: <small>root</small></li>
            	<li>Password: </li>
            	<li>Tabla: <small>test</small></li>
            </ul>
            
            <br>
            <a class="btn btn-primary btn-xs" href="form.jsp" role="button">Insertar nuevo registro</a>
            <br><br>         
            
            <table class="tabla_blanco">
            	<thead>
					<tr>
						<th>Nombre</th>
						<th>Nota</th>
						<th>Telefono</th>
						<th>Fecha</th>
					</tr>
				</thead>
				<tbody>
				<%
					//Recuperar atributo de listado personas
					ArrayList<Persona> alumnos = (ArrayList<Persona>)request.getAttribute("alumnos");
					int id = 0;
					if(alumnos == null) {
						alumnos = new ArrayList<Persona>();
					}
					
					Persona p = null;
					//Recorrer listado
					for(int i=0; i<alumnos.size(); i++) {
						p = alumnos.get(i);
						%>
							<tr>
								<td><%=p.getNombre() %></td>
								<td><%=p.getNota() %></td>
								<td><%=p.getTelefono() %></td>
								<td><%=p.getFecha() %></td>
								<td><a href="inicio?accion=1&id=<%=p.getId() %>" class="verde">E</a></td>
								<td><a href="#myModal" class="rojo" data-toggle="modal" data-target="#myModal<%=+i %>">X</a></td>
						</tr>
						<!-- Ventana Modal -->
 						<div class="modal fade col-md-6 col-md-offset-3" id="myModal<%=+i %>"
 						role="dialog">
							<div class="modal-dialog">
								<!-- Modal content-->
								<div class="modal-content text-info">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">&times;</button>
										<h2 class="modal-title text-center text-danger">
											<i class="fa fa-exclamation-triangle"></i> ELIMINAR REGISTRO:
											</h2>
									</div>
									<div class="modal-body">
										<p>Estas seguro de que desea eliminar el siguiente registro:</p>
										<div class="row">
											<div class="form-group col-md-6">
												<label for="id">Nombre</label> 
												<input type="text" name="id" class="form-control" value="<%=p.getNombre() %>" disabled>
											</div>
											<div class="form-group col-md-6">
												<label for="nombre">Nota</label> 
												<input type="text" name="nombre" class="form-control" value="<%=p.getNota() %>" disabled>
											</div>
										</div>
										<div class="row">
											<div class="form-group col-md-6">
												<label for="grado">Telefono</label> 
												<input type="text" name="grado" class="form-control" value="<%=p.getTelefono() %>" disabled>
											</div>
											<div class="form-group col-md-6">
												<label for="longitud">Fecha</label> 
												<input type="text" name="longitud" class="form-control" value="<%=p.getFecha()%>" disabled>
											</div>
										</div>
									</div>
									<div class="modal-footer">
										<a href="inicio?accion=2&id=<%=p.getId() %>" id="boton_eliminar" class="btn btn-danger">Eliminar</a>
										<button type="button" id="boton_cancelar" class="btn btn-default" data-dismiss="modal">Cancelar</button>
									</div>
								</div>
							</div>
 		         		</div> <!-- END Modal content-->
					<%
					} // end for
					%>
					
				</tbody>     
				<tfoot>
					<tr>
						<td colspan="6">Mostrando <%=alumnos.size()%></td>
					</tr>
				</tfoot>      
            </table> 
            <br>
           	<a class="btn btn-primary btn-xs" href="inicio?accion=0&filtro=0" role="button">Aprobados</a>
           	<a class="btn btn-primary btn-xs" href="inicio?accion=0&filtro=1" role="button">Suspendidos</a>
           	<a class="btn btn-primary btn-xs" href="inicio" role="button">Todos</a>
            
            
          </div>
          
          <div class="mastfoot">
            <div class="inner">
              <p>Documentación <a href="#">Ipartek Campus</a></p>
              <p>Codigo Fuente <a href="#">GITHUB</a></p>
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