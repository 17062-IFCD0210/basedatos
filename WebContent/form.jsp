<!DOCTYPE html>

<%@page import="com.ipartek.formacion.basedatosProfe.util.UtilidadesFecha"%>
<%@page import="com.ipartek.formacion.basedatosProfe.bean.Persona"%>
<html lang="es">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>BBDD Insertar</title>
    
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
            
            <%
				Persona alumno = (Persona)request.getAttribute("alumno");
				String titulo = "Insertar nuevo registro";
				if ( alumno == null){
					alumno = new Persona("sin nombre");
				}else{
					titulo = "Modificando a " + alumno.getNombre();
				}
				
			%>
            
              <h3 class="masthead-brand"><%=titulo%></h3>
              <nav>
                <ul class="nav masthead-nav">
                	<li><a href="inicio">Home</a></li>
                	<!-- <li class="active"><a href="#">Insertar Nuevo Registro</a></li> -->
                </ul>
              </nav>
            </div>
          </div>

			
          <div class="inner cover">
              	
              	<%
              		String msg = (String)request.getAttribute("msg");
	              	if ( msg != null){
						out.print("<p>" + msg + "</p>");
					}
				%>
				
	          <form action="inicio" method="post"> <!-- action="insertar" o URL mapping en el controlador (servlet) -->
	          	<div class="area">
		          	<p>Nombre: 
			          	<input type="text"
			          		   name="nombre"
			          		   value="<%=alumno.getNombre()%>"
			          		   placeholder="Inserta tu nombre"
			          		   required
			          		   autofocus
			          		   tabindex="1">
		          	</p>
		          	<p>Nota: 
			          	<input type="text"
			          		value="<%=alumno.getNota()%>"
			          		name="nota"
			          		required
			          		tabindex="2"
			          		placeholder="Inserta tu calificación">
		          	</p>
		          	<p>Teléfono: 
			          	<input type="text"
			          		value="<%=alumno.getTelefono()%>"
			          		name="telefono"
			          		required
			          		tabindex="3"
			          		placeholder="Inserta tu teléfono">
		          	</p>
		          	<p>Fecha: 
			          	<input type="datetime-local"
			          		value="<%=UtilidadesFecha.timestampToString(alumno.getFecha())%>"
			          		name="fecha"
			          		tabindex="4"
			          		placeholder="Inserta la fecha">
		          	</p>
	          	</div>
	          	<br>
	          	<br>
	          	
	          	<input type="hidden" name="id" value="<%=alumno.getId()%>">
	          	
	          	<button type="reset" class="btn btn-success">Limpiar</button>
	          	
	          	<% if ( alumno.getId() == -1){ %>
	          		<button type="submit" class="btn btn-danger">Crear</button>
	          	<% }else{ %>
	          		<button type="submit" class="btn btn-danger">Modificar</button>
	          	<% } %>
	            
	          </form>
	          
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

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="js/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>