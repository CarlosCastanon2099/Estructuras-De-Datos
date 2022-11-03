package mx.unam.ciencias.edd.proyecto2.graficadores.arboles;

import mx.unam.ciencias.edd.*;
import mx.unam.ciencias.edd.proyecto2.graficadores.*;

/**
 * Clase para graficar Árboles Binarios Ordenados
 */
public class GraficadorArbolBinarioOrdenado<T extends Comparable<T>> extends GraficadorArbol<T> {
    /**
     * Constructor del graficador de árbol binario ordenado.
     */
    public GraficadorArbolBinarioOrdenado(ArbolBinarioOrdenado<T> arbol) {
        super(arbol);

    }
}
