<!DOCTYPE html>

<%@page import="java.util.ArrayList"%>
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
              <h3 class="masthead-brand">Conexión Base Datos</h3>
              <nav>
                <ul class="nav masthead-nav">
                  <li class="active"><a href="#">Home</a></li>
                  <li class=""><a href="#">JDBC</a></li>
                  <li><a href="#">Patrón DAO</a></li>
                  <li><a href="#">Hibernate</a></li>
                </ul>
              </nav>
            </div>
          </div>


          <div class="inner cover">
            <h1 class="cover-heading">Aprende Base de Datos</h1>
            
            
            <% 
            	//gestion de mensajes
            	if ( request.getAttribute("msg")!=null ){
            		out.print("<h4>" + request.getAttribute("msg") + "</h4>");	
            	}            	
            
            %>
            
            
    		<table id="bbdd">      
	            <tr>
	            	<td>Servidor: <small>localhost</small></td>
	            	<td>Base de Datos: <small>skalada</small></td>
	            	<td>Puerto: <small>3306</small></td>
	            	<td>Usuario: <small>root</small></td>
	            	<td>Password: </td>
	            	<td>Tabla: <small>test</small></td>
	            </tr>
            </table>
            <br>
            <br>
            <a href="form.jsp"><i>Insertar nuevo registro</i></a>
                     
            <p class="lead">Registros:</p>
            
            	<table>
            		<thead>
            	
            	<tr>
	            	<th>Nombre</th>
	            	<th>Nota</th>
	            	<th>Telefono</th>
	            	<th>Fecha</th>
	            	<th>Eliminar</th>
	            	<th>Editar</th>
	            </tr>
	            </thead>
  
	            <tbody>
	            <%
	            	//recuperar atributo de listado personas
	            	ArrayList<Persona> alumnos = 
		            	(ArrayList<Persona>)request.getAttribute("alumnos");
	            	if ( alumnos == null ){
	            		alumnos = new ArrayList<Persona>();
	            	}
	            	
	            	Persona p = null;
	            	for (int i=0; i < alumnos.size(); i++){
	            		p = alumnos.get(i);
	            		%>
	            			<tr>
	            				<td><%=p.getNombre()%></td>
	            				<td><%=p.getNota()%></td>
	            				<td><%=p.getTelefono()%></td>
	            				<td><%=p.getFecha()%></td>
	            				<td>
	            					<a href="inicio?id=<%=p.getId()%>&accion=2">
	        						<span class="glyphicon glyphicon-trash" style="color: red;"></span>
	   								</a>
	            				</td>
	            				<td>
	            					<a href="inicio?id=<%=p.getId()%>&accion=1">
	        						<span class="glyphicon glyphicon-pencil" style="color: #2e6da4;"></span>
	   								</a>
	            				</td> 

	            			</tr>	            		
	            		<%
	            		
	            	}//end for
	            	
	            %>
	             </tbody>
	           	 <tfoot>
	           	 	<tr>
	            		<td colspan="6">Mostrando: <%=alumnos.size()%></td>
	           	 	</tr>
	           	 </tfoot>	

				</table>

          <br>
            	<a class="btn btn-primary btn-sm" href="inicio?filtro=0" role="button">Aprobados</a>
            	<a class="btn btn-primary btn-sm" href="inicio?filtro=1" role="button">Suspendidos</a>
            	<a class="btn btn-primary btn-sm" href="inicio" role="button">Todos</a>
           
          <br>
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