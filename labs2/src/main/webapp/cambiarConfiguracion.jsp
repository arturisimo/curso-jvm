<%@ page import="curso.Configuracion" %>
<%
            Configuracion.setTiempoDemoraJava(Integer.parseInt(request.getParameter("tiempo_demora_java")));
            Configuracion.setTiempoDemoraDb(Integer.parseInt(request.getParameter("tiempo_demora_db")));
            Configuracion.setObjetosCache(Integer.parseInt(request.getParameter("objetos_cache")));
            Configuracion.setObjetosPeticion(Integer.parseInt(request.getParameter("objetos_peticion")));
        %>
<html>
    <head>
        <title>Aplicacion de Prueba</title>
    </head>
    <body>
        <h1>Cambios realizados</h1>
        
        <p>Nueva configuracion:</p>
        <p>Demora: <%=Configuracion.getTiempoDemoraJava()%></p>
        <p>Demora BBDD: <%=Configuracion.getTiempoDemoraDb()%></p>
        <p>Objetos en Cache: <%=Configuracion.getObjetosCache()%></p>
        <p>Objetos por peticion: <%=Configuracion.getObjetosPeticion()%></p>

        <p><a href="./configuracion.jsp">Cambiar configuracion</a></p>
        <p><a href="./index.jsp">Inicio</a></p>
        <p><a href="./peticion.jsp">Peticion</a></p>
    </body>
</html>
