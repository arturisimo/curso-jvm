# Uso del API java.lang.ref

Un objeto referenciado por weakreference es eliminado al hacerse un GC

Existen varios algoritmos de GC

G1 - GC por defecto en serie

Softrefence se elimina el valor cuando no hay más espacio heap disponible
Su uso es adecuado para caches. Teniendo en cuenta que se puede eliminar, así que antes de obtenerlos hay que comprobar si no existe


https://www.baeldung.com/java-weakhashmap


**Construccion proyect**

mvn archetype:generate -DarchetypeGroupId=org.apache.maven.archetypes -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4


**Ejecución**

  java -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp/memory.hprof -Xloggc:/tmp/gc.log -Xms200m -Xmx200m -cp target/lab1-1.0-SNAPSHOT.jar org.apz.edu.App


