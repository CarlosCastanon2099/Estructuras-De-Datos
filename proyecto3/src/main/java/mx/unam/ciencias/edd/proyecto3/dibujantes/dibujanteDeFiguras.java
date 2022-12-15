package mx.unam.ciencias.edd.proyecto3.dibujantes;

import java.util.Iterator;

/**
 * Clase abstracta de la que heredan las clases concretas de dibujantes que
 * corresponden a estructuras de datos lineales.
 */
public abstract class dibujanteDeFiguras<T> extends dibujameLaEstructura<T> {

    protected int verticeAltura;
    protected int medidaContenidoVertice;

    protected int nMaximoEnVertice;
    protected int anchoDeLaConexion;

    protected int borde;
    protected Iterable<T> iterable;

    protected boolean esVacia(){
        return !iterable.iterator().hasNext();
    }

    /**
     * Constructor de la clase abstracta
     */
    public dibujanteDeFiguras(Iterable<T> iterable){
        verticeAltura = 40;
        anchoDeLaConexion = 50;
        medidaContenidoVertice = 20;
        borde = 10;
        nMaximoEnVertice = 3;
        this.iterable = iterable;
        
    }

    /**
     * Metodo para obténer el String de la gráfica SVG 
     */
    public String graficarEstructura(){
        int anchoVertice = calculaAnchoVertices();
        String svg = "";
        Iterator<T> iterador = iterable.iterator();
        int anchoSVG = anchoVertice + borde;

        if(iterador.hasNext()){
            svg += graficaVertice(iterador.next(), borde, borde, anchoVertice, verticeAltura);
        }

        while(iterador.hasNext()){
            svg += graficaConexion(anchoSVG, borde + (verticeAltura / 4), anchoDeLaConexion, verticeAltura / 2);
            anchoSVG += anchoDeLaConexion;

            svg += graficaVertice(iterador.next(), anchoSVG, borde, anchoVertice, verticeAltura);
            anchoSVG += anchoVertice;

        }

        return dibujaSVG.generaElInicioDelArchivo() + dibujaSVG.generaElInicioDelSVG(anchoSVG + borde, verticeAltura + 2 * borde) + svg + dibujaSVG.generaElTerminoDelSVG();
    }

    /**
     * Método para calcular la medida del ancho de los vértices.
     */
    protected int calculaAnchoVertices(){
        return (nMaximoEnVertice * medidaContenidoVertice) + 2 * borde;
    }

    /**
     * Método para generar el String que representa un vértice con el
     * elemento recibido. 
     */
    protected String graficaVertice(T elemento, int origenX, int origenY, int medidaX, int medidaY){
        return dibujaSVG.generaRectanguloConTexto(origenX, origenY, medidaX, medidaY, "black", "white", medidaContenidoVertice, "black", elemento.toString());
    }

    /**
     * Método para generar el String con el SVG que representa la unión entre dos
     * vértices de la estructura en cuestion.
     */
    protected String graficaConexion(int origenX, int origenY, int medidaX, int medidaY){
        int seccion = medidaX / 3;
        String conexion;

        conexion = dibujaSVG.generaLineaSVG(origenX, (medidaY / 2) + origenY, medidaX - seccion, 0, "black");
        conexion += dibujaSVG.generaTriangulo(origenX + medidaX, origenY, -seccion, medidaY, "black");

        return conexion;

    }

    
}
