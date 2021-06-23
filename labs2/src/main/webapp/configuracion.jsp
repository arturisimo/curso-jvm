<%@ page import="curso.Configuracion" %>
<html>
    <head>
        <title>Aplicacion de Prueba</title>
    </head>
    <body>
        <h1>Configuracion</h1>
        <form method="POST" action="./cambiarConfiguracion.jsp">
            
            <label for="tiempo_demora_java">Tiempo de demora JAVA: </label>
            <input type="text" id="tiempo_demora_java" name="tiempo_demora_java" value='<%=Configuracion.getTiempoDemoraJava()%>' ><br><br>

            <label for="tiempo_demora_db">Tiempo de demora BBDD: </label>
            <input type="text" id="tiempo_demora_db" name="tiempo_demora_db" value='<%=Configuracion.getTiempoDemoraDb()%>' ><br><br>

            <label for="objetos_cache">Objetos en cache: </label>
            <input type="text" id="objetos_cache" name="objetos_cache" value='<%=Configuracion.getObjetosCache()%>' ><br><br>

            <label for="objetos_peticion">Objetos peticion: </label>
            <input type="text" id="objetos_peticion" name="objetos_peticion" value='<%=Configuracion.getObjetosPeticion()%>' ><br><br>

            <input type="submit" value="Cambiar">
        </form>
        <p><a href="./index.jsp">Cancelar</a></p>
    </body>
</html>
