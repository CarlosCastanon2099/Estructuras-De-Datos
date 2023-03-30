package mx.unam.ciencias.edd.proyecto1;
import mx.unam.ciencias.edd.*;


/**
 * Clase para la ejecucion de las banderas -r y -o.
 */
public final class banderas {
    /* La lista de nuestras banderas. */
    private static Lista<String> listaDeBanderas = new Lista<String>();

    /* Variable de tipo String para que funcione el constructor de banderas*/
    String[] argumentos;
    /* La bandera de guardado, la que mas lata me dio */
    static String banderaDeGuardado = "-o";
    /* La bandera de reversa, todo lo contario a la problematica de arriba */
    static String banderaDeReversa = "-r";

    

    /**
    * Clase para la ejecucion de las banderas -r y -o.
    * como necesitamos meter las banderas por linea de comandos
    * nos apoyamos de nuestro amigo String[] args
    */
    public banderas(String[] args) {
        argumentos = args;
    }
    
    /** 
    * Metodo para regresar la lista de banderas.
    * @return la lista de banderas.
    */
    public static Lista<String> getlistaDeBanderas() {
        return listaDeBanderas;
    }
    
    /** 
    * Metodo para buscar si nos pasaron la bandera de reversa
    * @return true si se tiene la bandera de reversa, false en el caso contrario. 
    */
    public boolean printReverse() {
        for(String esReversa : argumentos){
            if(esReversa.equals(banderaDeReversa)){
                return true;
            }
        }

        return false;
    }
    
    

    
    /** 
    * Metodo para buscar si nos pasaron la bandera de guardado y en dado caso proceder a guardar
    * @return null si no se tiene la bandera de guardado
    */
    public String banderaGuarda() {
        for (int i = 0; i < argumentos.length; i++)
            if (argumentos[i].equals(banderaDeGuardado))
                if (argumentos.length > i+1)
                    return argumentos[i+1];
                else
                    throw new IllegalArgumentException("Explote, si no pusiste archivo.txt -o fue por eso, si no preocupate, banderas.java linea 65.");

        return null;
    }

    /** 
    * Metodo para separar las banderas de los archivos 
    * para que las funciones de ambos sean correctasy no pasen cosas raras.
    *
    * @return los archivos de entrada.
    */
    public Lista<String> fuentesEntrada() {
        Lista<String> archivosEntrada = new Lista<String>();

        for (int i = 0; i < argumentos.length; i++)
            if (argumentos[i].equals(banderaDeReversa))
                i++;
            else if (!argumentos[i].equals(banderaDeGuardado))
                archivosEntrada.agrega(argumentos[i]);

        return archivosEntrada;
    }
    
 

    
}
