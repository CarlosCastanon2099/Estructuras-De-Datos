package mx.unam.ciencias.edd.proyecto2.graficadores.arboles;

import mx.unam.ciencias.edd.*;
import mx.unam.ciencias.edd.proyecto2.graficadores.*;

/**
 * Clase para graficar Árboles Rojinegros
 */
public class GraficadorArbolRojinegro<T extends Comparable<T>> extends GraficadorArbolBinarioOrdenado<T> {

    /**
     * Constructor del graficador de Árboles Rojinegros
     */
    public GraficadorArbolRojinegro(ArbolRojinegro<T> arbol) {
        super(arbol);

    }

    /**
     * Metodo para graficar el vértice recibido, en la posición descrita por los
     * argumentos, ademas de darle su color.
     */
    protected String graficaVertice(VerticeArbolBinario<T> vertice, int centroX, int centroY, int radio) {
        Color color = ((ArbolRojinegro<T>) arbol).getColor(vertice);
        
        return GraficadorSVG.generaCirculoConTexto(centroX, centroY, radio, "black", color == Color.ROJO ? "red" : "black", medidaContenidoVertice,"white", vertice.get().toString());
        
    }
}
