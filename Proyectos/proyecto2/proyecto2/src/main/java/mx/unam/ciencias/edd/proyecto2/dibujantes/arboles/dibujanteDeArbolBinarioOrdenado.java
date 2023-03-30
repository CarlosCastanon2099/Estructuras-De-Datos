package mx.unam.ciencias.edd.proyecto2.dibujantes.arboles;

import mx.unam.ciencias.edd.*;
import mx.unam.ciencias.edd.proyecto2.dibujantes.*;

/**
 * Clase para graficar Árboles Binarios Ordenados
 */
public class dibujanteDeArbolBinarioOrdenado<T extends Comparable<T>> extends dibujanteDeArbol<T> {
    /**
     * Constructor del graficador de árbol binario ordenado.
     */
    public dibujanteDeArbolBinarioOrdenado(ArbolBinarioOrdenado<T> arbol) {
        super(arbol);

    }
}
