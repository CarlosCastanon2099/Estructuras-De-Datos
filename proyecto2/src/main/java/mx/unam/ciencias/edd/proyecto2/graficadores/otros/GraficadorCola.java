package mx.unam.ciencias.edd.proyecto2.graficadores.otros;

import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.proyecto2.graficadores.*;

/**
 * Clase para graficar Colas.
 */
public class GraficadorCola<T> extends GraficadorLineal<T> {

    /**
     * Constructor que recibe la cola que será graficada.
     */
    public GraficadorCola(Lista<T> coleccion) {
        super(coleccion);
    }
}

