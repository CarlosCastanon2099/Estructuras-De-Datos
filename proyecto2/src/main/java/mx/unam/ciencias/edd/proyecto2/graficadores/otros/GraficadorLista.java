package mx.unam.ciencias.edd.proyecto2.graficadores.otros;

import mx.unam.ciencias.edd.*;
import mx.unam.ciencias.edd.proyecto2.graficadores.*;

/**
 * Clase para graficar Listas.
 */
public class GraficadorLista<T> extends GraficadorLineal<T> {

    /**
     * Constructor que recibe la lista que será graficada.
     */
    public GraficadorLista(Lista<T> coleccion) {
        super(coleccion);
    }


    @Override
    protected String graficaConexion(int origenX, int origenY, int medidaX, int medidaY) {
        int seccion = (medidaX / 4);
        String conexion;

        conexion = GraficadorSVG.generaTriangulo(origenX, origenY, seccion, medidaY, "black");
        conexion += GraficadorSVG.generaLineaSVG(origenX + seccion, (medidaY / 2) + origenY, medidaX - 2 * seccion, 0, "black");

        conexion += GraficadorSVG.generaTriangulo(origenX + medidaX, origenY, -seccion, medidaY, "black");
        return conexion;
        
    }
}
