<!DOCTYPE html>
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
            <a href="form.jsp">Insertar nuevo registro</a>
            <p class="lead">Registros</p>
            
          
            <%
            	Class.forName("com.mysql.jdbc.Driver");
            	Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/skalada","root", "");
            	
            	// Preparamos la consulta 
            	Statement st = conexion.createStatement(); 
            	String sql = "SELECT * FROM `test`";
            	ResultSet rs = st.executeQuery (sql);
            	
            	//recorrer datos del resultado
            	out.print("<table>");
            	
            	out.print("<tr>"); 
	            	out.print("<th>Id</th>");
	            	out.print("<th>Nombre</th>");
	            	out.print("<th>Nota</th>");
	            	out.print("<th>Telefono</th>");
	            	out.print("<th>Fecha</th>");
	            	out.print("<th>Eliminar</th>");
	            	out.print("<th>Editar</th>");
	            	
            	while (rs.next()){ 
            		out.print("</tr>");	 						
            		out.print("<td>" + rs.getInt("id") + "</td>");
            		out.print("<td>" + rs.getString("nombre") + "</td>");
            		out.print("<td>" + rs.getFloat("nota") + "</td>");
            		out.print("<td>" + rs.getInt("telefono") + "</td>");
            		out.print("<td>" + rs.getDate("fecha") + "</td>");

            		          		
            		           		
				}
            	out.print("</table>");
            	
            	
            	//cerrar conexiones
            %>
          
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