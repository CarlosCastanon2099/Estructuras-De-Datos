package mx.unam.ciencias.edd.proyecto2.dibujantes.otros;

import mx.unam.ciencias.edd.*;
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

    /**
     * Método sobreescrito para graficar las pilas
     */
    public String graficarEstructura() {
        Lista<T> coleccion = (Lista<T>) iterable;
        int anchoVertice = calculaAnchoVertices();

        String svg = "";
        int alturaSVG = borde + verticeAltura * coleccion.reversa().getElementos();
        

        for(T elemento : iterable){
            alturaSVG -= verticeAltura;
            svg += graficaVertice(elemento, borde, alturaSVG, anchoVertice, verticeAltura);
        }
        return dibujaSVG.generaElInicioDelArchivo() + dibujaSVG.generaElInicioDelSVG(anchoVertice + 2 * borde, coleccion.getLongitud() * verticeAltura + 2 * borde) + svg + dibujaSVG.generaElTerminoDelSVG();

    }
}

