package mx.unam.ciencias.edd.proyecto2.graficadores.arboles;

import mx.unam.ciencias.edd.*;
import mx.unam.ciencias.edd.proyecto2.graficadores.*;

/**
 * Clase para graficar Árboles AVL
 */
public class GraficadorArbolAVL<T extends Comparable<T>> extends GraficadorArbolBinarioOrdenado<T> {

    private final int medidaContenidoVerticeBal = 15;
    private final int medidaDelDesp = 10;

    /**
     * Constructor del graficador de Árboles AVL
     */
    public GraficadorArbolAVL(ArbolAVL<T> arbol){
        super(arbol);
        medidaBordeSvg = 34;

    }

    /**
     * Metodo para graficar el vértice recibido, en la posición descrita por los
     * argumentos, ademas de darle su balance.
     */
    protected String graficaVertice(VerticeArbolBinario<T> vertice, int centroX, int centroY, int radio){
        String textoVertice = vertice.toString();
        int ultimoEspacio = textoVertice.lastIndexOf(' ');

        String balance = String.format("(%s)", textoVertice.substring(ultimoEspacio + 1));

        int centroTextoX = centroX;
        if(esDerecho(vertice))
            centroTextoX += (int) Math.ceil(balance.length() / 2) * medidaContenidoVerticeBal;
        else if(esIzquierdo(vertice))
            centroTextoX += - (int) Math.ceil(balance.length() / 2) * medidaContenidoVerticeBal;
  
        String resultado = "";
        resultado += GraficadorSVG.graficaTexto(centroTextoX, centroY - radio - medidaDelDesp, medidaContenidoVerticeBal, "black", balance);
        resultado += GraficadorSVG.generaCirculoConTexto(centroX, centroY, radio, "black", "white", medidaContenidoVertice, "black", vertice.get().toString());

        return resultado;
    }
}
