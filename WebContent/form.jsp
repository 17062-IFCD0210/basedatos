<!DOCTYPE html>
<%@page import="com.ipartek.formacion.basedatos.bean.Persona"%>
<html lang="es">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Base Datos BBDD</title>

<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/cover.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">

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
						<h3 class="masthead-brand"><%
						String action;
						String stringBtn;
						if (request.getAttribute("alumno")!=null){
							out.print("Editar registro");
							action = "4";
							stringBtn = "Editar";
						}else{
							out.print("Insertar nuevo registro");
							action = "3";
							stringBtn = "Crear";
							}
						String id = request.getParameter("id");
						%>
						</h3>
						<nav>
							<ul class="nav masthead-nav">
								<li class="active"><a href="inicio">Home</a></li>
								<li class=""><a href="#">JDBC</a></li>
								<li><a href="#">Patron DAO</a></li>
								<li><a href="#">Hibernate</a></li>
							</ul>
						</nav>
					</div>
				</div>

				<%
					Persona p = (Persona) request.getAttribute("alumno");
				%>

				<div class="inner cover">

					<%
						//Mostrar mensaje
						if (request.getAttribute("msg") != null) {
							out.print("<h4>" + request.getAttribute("msg") + "</h4>");
						}
					%>



					<form action="inicio?accion=<%=action%>&id=<%=id %>" method="POST">
						<div class="form-group">
							<label for="nombre">Nombre</label> <input required tabindex="1"
								autofocus="autofocus" class="form-control" name="nombre"
								id="nombre" value="<%if (p!=null) out.print(p.getNombre());%>" placeholder="nombre">
						</div>
						<div class="form-group">
							<label for="nota">Nota</label> <input class="form-control"
								tabindex="2" id="nota" required name="nota" value="<%if (p!=null) out.print(p.getNota());%>" placeholder="nota">
						</div>
						<div class="form-group">
							<label for="telefono">Telefono</label> <input
								class="form-control" tabindex="3" id="telefono" name="telefono"
								value="<%if (p!=null) out.print(p.getTelefono());%>" placeholder="Telefono">
						</div>
						<div class="form-group">
							<label for="fecha">Fecha</label> <input type="datetime-local" class="form-control"
								tabindex="3" id="fecha" name="fecha"
								value="<%if (p!=null) out.print(p.getFecha().toString().replace(" ", "T"));%>" placeholder="Fecha">
						</div>
						<button type="submit" tabindex="4" class="btn btn-primary"><%=stringBtn %></button>
						<button type="reset" tabindex="5" class="btn btn-warning">Limpiar</button>
					</form>
				</div>
				<div class="mastfoot">
					<div class="inner">
						<p>
							Documentación <a href="#">Ipartek Campus</a>
						</p>
						<p>
							Codigo Fuente <a href="#">GITHUB</a>
						</p>
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
