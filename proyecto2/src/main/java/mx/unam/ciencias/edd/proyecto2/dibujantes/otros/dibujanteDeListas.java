package mx.unam.ciencias.edd.proyecto2.dibujantes.otros;

import mx.unam.ciencias.edd.*;
import mx.unam.ciencias.edd.proyecto2.dibujantes.*;

/**
 * Clase para graficar Listas.
 */
public class dibujanteDeListas<T> extends dibujanteDeFiguras<T> {

    /**
     * Constructor que recibe la lista que será graficada.
     */
    public dibujanteDeListas(Lista<T> coleccion) {
        super(coleccion);
    }


    @Override
    protected String graficaConexion(int origenX, int origenY, int medidaX, int medidaY) {
        int seccion = (medidaX / 4);
        String conexion;

        conexion = dibujaSVG.generaTriangulo(origenX, origenY, seccion, medidaY, "black");
        conexion += dibujaSVG.generaLineaSVG(origenX + seccion, (medidaY / 2) + origenY, medidaX - 2 * seccion, 0, "black");

        conexion += dibujaSVG.generaTriangulo(origenX + medidaX, origenY, -seccion, medidaY, "black");
        return conexion;
        
    }
}
