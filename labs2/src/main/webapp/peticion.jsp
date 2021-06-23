<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.*" %>
<%@ page import="curso.Configuracion" %>
<%@ page import="javax.naming.*" %>
<%@ page import="java.util.*" %>

<%
// Introduciendo retraso
Thread.sleep(Configuracion.TIEMPO_DEMORA_JAVA);

// Query a BBDD con retraso
Context contexto = new InitialContext();
DataSource datasource = (DataSource)contexto.lookup("java:/comp/env/jdbc/miTestDB");
Connection conexion = datasource.getConnection();
Statement statement = conexion.createStatement();
ResultSet resultado = statement.executeQuery("SELECT SLEEP("+Configuracion.TIEMPO_DEMORA_DB+")");
conexion.close();

//Creando objetos:
Hashtable objetos=new Hashtable();
for (int i=0;i<Configuracion.getObjetosPeticion();i++){
    objetos.put(UUID.randomUUID().toString(),UUID.randomUUID().toString());
}
%>

<html>
    <head>
        <title>Aplicacion de Prueba</title>
    </head>
    <body>
        <h1>Peticion realizada satisfactoriamente con la configuracion:</h1>

        <p>Demora: <%=Configuracion.getTiempoDemoraJava()%></p>
        <p>Demora BBDD: <%=Configuracion.getTiempoDemoraDb()%></p>
        <p>Objetos en Cache: <%=Configuracion.getObjetosCache()%></p>
        <p>Objetos por peticion: <%=Configuracion.getObjetosPeticion()%></p>
        
        <p><a href="./configuracion.jsp">Cambiar configuracion</a></p>
    </body>
</html>
