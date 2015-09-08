<!DOCTYPE html>
<%@page import="com.ipartek.formacion.basedatos.bean.Persona"%>
<%@page import="java.util.ArrayList"%>
<html lang="es">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <title>Base Datos BBDD</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/cover.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
<style> 

	
	.verde{
		color:green;
	}
	
	.cuadro{
		border:7px solid white;
	}
	
	.tabla{
		border:1px solid white;
		margin:0 auto;
	}
	
	tbody{
		border:1px solid white;
	}
	.cabecera_tabla{
		   	background-color: white;
    		color: #0E00FF;
   		 	text-decoration: underline;
	}
	td{
		padding:10px;
		background-color: green;
		color: white;
	}
	button{
		background-color: red;
		color: white;
	}
	.modal-body{
		background-color: red;
	}
	.modal-body_editar{
		
		background-color: rebeccapurple;
	}
	
	.editar{
		background-color: white;
		color: red;
	
	}
	.crear{
		background-color: red;
	}
	.boton_nota{
		background-color: purple;
	}
	.total{
		color:black;
		background-color: white;
	}
	.blanco {
		list-style-type: none;
		background-color: white;
		color: black;
		width: 50%;
		margin: 0 auto;
	}
	small{
		color:red;
	}
	.lista_eliminar, .lista_editar{
		list-style: none;
		
		
	}
	.lista_eliminar p{
		color:black;
		}
</style>        
  </head>
  <body>
    


 <div class="site-wrapper">

      <div class="site-wrapper-inner">

        <div class="cover-container">

          <div class="masthead clearfix">
            <div class="inner">
              <h3 class="masthead-brand"><a href="inicio">Conexion Base Datos</a></h3>
              <nav>
                <ul class="nav masthead-nav">
                  <li class="active"><a href="#">Home</a></li>
                  <li class=""><a href="#">JDBC</a></li>
                  <li><a href="#">Patron DAO</a></li>
                  <li><a href="#">Hibernate</a></li>
                </ul>
              </nav>
            </div>
          </div>

		


          <div class="inner cover">
            <h1 class="cover-heading">Aprende Base Datos</h1>
             
              <ul class="blanco">
            	<li>Servidor: <small>localhost</small></li>
            	<li>Base de Datos: <small>skalada</small></li>
            	<li>Puerto: <small>3306</small></li>
            	<li>Usuario: <small>root</small></li>
            	<li>Password: </li>
            	<li>Tabla: <small>test</small></li>
            	<li> Mensage: <small> <% //Mostrar mensaje
		              	if(request.getAttribute("msg") != null) {
		              		out.print("<h4>" + request.getAttribute("msg") + "</h4>");
		              	}
              
              		%></small>
              	</li>
            </ul>
            
           
            <p class="lead">Registros</p>
    		<table class="verde tabla">
            	<thead class="cabecera_tabla">
	            	<tr>
	            		<td class="cabecera_tabla">Nombre</td>
	            		<td class="cabecera_tabla">Nota</td>
	            		<td class="cabecera_tabla">Telefono</td>
	            		<td class="cabecera_tabla">Fecha</td>
	            		<td class="cabecera_tabla">Editar</td>
	            		<td class="cabecera_tabla">Eliminar</td>
	            	</tr>
            	</thead>

				<tbody>
 			<% 	//recuperar atributo de listado personas
 				ArrayList<Persona> alumnos = (ArrayList<Persona>)request.getAttribute("alumnos");
				if (alumnos==null){
					alumnos=new ArrayList<Persona>();
				}
				Persona p = null;
				for (int i=0;i<alumnos.size();i++){
					p = alumnos.get(i);
					//out.print("<tr><td>" + p.getNombre() + "</td><td>" + p.getNota() +"</td><td>"+p.getTelefono()+"</td><td>"+p.getFecha() +"</td><td><a href='inicio?accion=1&id="+p.getId()+"'>E</a></td><td><a href='inicio?accion=2&id="+p.getId()+"'>X</a></td></tr>");
					%>
					<tr>
						<td><%=p.getNombre()%></td>
						<td><%=p.getNota()%></td>
						<td><%=p.getTelefono()%></td>
						<td><%=p.getFecha()%></td>
						<td><button type="button" class="editar" data-toggle="modal" data-target="#myModal1<%=+i%>">EDITAR</button></td>												
						<td><button type="button" class="eliminar" data-toggle="modal" data-target="#myModal<%=+i%>">ELIMINAR</button></td>
					</tr>

			
					
						<!-- Ventana Modal Eliminar -->
					<div class="modal fade col-md-6 col-md-offset-3" id="myModal<%=+i%>" role="dialog">
						<div class="modal-dialog">
					  
					    <!-- Modal content-->
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
										<h2 class="modal-title text-center text-danger"><i class="fa fa-exclamation-triangle"></i>Confirmacion de eliminacion</h2>
					  			</div>
					  			<div class="modal-body">
					    			<p>Estas seguro de que desea eliminar a:</p>
					    			<ul>
						    			<li class="lista_eliminar"><p>Id:<%=p.getId()%></p></li>
						    			<li class="lista_eliminar"><p> Nombre:<%=p.getNombre()%></p></li>						    			
						    			<li class="lista_eliminar"><p>Telefono:<%=p.getTelefono()%></p></li>
					    			</ul>
					  			<div class="modal-footer">						    			
					    			<a href="inicio?accion=2&id=<%=p.getId()%>" class="btn btn-danger">Eliminar</a>
					      			<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
					      		</div>
					      		</div>
					    	</div> <!-- END Modal content-->
					  	</div>
					</div> <!-- END Ventana Modal -->
					
									<!-- Ventana Modal Editar -->
					<div class="modal fade col-md-6 col-md-offset-3" id="myModal1<%=+i%>" role="dialog">
						<div class="modal-dialog">
					  
					    <!-- Modal content-->
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
										<h2 class="modal-title text-center text-danger"><i class="fa fa-exclamation-triangle"></i>Confirmacion de modificacion</h2>
					  			</div>
					  			<div class="modal-body_editar">
					    			<p>Estas seguro de que desea editar a:</p>
					    			<ul>
						    			<li class="lista_editar"><p>Id:<%=p.getId()%></p></li>
						    			<li class="lista_editar"><p> Nombre:<%=p.getNombre()%></p></li>						    			
						    			<li class="lista_editar"><p>Telefono:<%=p.getTelefono()%></p></li>
					    			</ul>
					  			<div class="modal-footer">						    			
					    			<a href="inicio?accion=1&id=<%=p.getId()%>" class="btn btn-danger">Editar</a>
					      			<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
					      		</div>
					      		</div>
					    	</div> <!-- END Modal content-->
					  	</div>
					</div> <!-- END Ventana Modal -->
				<%
				}
				%>
				</tbody>
				<tfoot>
					<tr>
						<td class="total" colspan="6">Mostrando: <%=alumnos.size()%></td>
					</tr>
				</tfoot>
			</table>
	<br>
            <a  class="btn btn-primary btn-ln boton_nota" href="inicio?filtro=0">Aprobados</a>
            <a class="btn btn-primary btn-ln boton_nota" href="inicio?filtro=1">Suspendidos</a>
            <a  class="btn btn-primary btn-ln"href="inicio">Todos</a>
            <a class="btn btn-primary btn-ln crear" href="inicio?accion=3">Crear</a>
            
   <br>
            <!--    <a href="form.jsp">Insertar nuevo registro</a>-->
          </div>

          <div class="mastfoot">
            <div class="inner">
              <p>Documentación <a href="#">Ipartek Campus</a></p>
              <p>Codigo Fuente <a target="_blank" href="https://github.com/17062-IFCD0210/basedatos">GITHUB</a></p>
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