package mx.unam.ciencias.edd.proyecto2.dibujantes.otros;

import mx.unam.ciencias.edd.*;
import mx.unam.ciencias.edd.proyecto2.dibujantes.*;

/**
 * Clase  para graficar  pilas.
 */
public class dibujanteDePilas<T> extends dibujanteDeFiguras<T> {

    /**
     * Constructor que recibe la pila que será graficada.
     */
    public dibujanteDePilas(Lista<T> coleccion) {
        super(coleccion);
    }

    /**
     * Método sobreescrito para graficar las pilas
     */
    public String graficarEstructura() {
        Lista<T> coleccion = (Lista<T>) iterable;
        int anchoVertice = calculaAnchoVertices();

        String svg = "";
        int alturaSVG = borde + verticeAltura * coleccion.getElementos();

        for(T elemento : iterable){
            alturaSVG -= verticeAltura;
            svg += graficaVertice(elemento, borde, alturaSVG, anchoVertice, verticeAltura);
        }
        return dibujaSVG.generaElInicioDelArchivo() + dibujaSVG.generaElInicioDelSVG(anchoVertice + 2 * borde, coleccion.getLongitud() * verticeAltura + 2 * borde) + svg + dibujaSVG.generaElTerminoDelSVG();

    }
}
