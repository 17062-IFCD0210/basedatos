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
    
	<!-- Estilos CSS -->
		<link rel="stylesheet" type="text/css" href="css/styles.css?v=1.0">
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

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
            <h1 class="cover-heading">Conexión Base Datos</h1>
            <a href="form.jsp">Insertar nuevo registro</a>
            <p></p>
            
            <div id="bbdd">
	 				<h5><strong>Servidor: </strong><span id="location_host"></span></h5>
	 				<h5><strong>Base Datos: </strong><span id="bbdd_nombre"></span></h5>
	 				<h5><strong>Puerto: </strong><span id="location_port"></span></h5>
	 				<h5><strong>Usuario: </strong><span id="bbdd_usuario"></span></h5>
	 				<h5><strong>Password: </strong><span id="bbdd_pass"></span></h5>
	 				<h5><strong>Tabla: </strong><span id="bbdd_tabla"></span></h5>
            </div>

            <div id="area">
	           	<p class="lead">Cargando Driver: <span id="comando">Class.forName("com.mysql.jdbc.Driver");</span></p>
	           	<%Class.forName("com.mysql.jdbc.Driver");%>
           	</div>
           	
           	<div id="area">
	           		<p class="lead">Establecemos conexión con BBDD</p>
	           		<span id="comando">Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/skalada","root", "");</span>
	           	<%Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/skalada","root", "");%>
           	</div>
           	
           	<div id="area">
	           		<p class="lead">Preparamos la consulta <span id="comando">SELECT * FROM `test`</span></p>
	           	<%
	            	Statement st = conexion.createStatement();
	            	String sql = "SELECT * FROM `test`";
	            	ResultSet rs = st.executeQuery (sql);
	           	%>
           	</div>
           	
           	<div id="area">
	           		<p class="lead">Recorremos los datos del resultado <span id="comando">while (rs.next()){out.print("<li>" + rs.getString("id") + " " + rs.getString("nombre") + "</li>");}</span></p>
	           	<%
	           		if (rs.next()){
		            	out.print("<table><tr><th>Nombre</th><th>Nota</th><th>Tel&eacute;fono</th><th>Fecha</th></tr>");
		            	while (rs.next()){
		            		out.print("<tr><td>" + rs.getString("id") + "</td><td>" + rs.getString("nombre") + "</td><td>" + rs.getFloat("nota") + "</td><td>"
		            		+ rs.getString("telefono") + "</td>");
		            			// "<td><a href='eliminar&id=" + rs.getInt("id") + "'>Eliminar</a></td>");
		            			// o as�
		            			%>
		            			<td><a href="eliminar?id=<%=rs.getInt("id")%>">Eliminar</a></td>
		            			<td><a href="editar?id=<%=rs.getInt("id")%>">Editar</a></td>
		            			<%
							}
		            	out.print("</table>");
	           		}
	            %>
            </div>
            
            <div id="area">
	            	<p class="lead">Cerramos la conexión a la BBDD <span id="comando">rs.close(); st.close(); conexion.close();</span></p>
	           	<%
	           		rs.close();
	           		st.close();
	           		conexion.close();
	           	%>
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