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
								<li><a href="index.jsp">Home</a></li>
								<li class="active"><a href="JDBC.jsp">JDBC</a></li>
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
					
											
						<% 
							Class.forName("com.mysql.jdbc.Driver"); 
							Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost:3306/skalada","root", "");
							Statement s = conexion.createStatement(); 
							String sql = "SELECT * FROM `test`";						
							ResultSet rs = s.executeQuery (sql);
						%>						
						<div class="resultado">
							<table>
								<tr>
									<th>ID</th>
									<th>NOMBRE</th>
									<th>NOTA</th>
									<th>TELEFONO</th>
									<th>FECHA</th>
									<th>Opc.</th>
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
											out.print("<td><a href='eliminar?id=" + rs.getString("id") + "'><span class='icon-trashcan red'></span></td>");
										out.print("</tr>");	
									}
								%>
							</table>							
						</div> <!-- END .resultado -->
						<%
							conexion.close();
						%>						
					</div>
					
					<a href="form.jsp"><button class="btn-lg">Nuevo Registro</button></a>
					
									
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