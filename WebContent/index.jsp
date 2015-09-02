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
								<li class="active"><a href="index.jsp">Home</a></li>
								<li><a href="form.jsp">JDBC</a></li>
								<li><a href="#">Patron DAO</a></li>
								<li><a href="#">Hibernate</a></li>
							</ul>
						</nav>
					</div>
				</div>

				<div class="inner cover">
					<h1 class="cover-heading">Aprende Conexión Base Datos</h1>
					<div>
						
						<div class="bd_datos">
							<p>Servidor: <mark>localhost</mark></p>
							<p>Base de Datos: <mark>skalada</mark></p>
							<p>Puerto: <mark>3306</mark></p>
							<p>Usuario: <mark>root</mark></p>
							<p>Password: <mark></mark></p>
							<p>Tabla: <mark>test</mark></p>
						</div><!-- END .bd_datos -->
						
						<div class="bd_proceso">
							<p class="lead"><span class="icon-number lead"></span> Cargando Driver: <code>com.mysql.jdbc.Driver</code></p>
							<% 
								Class.forName("com.mysql.jdbc.Driver"); 
							%>
							
							<p class="lead"><span class="icon-number2 lead"></span> Establecer Conexión: <code>jdbc:mysql://localhost:3306/skalada","root", ""</code></p>
							<%
								Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost:3306/skalada","root", "");
							%>
							
							<p class="lead"><span class="icon-number3"></span> Preparar Consulta: <code>SELECT * FROM `test`</code></p>
							<%	
								Statement s = conexion.createStatement(); 
								String sql = "SELECT * FROM `test`";						
								ResultSet rs = s.executeQuery (sql);
							%>
							
							<p class="lead"><span class="icon-number4"></span> Leer Resultados:</p>
								<div class="resultado">
									<table>
										<tr>
											<th>ID</th>
											<th>NOMBRE</th>
											<th>NOTA</th>
											<th>TELEFONO</th>
											<th>FECHA</th>
										</tr>
										<%
											while (rs.next()) 
											{ 
												out.print("<tr>");
													out.print("<td>" + rs.getString("id") + "</td>");
													out.print("<td>" + rs.getString("nombre") + "</td>");
													out.print("<td>" + rs.getString("nota") + "</td>");
													out.print("<td>" + rs.getString("telefono") + "</td>");
													out.print("<td>" + rs.getString("fecha") + "</td>");
												out.print("</tr>");	
											}
										%>
									</table>							
								</div> <!-- END .resultado -->
								
							<p class="lead"><span class="icon-number5"></span> Cerrar conexión: <code>close()</code></p>
							<%
								conexion.close();
							%>
						</div> <!-- END .bd_proceso -->
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