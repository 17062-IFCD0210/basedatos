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



    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="js/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>