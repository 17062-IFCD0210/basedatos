<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ipartek.formacion.basedatos.bean.Persona"%>
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
						<h3 class="masthead-brand">Conexion Base Datos</h3>
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




				<div class="inner cover">
					<h1 class="cover-heading">Aprende Base Datos</h1>
					<%
						//Mostrar mensaje
						if (request.getAttribute("msg") != null) {
							out.print("<h4>" + request.getAttribute("msg") + "</h4>");
						}
					%>

					<a class="btn btn-default mb15" href="inicio?accion=1&id=-1">Insertar nuevos	registros</a>
					<br>
					<a class="btn btn-primary" href="inicio?accion=0&filtro=1" role="button">Aprobados</a>
            		<a class="btn btn-primary" href="inicio?accion=0&filtro=2" role="button">Suspendidos</a>
            		<a class="btn btn-primary" href="inicio?accion=0&filtro=0" role="button">Todos</a>
            		<br><br>

					<%
						//recuperar atributo de listado personas 
						ArrayList<Persona> alumnos = (ArrayList<Persona>) request.getAttribute("alumnos");
						if (alumnos == null) {
							alumnos = new ArrayList<Persona>();
						}
					%>
					<div class="panel panel-info dark-panel">
						<div class="panel-heading">Registros</div>
						<div class="panel-body">
							<table class="table table-striped">
								<thead>
									<tr>
										<td>ID</td>
										<td>Nombre</td>
										<td>Nota</td>
										<td>Telefono</td>
										<td>Fecha</td>
										<td></td>
										<td></td>
									</tr>
								</thead>
								<tbody>
									<%
										Persona p = null;
										for (int i = 0; i < alumnos.size(); i++) {
											p = alumnos.get(i);
									%>
									<tr>
										<td><%=p.getNombre()%></td>
										<td><%=p.getNota()%></td>
										<td><%=p.getTelefono()%></td>
										<td><%=p.getFecha()%></td>
										<td><a href="inicio?accion=1&id=<%=p.getId()%>"><span
												class='glyphicon glyphicon-pencil editar' aria-hidden='true'></span></a></td>
										<td><a href="inicio?accion=2&id=<%=p.getId()%>"><span
												class='glyphicon glyphicon-trash editar' aria-hidden='true'></span></a></td>
									</tr>
									<%
										}//end for
									%>
								</tbody>
							</table>
						</div>
					</div>
					
										<div class="row">
						<div class="col-md-12">
							<div class="panel panel-info datos">
								<div class="panel-heading">Datos Conexion</div>
								<div class="panel-body">
									<p>
										Servidor:
										<mark>localhost</mark>
									</p>
									<p>
										Base de Datos:
										<mark>skalada</mark>
									</p>
									<p>
										Puerto:
										<mark>3306</mark>
									</p>
									<p>
										Usuario:
										<mark>root</mark>
									</p>
									<p>
										Password:
										<mark></mark>
									</p>
									<p>
										Tabla:
										<mark>test</mark>
									</p>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="panel panel-info">
								<div class="panel-heading">Abrir Conexion</div>
								<div class="panel-body">
									<code>
										Class.forName("com.mysql.jdbc.Driver");<br> Connection
										conexion = DriverManager.getConnection(
										"jdbc:mysql://localhost/skalada", "root", "");
									</code>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="panel panel-info">
								<div class="panel-heading">Crear y ejecutar SQL</div>
								<div class="panel-body">
									<code>
										Statement st = conexion.createStatement();<br> String sql
										= "SELECT * FROM `test`";<br> ResultSet rs =
										st.executeQuery(sql);
									</code>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="panel panel-info">
								<div class="panel-heading">Mostrar datos</div>
								<div class="panel-body">
									<code>
										while (rs.next()) {&lt;br&gt; out.print(" &lt;tr&gt;
										&lt;td&gt;" + rs.getInt("id") + "&lt;/td&gt; &lt;td&gt;" +
										rs.getString("nombre") + "&lt;/td&gt; &lt;td&gt;" +
										rs.getFloat("nota") + "&lt;/td&gt; &lt;td&gt;" +
										rs.getString("telefono") + "&lt;/td&gt; &lt;td&gt;" +
										rs.getDate("fecha") + "&lt;/td&gt; &lt;/tr&gt; ");<br> }
									</code>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="panel panel-info">
								<div class="panel-heading">Cerrar conexion</div>
								<div class="panel-body">
									<code> rs.close(); st.close(); conexion.close(); </code>
								</div>
							</div>
						</div>
					</div>
					
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