package mx.unam.ciencias.edd.proyecto2.graficadores;

/**
 * Clase abstracta encargada de graficar las estructuras correspondientes
 * cuya funcion es la de que la implementen las demas estructuras para
 * tener una grafica adecuada
 */
public abstract class GraficadorEstructura<T> {

    private int gorduraBorde = 20;
    private int ladoAuxiliar = 55;

    protected abstract boolean esVacia();

    /**
     * Método que regresa la gráfica SVG de una estructura de datos.
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
