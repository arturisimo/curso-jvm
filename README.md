# Curso de optimizacion de la JVM

## Calibracion de un servidor

Un servidor de aplicaciones se necesita saber hasta donde da de sí: (RAM , CPU ...). Cuantas peticiones es capaz de procesar

De qué depende el rendimiento de la aplicación (tiempo de respuesta (está entorno 500ms)) 
* Comunicaciones entre servidor Latencia 20ms
* Cantidad de accesos a sistemas externo que se realizan:
   * Oracle Database JDBC
   * Solr HTTP
 
 Para hacer menos accesos existen varias estrategias:
   * Cache
   * Empaquetar peticiones  
 
La capacidad de carga lo define los recursos CPU / RAM

El rendimiento esta definido por las comunicaciones, por la configuración de los pools de conexiones a BBD y de threads

**Pool de acceso a BBDD**

La consulta a la BBDD lo realiza el driver, que lo gestiona el servidor de aplicaciones (por una delegacion de responsabilidades, el administrador de aplicacion gestiona la información de acceso, no los desarrolladores )

El servidor de aplicaciones aporta un Pool de conexiones - conjunto de conexiones dinamico preestablecidad con la BBDD. Si no hay suficientes hay una cola de conexiones que habrá que  gestionar para su optimización

Qué implica abrir una conexion a BBDD? 
En el servidor de aplicaciones arrancar un nuevo proceso a nivel de SO > 1seg - 2seg

**Pool threads**

si no hay suficientes threads las peticiones se encolan. El número de de ejecutores depende de los recursos. Los recuros (CPU/Ram/Capacidad I/O Disco) límita el número de threads que se pueden crear

Un servlet es ejecutado por un thread
Cada peticiones es atendida en el servidor de aplicaciones por un thread. El thread se gestiona en el pool de ejecutores

Configuración 

* Pool de conexiones a BBDD (min,max, incrementales)
* Pool de thread (ejecutores)

+info: https://www.datadoghq.com/blog/tomcat-architecture-and-performance/#working-with-tomcat-metrics

**Cache**

Tomcat con uso de cache, se que persiste objetos en la zona del heap OLD durante un tiempo X. Si el sistema de cache está bien implementado deberia persistir solo si hay espacio.

**Sessions**

En el aplicaciones JEE tenemos Sessions (objeto que persiste informacion del usuario en una zona de la RAM) La sesión se indentifica mediante un JSESSIONID que esta en la cookie en el navegador.

Una estrategia podría ser si no hay espacio en la RAM liberar espacio de la session.

Es conveniente tener un sistema de persistencia externo para las sesiones (En Absys cacheo de sesiones en hazelCast)

## SERVIDORES EN HD

En un entorno de produccion, hay que tener la politica de HD de la empresa. Teniendo en cuenta que el servidor va a estar en cluster
Si hay dos maquinas en un cluster si una se cae la otra debe poder asumir la carga:

* Tomcat1 40% (CPU/RAM)
* Tomcat2 40%

Ejemplo real Sistema bancario:  4 servidores en weblogic. CPU no puede superar el 25%. App crítica pueden caer 3 servidores y el sistema seguiria funcionando.

                              Tomcat 1 (App A)
                            /
    Client -> Balanceador - 
                            \
                              Tomcat 2 (App 2)

El balanceador es capaz de ubicar la sesion  (a partir del JSESSIONID de la cookie del cliente) indepedientemente de en que servidor se encuentres 


## MEMORIA JVM

Java gestiona la memoria RAM en la JVM

Las variables permiten almacenar y recuperar información que se encuentra en la memoria RAM de un ordenador, la variable es una referencia a un valor; apunta a una determinada parte de la memoria.

  Integer numero = 7;

* 7 reserva un espacio en memoria y escribe en la ram un 7 
* int numero Genera una variable (numero) para referenciar a número enteros
* = hace que la referencia apunte al valor

  numero = 18;

* reserva un nuevo espacio de memoria  y escribe en la ram un 18
* cambia la referencia de 7 a 18

En este caso hay dos dos zonas de memoria una para 7 y otra para 8. Posteriormente el GC eliminará la memoria ocupada por el 8. 

A diferencia de C que utiliza funciones como malloc para solicitar un bloque de memoria y free para liberarlo; de está manera el uso de la memoria es más óptimo. 

En java la gestión de memoria está menos optimizada, pero se ha simplficado el desarrollo ya que la gestión de la memoria lo hace la JVM. Pero es importante el control del memoria y el uso del GC para que no se den outofmemory errors

**Memoria HEAP**

* En el heap (RAM) se almacenan los objetos que se instancian Cuando el servlet ha terminado su ejecución los objetos creados no sirven para nada, son basura.

* La configuración de la app, no se borran se dicen que son estáticas (static)

* Cache > accesso rápido a información. 

**GARBAJE COLLECTOR GC**

Conjunto de hilos que mira que objetos hay en la RAM que no está referenciados.

* 1ª etapa: los objetos no referenciados se marcan para su borrado
* 2ª etapa: se eliminan los objetos marcados 

Por tanto, mientras mayor sea la RAM mas costoso será el proceso de GC. Para que sea menos costoso el proceso se divide la heap en varias zonas

* EDEN - Objetos nuevos - 25%
* OLD - Objetos viejos - 75%

Cuando sea necesario se ejecutará el GC ( Aunque se puede forzar System.gc() pero es considerado un mala practica)

Cuando se llena el eden se ejecuta el GC en el eden. Al ejecutar se eliminar los no referenciados y los referenciados pasan al old. No se hace directamente pasan los survivors A y B que funcionan como un buffer.

Cuando se ejectuta el GC y el OLD está lleno se hace un full GC. tarda 10 veces mas que el GC del eden

Si el GC no puede vaciar se produce un outofmemory. Antes de que se de el outofmemory se ejecuta el bucle el GC hasta que hace que baje el rendimiento hasta que al final colapsa.

Hay que monitorizar la RAM de JVM y del SO. Se puede aumentar la RAM de la JVM hasta el límite de RAM que tenga el SO host. Suele ser un 50-60% el JVM del SO. Despues hay que calibrar el uso:

Parametrizar cantidad de memoria del HEAP:
* Xms Memoria inicial
* Xmx Memoria máxima

La recomendación es que estos valores sean iguales

Existen varios algoritmos de GC. Por defecto se hace GC en serie
Cambio GC en paralelo

 -XX:+UseParallelGC

**Compilacion**

compilador javac genera el bytecode, optimiza el código

dspues la JVM tiene un proceso de interpretado a codigo ejecutable directamente por el SO (código maquina)
la interpretacion se hace en tiempo de ejecucion por el JIT Just in Time Compiler, que también optimiza el código.

En la version 1.2 JVM se desarrollo HotSpot que tiene un cache de compilaciones 
La JVM detecta los puntos calientes los cachea y los optimiza

Con el HotSpot se consigue que el rendimiento sea igual que los lenguajes compilados

Las aplicaciones java tiene un **warm time up** para la optimización

+ info: https://notasdeit.wordpress.com/2014/07/29/afinamiento-de-garbage-collector-de-una-jvm-de-java/

## visualvm

permite monitorizar VM locales y remotos. Instalar plugin VisualGC

paramétros de la JVM

* Logs GC -Xloggc:/tmp/gc.log

* Dump de objetos del heap:-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp/memory.hprof  

Los archivos hprof se pueden importar al visualvm 

**Conexion Remota JMX**

service:jmx:rmi:///jndi/rmi://[HOST]:[JMXREMOTE_PORT]/jmxrmi

Comandos proceso java

 -Dcom.sun.management.jmxremote
 -Dcom.sun.management.jmxremote.port=12345 
 -Dcom.sun.management.jmxremote.authenticate=false 
 -Dcom.sun.management.jmxremote.ssl=false 
 -Dcom.sun.management.jmxremote.rmi.port=12346


## jmeter

Herramientas para pruebas de rendimiento (performance). 

Se establece la línea base teniendo en cuenta el rendimiento (tiempo de respuesta) de un usuario. 

A partir de ahí podemos calibrar simulando peticiones (nº de hilos), controlando la CPU/RAM Si el tiempo de respuesta aumenta el servicio se está degradadando ya que las peticiones se encolan.

Se guardan los planes de pruebas en archivos .jmx 

![monitorizacion Tomcat](doc/monitorizacionJVM.png)


## jconsole

Un proceso puede entrar en deadlock si intentan acceder a un mismo recurso. Si un código tiene un sychronize puede provocar deaklock lo que constitue un cuello de botellas > Uso código thread-safe.

MBeans podemos acceder a información. Jconsole es muy util para visualizacion de mbeans

![jconsole](doc/jconsole.png)

MBeans

![mbeans_jconsole](doc/mbeans_jconsole.png)

Thred Pool 

![thread pool](doc/threadpool_jconsole.png)

## Java mission Control jmc

https://adoptopenjdk.net/jmc.html

Personalizacion gráficas de métricas en funcion de Mbeans

![chart jmc](doc/chartDBConnections_2_jmc.png)

Flight recorder - grabacion de métrica para su posterior análisis (fichero jfr)

![flight recording](doc/flight_recording_jmc.png)

![flight recording](doc/jmr_jmc.png)

## Plan de acción de monitorización de una app

* Definir un plan de pruebas en el Jmeter
  Existe la posbilidad de configurar un proxy para copiar las acciones de un usuario de la app
* Cálculo línea base de la métrica en el Jmeter (un único hilo)
  Estableciendo el mejor funcionamiento esperable  (teniendo en cuenta el warm time up)
* Equipo desarrollo debe Intentar optimizar el código para mejorar la línea base.
    * Herramientas calidad - SonarQube
    * identificar partes más lentas (Jmeter)
    * BBDD : anális conexiones. intentar bajar el número acceso o mejor las consultas (revisar plan de ejecución)

* Sistemas ha de asegurarse que el rendimiento no se degrada para el volumen esperado de usuarios
    * Pruebas de rendimiento: 150-200% de la capadidad máxima estimada 
    * Monitorización (jmc, jconsole, visualvm) para ubicar el qué momento se degrada e identificar cuellos de botellas.
      Los cuellos de botellas estarán principalmente en :
        * numero de ejecutores
        * número de conexiones a BBDD
        * GC

Hasta el límite de los recursos del harware CPU,Memoria,I/0. Para no saturar hay que escalar:

* Escalamiento vertical cambiar a una maquina con más recursos
* Escalamiento horizontal añadir servidores al cluster

## Repositorio de profesor

https://github.com/IvanciniGT/cursoOptimizacionJava






