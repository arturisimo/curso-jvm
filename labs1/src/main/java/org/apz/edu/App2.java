package org.apz.edu;

import java.util.*;
import java.lang.Exception;
import java.lang.ref.WeakReference;
import java.lang.ref.SoftReference;

public class App2 {

    public static void main( String[] args ) throws Exception {
        
       String txt;
       txt = "hola";
       System.out.println("txt: " + txt);

       WeakReference<String> weak = new WeakReference<String>("adios");
       System.out.println("weak: " + weak.get());

       WeakReference<String> soft = new WeakReference<String>("adios");
       System.out.println("soft: " + soft.get());

       //llamada expresa GC. Solicita un GC pero lo mism la JVM puede ignorarlo si lo estima
       //se ejecuta en otro hilo
       System.gc(); 
       
       Thread.sleep(5000);

       System.out.println("txt: " + txt);
       System.out.println("weak: " + weak.get());
       System.out.println("soft: " + soft.get());

    }
}
