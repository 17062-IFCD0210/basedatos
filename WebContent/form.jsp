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
								<li class="active"><a href="form.jsp">JDBC</a></li>
								<li><a href="#">Patron DAO</a></li>
								<li><a href="#">Hibernate</a></li>
							</ul>
						</nav>
					</div>
				</div>

				<div class="inner cover">
					<%
						//Mostrar mensaje
						if (request.getAttribute("msg") != null){
							out.print("<div class='msg'>");
							out.print("<h2>" + request.getAttribute("msg") + "</h2>");
							out.print("</div>");
						}					
					%>
					
					<h1 class="cover-heading">Insertar Nuevo registro</h1>
					
					
										
					<form action="insertar" method="post">
						
						<input type="text" name="nombre" placeholder="Tu nombre" 
						required="required" autofocus="autofocus" tabindex="1">
						<br><br>
						<input type="text" name="nota" placeholder="Tu nota" 
						tabindex="2">
						<br><br>
						<input type="text" name="telefono" placeholder="Tu telefono" 
						tabindex="3" pattern="[0-9]{9,15}">
						<br><br>						
						<input type="submit" class="btn-lg" value="Crear">
						
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