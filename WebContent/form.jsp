<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <title>BBDD insertar</title>

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
              
              <h3 class="masthead-brand">Insertar nuevo registro</h3>
				
				<%
					// Mostrar mensajes
					if (request.getAttribute("msg") != null ){
						out.print ("<h4>"+ request.getAttribute("msg")+ "</h4>" );
					}				
				%>
				
            </div>
          </div>

		


          <div class="inner cover">
           
           	<form action="insertar" method="post">
           		
           		<input type="text" name="nombre" placeholder="Tu nombre" required tabindex="1" autofocus>
           		<br>
           		<input type="text" name="nota" placeholder="Tu nota" required tabindex="2">
           		<br>
           		<input type="text" name="telefono" placeholder="Tu telefono" required tabindex="3">
           		<br>
           		<br>
           		<input type="reset" value="Limpiar formulario">
           		<input type="submit" value="Crear">
           	
           	
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



    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="js/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>