package org.apz.edu;

import java.util.*;
import java.lang.Exception;

/**
 * Programcion funcional java Uso de java.util.function
 */
public class Function2App  {

    public static final int TOTAL = 1000*000;

    public static void main( String[] args ) throws Exception {
        
        List<String> miLista= new ArrayList<>();
        for(int i=0; i < miLista.size(); i++) {
            System.out.println(miLista.get(i));
        }
        //mas eficiente
        for(String item : miLista) {
            System.out.println(item);
        }
        
        //desde JDK8
        //https://docs.oracle.com/javase/8/docs/api/java/lang/Iterable.html#forEach-java.util.function.Consumer-

        //List acepta forEach(Consumer) se puede hacer de esta manera
        //operador :: para referencia una funcion
        //mas eficiente y más rápido de desarrollar
        miLista.forEach(System.out::println);

        //abre hilos para hacer en paralelo para recorrer la collection
        //tiene sentido si la lista es muy grande
        //al no ser secuencial no se garatiza un orden
        miLista.parallelStream().forEach(System.out::println);

        java.util.function.Consumer<String> impresorFunction = System.out::println;
        miLista.parallelStream().forEach(impresorFunction);
        impresorFunction.accept("HOLA"); //imprime hola

        //es equivalente
        impresorFunction = RendimientoApp::printConsumer;
        
        //expresion lambda: referencia funciones anonimas
        miLista.parallelStream().forEach(txt -> System.out.println(txt));
    }

    //funcion consumer
    public static void printConsumer (String txt) {     
        System.out.println(txt);
    }

}
