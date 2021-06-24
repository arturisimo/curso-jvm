package org.apz.edu;

import java.util.*;
import java.lang.Exception;

/**
 * Programcion funcional java Uso de java.util.function
 */
public class FunctionApp  {

    public static final int TOTAL = 1000*000;

    public static void main( String[] args ) throws Exception {
 
        List<Integer> miLista= new ArrayList<>();
        for(int i=0; i < 300; i++) {
            miLista.add(i);
        }

        //funcion predicado: Eliminar los pares
        //miLista.removeIf(i->i%2==0);

        //uso filter
        System.out.println("USO FILTER " + miLista.size());
        Integer max = miLista.stream()
                        .filter(i->i%2==0)
                        .map(i->i*4)
                        .max(Integer::compare)
                        .orElse(0);

        System.out.println("USO FILTER " + max);


        //Map
        Map<String,String> mapa = new HashMap();
        mapa.forEach((key,value) -> {
            System.out.println(key + " > " + value);
        });   
        
        
        //algoritmo de orden(n) tantas operaciones como elementos tenga la lista
        for (int i=0; i < 300; i++) {
            if (miLista.contains(i))  System.out.println("lista contiene i");
        }

        //algoritmo de orden(1) mucho mas eficiente
        Set<Integer> miSet = new HashSet(miLista);
        for (int i=0; i < 300; i++) {
            if (miSet.contains(i))  System.out.println("set contiene " + i);
        }
    }
}