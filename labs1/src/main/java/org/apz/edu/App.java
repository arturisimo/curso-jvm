package org.apz.edu;

import java.util.*;
import java.lang.Exception;

/**
 * Prueba de stress del sistema
 * 
 * Persistimos en un mapa String aleatorios hasta que nos da un Heap memory
 * 
 */
public class App  {

    public static final int TOTAL = 1000000000;

    public static void main( String[] args ) throws Exception {
        
        Map<String, String> tabla = new HashMap<>();
        int cont = 0;
        for (int i = 0; i < TOTAL; i++) {
            cont++;
            String aleatorio = UUID.randomUUID().toString();
            String aleatorio2 = UUID.randomUUID().toString();
            tabla.put(aleatorio, aleatorio2);
            if (cont==1000) {
                Thread.sleep(1);
                cont = 0;
            }    
        }

    }
}
