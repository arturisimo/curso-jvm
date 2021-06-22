package org.apz.edu;

import java.util.*;
import java.lang.Exception;
import java.lang.ref.WeakReference;

/**
 * Prueba de stress del sistema
 * 
 * Uso de java.lang.ref.WeakReference;
 * 
 */
public class ReferenceApp  {

    public static final int TOTAL = 215000000;

    public static void main( String[] args ) throws Exception {
        
        Map<WeakReference<String>, WeakReference<String>> tabla = new HashMap<>();
        int cont = 0;
        for (int i = 0; i < TOTAL; i++) {
            cont++;
            String aleatorio = UUID.randomUUID().toString();
            String aleatorio2 = UUID.randomUUID().toString();
            tabla.put(new WeakReference<String>(aleatorio), new WeakReference<String>(aleatorio2));
            if (cont==1000) {
                Thread.sleep(1);
                cont = 0;
            }    
        }

    }
}
