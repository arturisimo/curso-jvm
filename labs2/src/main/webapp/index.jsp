<%@ page import="curso.Configuracion" %>

<html>
    <head>
        <title>Aplicacion de Prueba</title>
    </head>
    <body>
        <h1>Bienvenido</h1>

        <h2>Configuracion actual</h2>

        <p>Demora: <%=Configuracion.getTiempoDemoraJava()%></p>
        <p>Demora BBDD: <%=Configuracion.getTiempoDemoraDb()%></p>
        <p>Objetos en Cache: <%=Configuracion.getObjetosCache()%></p>
        <p>Objetos por peticion: <%=Configuracion.getObjetosPeticion()%></p>


        <h2>Acciones</h2>

        <p><a href="./configuracion.jsp">Cambiar configuracion</a></p>
        <p><a href="./peticion.jsp">Realizar peticion</a></p>
    </body>
</html>
