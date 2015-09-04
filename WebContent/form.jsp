<!DOCTYPE html>
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
              <h3 class="masthead-brand">Insertar nuevo registro</h3> 
              
              <% //mostrar mensajes
              	if( request.getAttribute("msg")!= null ){
              		out.print("<h4>" + request.getAttribute("msg") + "</h4>");
              	}
              

              %>
                         
            </div>
          </div>
			
			<%
		
			Persona p = (Persona)request.getAttribute("alumno");
			if ( p == null ){
				 p = new Persona("");
			}
		
			%>


          <div class="inner cover">
          	
          	<form action="insertar" method="post">
          		<fieldset>
					<legend>Datos Registro</legend>
	          		<p>
	          		<label for="nombre">Nombre: </label><br>
					<input type="text"
						   name= "nombre" 
						   value="<%=p.getNombre()%>"
						   id="nombre"
						   required pattern="[a-zA-ZáéíóúñÁÉÍÓÚÑ]{2,256}"	 
					       placeholder="Tu nombre"				       
					       autofocus
					       tabindex="1"			       
					       >
					</p>
					
					<p>
					<label for="nota">Nota: </label><br>
					<input type="text" id="nota" name="nota"
					       placeholder="Tu nota de 0-10"
					       required				        
						   >
					</p>
					
					<p>
					<label for="telefono">Tel&eacute;fono: </label><br>
	    			<input type="tel" 
	    				   id="telefono" name="telefono" 
	    				   required
	    				   placeholder="Tu telefono"
	    				   >
	          		</p>
	          		
	          		<br>
	          		<p>
	          		<label for="date">Fecha: </label>
	          		<input type="date" max="2015-12-31" min="2012-01-01" name="fecha"
	          				required
	          				/>
	          		</p>
	          		
	          		<br>
	          		<input type="submit" value="Crear">
	          		<input type="reset"  value="Limpiar">
	          	
          		</fieldset>
          	</form>
                                     
          </div>

          <div class="mastfoot">
            <div class="inner">
              <p>Documentaci�n <a href="http://www.formacion.ipartek.com/campus/">Ipartek Campus</a></p>
              <p>Codigo Fuente <a href="https://github.com/">GITHUB</a></p>
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