package mx.unam.ciencias.edd.proyecto2.graficadores;

/**
 * Interfaz que deben implementar las clases que se encargan de graficar las
 * estructuras de datos. Lo ideal es que cada estructura de datos tenga una
 * clase correspondiente que implemente esta interfaz para generar la
 * representación adecuada de la estructura.
 */
public abstract class GraficadorEstructura<T> {

    private int gorduraBorde = 20;
    private int ladoAuxiliar = 55;

    protected abstract boolean esVacia();

    /**
     * Método que regresa la gráfica SVG de una estructura de datos.
     * @return la cadena de texto con el SVG de la estructura.
     */
    protected abstract String graficarEstructura();
    
    public String graficar(){
        if(esVacia()){
            return graficarVacio();
        }   
        return graficarEstructura();
    }

    protected String graficarVacio(){
        return GraficadorSVG.generaElInicioDelArchivo() + GraficadorSVG.generaElInicioDelSVG(ladoAuxiliar + (2 * gorduraBorde), ladoAuxiliar + (2 * gorduraBorde)) + GraficadorSVG.generaCirculo(gorduraBorde + ladoAuxiliar / 2, gorduraBorde + ladoAuxiliar / 2, ladoAuxiliar / 2, "black", "white") + GraficadorSVG.generaLineaSVG(gorduraBorde, gorduraBorde + ladoAuxiliar, ladoAuxiliar, -ladoAuxiliar, "black") + GraficadorSVG.generaElTerminoDelSVG();
    }

    

    
}
