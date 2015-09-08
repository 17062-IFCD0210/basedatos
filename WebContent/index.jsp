<!DOCTYPE html>
<<<<<<< HEAD
<%@page import="com.ipartek.formacion.basedatosProfe.modelo.DataBaseHelper"%>
<%@page import="com.ipartek.formacion.basedatosProfe.bean.Persona"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
=======

<%@page import="com.ipartek.formacion.basedatos.bean.Persona"%>
<%@page import="java.util.ArrayList"%>

>>>>>>> branch 'master' of https://github.com/17062-IFCD0210/basedatos.git
<html lang="es">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Base Datos BBDD</title>
    
	<!-- Estilos CSS -->
		<link rel="stylesheet" type="text/css" href="css/styles.css?v=1.0">
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
<<<<<<< HEAD
=======
    <link href="css/cover.css" rel="stylesheet">
    <link href="css/basedatos.css" rel="stylesheet">
>>>>>>> branch 'master' of https://github.com/17062-IFCD0210/basedatos.git

	<!-- Custom styles for this template -->
    <link href="css/cover.css" rel="stylesheet">
    
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
              <h3 class="masthead-brand">Base Datos</h3>
              <nav>
                <ul class="nav masthead-nav">
                  <li class="active"><a href="#">Home</a></li>
                  <li><a href="#">JDBC</a></li>
                  <li><a href="#">Patron DAO</a></li>
                  <li><a href="#">Hibernate</a></li>
                </ul>
              </nav>
            </div>
          </div>

          <div class="inner cover">
<<<<<<< HEAD
            <h1 class="cover-heading">Conexión Base Datos</h1>
            
            <%
            
            	if (request.getAttribute("msg") != null){
            		out.print("<h4>" + request.getAttribute("msg") + "</h4>");
            	}
            
            %>
=======
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
		            					<td><a href="inicio?id=<%=p.getId()%>&accion=1">E</a></td>
		            					<td><a href="inicio?id=<%=p.getId()%>&accion=2">X</a></td>
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
            	<a class="btn btn-primary btn-xs" href="inicio?filtro=0" role="button">Aprobados</a>
            	<a class="btn btn-primary btn-xs" href="inicio?filtro=1" role="button">Suspendidos</a>
            	<a class="btn btn-primary btn-xs" href="inicio"          role="button">Todos</a>
            	<a class="btn btn-success btn-xs" href="inicio?accion=3" role="button">Crear</a>
            	
            	<br>
            	
           
            
>>>>>>> branch 'master' of https://github.com/17062-IFCD0210/basedatos.git
            
            
            <div id="bbdd">
 				<h5><strong>Servidor: </strong><span id="location_host"></span></h5>
 				<h5><strong>Base Datos: </strong><span id="bbdd_nombre"></span></h5>
 				<h5><strong>Puerto: </strong><span id="location_port"></span></h5>
 				<h5><strong>Usuario: </strong><span id="bbdd_usuario"></span></h5>
 				<h5><strong>Password: </strong><span id="bbdd_pass"></span></h5>
 				<h5><strong>Tabla: </strong><span id="bbdd_tabla"></span></h5>
            </div>

            <div>
	           	<p class="lead">Cargando Driver: <span id="comando">Class.forName("com.mysql.jdbc.Driver");</span></p>
	           	<%Class.forName("com.mysql.jdbc.Driver");%>
           	</div>
           	
           	<div>
	           	<p class="lead">Establecemos conexión con BBDD</p>
	           	<span id="comando">Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/skalada","root", "");</span>
	           	<%Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/skalada","root", "");%>
           	</div>
           	
           	<div>
	           		<p class="lead">Preparamos la consulta <span id="comando">SELECT * FROM `test`</span></p>
	           	<%
	            	Statement st = conexion.createStatement();
	            	String sql = "SELECT * FROM `test`";
	            	ResultSet rs = st.executeQuery (sql);
	           	%>
           	</div>
           	
           	<div>
	           	<p class="lead">Recorremos los datos del resultado <span id="comando">while (rs.next())+ "</li>");}</span></p>
	           	
	           	<%
	            	out.print("<table class='tabla_blanco'><thead><tr><th>Nombre</th><th>Nota</th><th>Tel&eacute;fono</th><th>Fecha</th><th>Editar</th><th>Eliminar</th></tr><thead>");
            		out.print("<tbody>");

	            	//Recuperar atributo del listado personas
	            	ArrayList<Persona> alumnos = (ArrayList<Persona>)request.getAttribute("alumnos");
	            	if (alumnos == null) {
	            		alumnos = new ArrayList<Persona>(); 
	            	}
	            	
	            	Persona p = null;
	            	for (int i=0; i<alumnos.size();i++){
	            		p = alumnos.get(i);
	            		%>
	            			<tr>
	            				<td><%=p.getNombre()%></td>
	            				<td><%=p.getNota()%></td>
	            				<td><%=p.getTelefono()%></td>
	            				<td><%=p.getFecha()%></td>
	            				<td><a href="inicio?id=<%=p.getId()%>&accion=3"><img src="img/editar.png"></a></td>
	            				<td><a href="inicio?id=<%=p.getId()%>&accion=2"><img src="img/eliminar.png"></a></td>
	            			</tr>
	            		<%
	            	} //end for
            	%>
            	
            	<!--  		<td><a href="eliminar?id=< % =rs.getInt("id")%>">Eliminar</a></td> -->
            	<!-- 		<td><a href="Editar?id=< % =rs.getInt("id")%>&accion=0">Editar</a></td> -->
        
            	</tbody>
            
	            <tfoot>
		            <tr class="">
		            	<td colspan="6">Mostrando: <%=alumnos.size()%></td>
		            </tr>
	            </tfoot>
            	</table>
	            
	            <a class="btn btn-info btn-xs"    href="inicio" 		 role="button">Todos</a>
	            <a class="btn btn-info btn-xs"    href="inicio?filtro=0" role="button">Aprobados</a>
	            <a class="btn btn-info btn-xs"    href="inicio?filtro=1" role="button">Suspendidos</a>
	            <a class="btn btn-success btn-xs" href="inicio?accion=3" role="button">Crear</a>
	            
            </div>
            
            <div>
	            	<p class="lead">Cerramos la conexión a la BBDD <span id="comando">DataBaseHelper.closeConnection();</span></p>
	        </div>
          </div>

          <div class="mastfoot">
            <div class="inner">
              <p>Documentación: <a href="http://getbootstrap.com">Ipartek Campus</a></p>
              <p>Código Fuente: <a href="http://getbootstrap.com">GITHUB</a></p>
            </div>
          </div>

        </div>

      </div>

    </div>

	<script type="text/javascript">
		document.getElementById("location_host").innerHTML = window.location.hostname;
		document.getElementById("bbdd_nombre").innerHTML = "skalada";
		document.getElementById("location_port").innerHTML = window.location.port;
		document.getElementById("bbdd_usuario").innerHTML = "root";
		document.getElementById("bbdd_pass").innerHTML = "";
		document.getElementById("bbdd_tabla").innerHTML = "test";
	</script>
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="js/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>
