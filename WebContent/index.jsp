<!DOCTYPE html>
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
              	if(request.getAttribute("msg2") != null) {
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
            
           
            
            <ol>
            	<li>Cargando Driver: <code>com.mysql.jdbc.Driver</code></li>
            	<br>
            	<li>Establecer Conexion: <code>"jdbc:mysql://localhost/skalada","root", ""</code></li>
            	<br>
            	<li>Preparar Consulta: <code>SELECT * FROM `test`</code></li>
            	<br>
            	<li>Registros
            	<br><br>
            	<a class="btn btn-primary btn-xs" href="form.jsp" role="button">Insertar nuevo registro</a>
            	<br><br>
            	<%
	            	Class.forName("com.mysql.jdbc.Driver");
	            	Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/skalada","root", "");
	            	
	            	// Preparamos la consulta 
	            	Statement st = conexion.createStatement(); 
	            	String sql = "SELECT * FROM `test`";
	            	ResultSet rs = st.executeQuery (sql);
	            	
	            	//recorrer datos del resultado
	            	out.print("<table class=\"tabla_blanco\">");
	            		out.print("<tr>");
	            			out.print("<th>Id</th>");
	            			out.print("<th>Nombre</th>");
	            			out.print("<th>Nota</th>");
	            			out.print("<th>Telefono</th>");
	            			out.print("<th>Fecha</th>");
	            		out.print("</tr>");
		            	while (rs.next()){ 
		            		out.print("<tr>");
			    				out.print("<td>" + rs.getInt("id") + "</td>");
			            		out.print("<td>" + rs.getString("nombre") + "</td>");
			            		out.print("<td>" + rs.getFloat("nota") + "</td>");
			            		out.print("<td>" + rs.getString("telefono") + "</td>");
			            		out.print("<td>" + rs.getDate("fecha") + "</td>");
			            		out.print("<td><a href=\"editar?id=" + rs.getInt("id") + "\" class=\"verde\">E</a></td>");
			            		out.print("<td><a href=\"eliminar?id=" + rs.getInt("id") + "\" class=\"rojo\">X</a></td>");
		            		out.print("</tr>");
						}
	            	out.print("</table>");

            		//cerrar conexiones
            		conexion.close();
            	%>
            	<br>
            	<a class="btn btn-primary btn-xs" href="lista?accion=1" role="button">Aprobados</a>
            	<a class="btn btn-primary btn-xs" href="lista?accion=2" role="button">Suspendidos</a>
            	<a class="btn btn-primary btn-xs" href="lista?accion=3" role="button">Todos</a>
            	</li>
            	<br>
            	<li>Cerrar conexion: <code>conexion.close();</code></li>
            </ol>
            
            
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