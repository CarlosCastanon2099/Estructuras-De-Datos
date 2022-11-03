package mx.unam.ciencias.edd.proyecto2.graficadores.otros;

import mx.unam.ciencias.edd.*;
import mx.unam.ciencias.edd.proyecto2.graficadores.*;

/**
 * Clase concreta para graficar la estructura de datos Cola.
 */
public class GraficadorPila<T> extends GraficadorLineal<T> {

    /**
     * Constructor que recibe la lista con los datos que contiene la Pila que
     * queremos graficar.
     * @param coleccion la lista con la información a graficar.
     */
    public GraficadorPila(Lista<T> coleccion) {
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
        return GraficadorSVG.generaElInicioDelArchivo() + GraficadorSVG.generaElInicioDelSVG(anchoVertice + 2 * borde, coleccion.getLongitud() * verticeAltura + 2 * borde) + svg + GraficadorSVG.generaElTerminoDelSVG();

    }
}
