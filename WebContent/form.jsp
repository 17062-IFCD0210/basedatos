<!DOCTYPE html>

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
              <h3 class="masthead-brand">Base Datos</h3>
              <nav>
                <ul class="nav masthead-nav">
                	<li class="active"><a href="inicio">Home</a></li>
                	<li class="active"><a href="#">Insertar Nuevo Registro</a></li>
                </ul>
              </nav>
            </div>
          </div>

          <div class="inner cover">
          
          		<% 
	          		/*ResultSet rs = request.getAttribute("rs");
	          		
	               //mostrar mensajes
	               	if (request.getAttribute("msg") != null){
	               		out.print("<h4>" + request.getAttribute("msg") + "</h4>");
	               	}*/
               	%>
                  
	          <form action="insertar" method="post"> <!-- action="insertar" o URL mapping en el controlador (servlet) -->
	          
	          	<input type="text"
	          		   name="nombre"
	          		   placeholder="Inserta tu nombre"
	          		   required autofocus tabindex="1">
	          	<br>
	          	<input type="text" name="nota" required tabindex="2" placeholder="Inserta tu calificación">
	          	<br>
	          	<input type="text" name="telefono" required tabindex="3" placeholder="Inserta tu teléfono">
	          	
	          	<br>
	          	<br>
	          	<input type="reset" value="Limpiar">
	          	<input type="submit" value="Crear">
	            
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

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="js/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>