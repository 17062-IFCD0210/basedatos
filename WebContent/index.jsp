<!DOCTYPE html>
<%@page import="com.ipartek.formacion.basedatos.modelo.DAOPersona"%>
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
							String tipo = (String)request.getAttribute("tipo");
							out.print("<div class='alert alert-dismissible alert-"+tipo+"' role='alert'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>x</span></button>" + request.getAttribute("msg") + "</div>");
						}
					%>

					<a class="btn btn-default mb15" href="inicio?accion=1&id=-1">Insertar
						nuevos registros</a> <br> <a class="btn btn-primary"
						href="inicio?accion=0&filtro=1" role="button">Aprobados</a> <a
						class="btn btn-primary" href="inicio?accion=0&filtro=2"
						role="button">Suspendidos</a> <a class="btn btn-primary"
						href="inicio?accion=0&filtro=0" role="button">Todos</a> <br>
					<br>

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
										<td><%=p.getId()%></td>
										<td><%=p.getNombre()%></td>
										<td><%=p.getNota()%></td>
										<td><%=p.getTelefono()%></td>
										<td><%=p.getFecha()%></td>
										<td><a href="inicio?accion=1&id=<%=p.getId()%>"><span
												class='glyphicon glyphicon-pencil editar' aria-hidden='true'></span></a></td>
										<td><a href="#" data-toggle="modal"
											data-target="#modalBorrar" data-nombre="<%=p.getNombre()%>"
											data-nota="<%=p.getNota()%>"
											data-telefono="<%=p.getTelefono()%>"
											data-fecha="<%=p.getFecha()%>" data-id="<%=p.getId()%>"><span
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



					<!-- Modal -->
					<div class="modal fade" id="modalBorrar" tabindex="-1"
						role="dialog" aria-labelledby="modalBorrar">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title black" id="myModalLabel">Seguro que
										desea eliminar?</h4>
								</div>
								<div class="modal-body">
									<h2 class="black">Datos</h2>
									<div id="datos_eliminar">
										<div class="row">
											<label class="black" for="nombre">Nombre:</label> <input
												id="nombre" disabled readonly class="black" name="nombre">

											<label class="black" for="nota">Nota:</label> <input
												id="nota" disabled readonly class="black" name="nota">
										</div>
										<div class="row">
											<label class="black" for="telefono">Telefono:</label> <input
												id="telefono" disabled readonly class="black"
												name="telefono"> <label class="black" for="fecha">Fecha:</label>
											<input id="fecha" disabled readonly class="black"
												name="fecha">
										</div>
										<div class="row black">
											<label><input type="checkbox" class="black"
												id="check_comprobacion"> Esta seguro que desea
												eliminar el registro?</label>
										</div>
									</div>

								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Cancelar</button>
									<button type="button" disabled class="btn btn-warning"
										id="eliminar" data-id="">Eliminar</button>
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

	<script type="text/javascript">
		$('#modalBorrar').on('show.bs.modal', function(event) {
			var button = $(event.relatedTarget)
			var id = button.data('id')
			var nombre = button.data('nombre')
			var nota = button.data('nota')
			var telefono = button.data('telefono')
			var fecha = button.data('fecha')
			var modal = $(this)
			modal.find('#eliminar').attr("data-id", id)
			modal.find('#nombre').val(nombre)
			modal.find('#nota').val(nota)
			modal.find('#telefono').val(telefono)
			modal.find('#fecha').val(fecha)
		})

		$('#eliminar').on('click', function() {
			var id = this.dataset.id
			document.location.href = "inicio?accion=2&id=" + id
		})

		$('#check_comprobacion').on('click', function() {
			$('#eliminar').prop('disabled', !this.checked);
		})
	</script>


</body>
</html>