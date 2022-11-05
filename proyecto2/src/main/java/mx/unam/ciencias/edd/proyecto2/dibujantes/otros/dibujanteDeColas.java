package mx.unam.ciencias.edd.proyecto2.dibujantes.otros;

import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.proyecto2.dibujantes.*;

/**
 * Clase para graficar Colas.
 * Cabe aclarar que el nombre y referencias de esta clase son meramente
 * con fines de identificación siguiendo el nombre de las otras clases y no por otra cosa en particular.
 */
public class dibujanteDeColas<T> extends dibujanteDeFiguras<T> {

    /**
     * Constructor que recibe la cola que será graficada.
     */
    public dibujanteDeColas(Lista<T> coleccion) {
        super(coleccion);
    }
}

