package mx.unam.ciencias.edd.proyecto1;
import mx.unam.ciencias.edd.*;


/**
 * Clase para ordenar lineas.
 */
public class ordenameLaLinea {

    /* Constructor privado de ordenameLaLinea. */
    private ordenameLaLinea() {}

    /**
     * La complejidad pedida fue de O(nlogn) por lo tanto se utilizo el algoritmo de MergeSort.
     * ya que el es nuestro amigo que nunca nos falla, Quick Sort es simpatico 
     * pero nos puede agarrar desprevenidos y mandarnos a volar a O(n^2).
     * Con eso en mente 
     * 
     * Este es un Metodo para regresar una copia de la lista de líneas recibida, pero de manera ordenada
     * utilizando las lineas limpias de las cadenas de texto.
     * @param lista la lista recibida a ordenar.
     * @return la copia ordenada de la lista de líneas usando MergeSort.
     */
    public static Lista<limpiaLineaTexto> ordena(Lista<limpiaLineaTexto> lista) {
        return lista.mergeSort((linea1, linea2) -> linea1.getCadenaLimpia().compareTo(linea2.getCadenaLimpia()));
    }

    /**
     * Metodo para regresar una copia de la lista de líneas recibida, 
     * pero ordenada en forma reversa.
     * 
     * @param lista la lista a ordenar.
     * @return una copia ordenada y en reversa de la lista de líneas.
     */
    public static Lista<limpiaLineaTexto> ordenaReversa(Lista<limpiaLineaTexto> lista) {
        return ordena(lista).reversa();
    }

    
}

    
