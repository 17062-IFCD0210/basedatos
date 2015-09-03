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
              <div class="tablaconexion">
	            <ul>
	            	<li>Servidor: <small>localhost</small></li>
	            	<li>Base de Datos: <small>skalada</small></li>
	            	<li>Puerto: <small>3306</small></li>
	            	<li>Usuario: <small>root</small></li>
	            	<li>Password: </li>
	            	<li>Tabla: <small>test</small></li>
	            </ul>
           	  </div>
           
             	<a class="btn btn-warning btn-xs" href="form.jsp" role="button">Insertar nuevo registro</a>
             	
            	<table class="tabla_blanco">
	            	<thead>
	            		<tr>	
	            			<th>Nombre</th>
	            			<th>Nota</th>
	            			<th>Telefono</th>
	            			<th>Fecha</th>
	            			<th>Editar</th>
	            			<th>Eliminar</th>
	            		</tr>
	            	</thead>
	            	
	            	
	            	
	            	<tbody>	
		            	<%    	
		            		//recuperar atributo de listado personas
		            		ArrayList<Persona> alumnos = 
		            		      (ArrayList<Persona>)request.getAttribute("alumnos");
		            		if ( alumnos == null ){ 
		            			alumnos = new ArrayList<Persona>();
		            		}
		            		
		            		Persona p = null;
		            		for (int i=0; i < alumnos.size(); i++){
		            			p = alumnos.get(i);
		            			%>
		            				<tr>            					
		            					<td><%=p.getNombre()%></td>
		            					<td><%=p.getNota()%></td>
		            					<td><%=p.getTelefono()%></td>
		            					<td><%=p.getFecha()%></td>
										<td><a href="">E</a></td>
										<td><a href="">X</a></td>
		            				</tr>            			
		            			<%
		            		}//end for
		            	
		            	%>
            		</tbody>
            		
            		<tfoot>
	            		<tr>
	            			<td colspan="6">Mostrando: <%=alumnos.size()%> </td>
	            		</tr>
	            	</tfoot>
	            	
	            	
            	</table>
            	
            	<br>
            	<a class="btn btn-warning btn-xs" href="/inicio" role="button" id="0">Aprobados</a>
            	<a class="btn btn-warning btn-xs" href="/inicio" role="button" id="1">Suspendidos</a>
            	<a class="btn btn-warning btn-xs" href="/inicio" role="button">Todos</a>
            	
            	<br>
            	
           
            
            
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