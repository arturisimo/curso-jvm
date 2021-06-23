package curso;

import java.util.*;

public class Configuracion{

    public static Hashtable cache;
    public static int TIEMPO_DEMORA_JAVA=1000;
    public static int TIEMPO_DEMORA_DB=1;
    public static int OBJETOS_CACHE=50;
    public static int OBJETOS_PETICION=5;

    static{
        Configuracion.actualizarCache();
    }

    private static void actualizarCache(){
        Configuracion.cache=new Hashtable();
        for (int i=0;i<OBJETOS_CACHE;i++){
            Configuracion.cache.put(UUID.randomUUID().toString(),UUID.randomUUID().toString());
        }
    }

    public static void setTiempoDemoraJava(int nuevoValor){
        Configuracion.TIEMPO_DEMORA_JAVA=nuevoValor;
    }
    public static void setTiempoDemoraDb(int nuevoValor){
        Configuracion.TIEMPO_DEMORA_DB=nuevoValor;
    }
    public static void setObjetosCache(int nuevoValor){
        Configuracion.OBJETOS_CACHE=nuevoValor;
        Configuracion.actualizarCache();
    }
    public static void setObjetosPeticion(int nuevoValor){
        Configuracion.OBJETOS_PETICION=nuevoValor;
    }

    public static int getTiempoDemoraJava(){
        return Configuracion.TIEMPO_DEMORA_JAVA;
    }
    public static int getTiempoDemoraDb(){
        return Configuracion.TIEMPO_DEMORA_DB;
    }
    public static int getObjetosCache(){
        return Configuracion.OBJETOS_CACHE;
    }
    public static int getObjetosPeticion(){
        return Configuracion.OBJETOS_PETICION;
    }

}