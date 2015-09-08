<!DOCTYPE html>
<<<<<<< HEAD

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
			          	<input type="text"
			          		value="<%=alumno.getFecha()%>"
			          		name="fecha"
			          		required
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
=======
<%@page import="com.ipartek.formacion.basedatos.bean.Persona"%>
<html lang="es">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <title>BBDD Insertar</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
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
              <a href="inicio" class="btn btn-info">Volver</a>	
              <%
		
				Persona p = (Persona)request.getAttribute("alumno");
                String titulo = "Insertar nuevo registro";
				if ( p == null ){
					p = new Persona("");
				}else{
					titulo= "Modificar a " + p.getNombre();
				}
				
			%>
              
              <h3 class="masthead-brand"><%=titulo%></h3>
              
              
              
              <% //Mostrar mensaje
              	if(request.getAttribute("msg") != null) {
              		out.print("<h4>" + request.getAttribute("msg") + "</h4>");
              	}
              
              %>
              
            </div>
          </div>

          <div class="inner cover">
          
         	<form action="inicio" method="post">
         		<label for="nombre">Nombre: </label>
         		<br>
         		<input type="text" 
         		       name="nombre" 
         		       value="<%=p.getNombre()%>" 
         		       placeholder="Tu Nombre" 
         		       required 
         		       autofocus 
         		       tabindex="1">
         		<br><br>
         		
         		<label for="nota">Nota: </label>
         		<br>
         		<input type="text" 
         			   value="<%=p.getNota()%>"
         			   name="nota" placeholder="Tu Nota" 
         			   required>
         		<br><br>
         		
         		<label for="telefono">Telefono: </label>
         		<br>
         		<input type="text"
         		       value="<%=p.getTelefono()%>" 
         			   name="telefono" placeholder="Tu Telefono">
         		<br><br>
         		
         		<label for="fecha">Fecha: </label>
         		<br>
         		<input type="date" 
         			   name="fecha"
         			   value="<%=p.getTelefono()%>" 
         		>
         		<br><br>
         		
         		<input type="hidden" name="id" value="<%=p.getId()%>">
         		
				<input type="reset" value="Limpiar"  class="btn btn-warning">
				
				<% if ( p.getId() == -1 ) {%>         		
         			<input type="submit" value="Crear"  class="btn btn-success">
         		<%}else{ %>	
         			<input type="submit" value="Modificar" class="btn btn-success">
         		<%} %>
         	</form>
            
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


>>>>>>> branch 'master' of https://github.com/17062-IFCD0210/basedatos.git

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="js/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>
