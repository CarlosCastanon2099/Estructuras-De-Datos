package mx.unam.ciencias.edd.proyecto2.dibujantes.otros;

import mx.unam.ciencias.edd.*;
import mx.unam.ciencias.edd.proyecto2.dibujantes.*;

/**
 * Clase para producir el svg de la estructura en cuestion
 */
public class dibujanteDeGraficas<T> extends dibujameLaEstructura<T> {

    protected int medidaBordeSvg;
    protected int medidaBordeVertice;

    protected int medidaContenidoVertice;
    protected int nMaximoEnVertice;

    protected Grafica<T> grafica;

    protected boolean esVacia() {
        return grafica.esVacia();
    }

    /**
     * El constructor del graficador.
     */
    public dibujanteDeGraficas(Grafica<T> grafica){
        medidaBordeVertice= 10;
        medidaContenidoVertice = 20;
        medidaBordeSvg = 10;
        nMaximoEnVertice = 3;
        this.grafica = grafica;

    }

    /**
     * Clase interna privada que utilizamos para mantener las coordenadas en
     * donde se ubica cada vértice, y poder graficar las aristas.
     */
    private class Coord {
        public int x;
        public int y;
        public int posicion;
        public T elemento;

        public Coord(int x, int y, int posicion, T elemento) {
            this.x = x;
            this.y = y;
            this.posicion = posicion;
            this.elemento = elemento;
        }
    }

    /**
     * Genera el String del SVG que representa a la Grafica.
     */
    public String graficarEstructura() {
        int radioVertice = calculaRadioVertices();
        double angulo = (double) 360 / grafica.getElementos();

        double radioCircunferencia = Math.abs((3 * radioVertice) / (2 * Math.sin(Math.toRadians(angulo / 2))));
        int radioGrafica = (int) Math.round(radioCircunferencia + medidaBordeSvg + radioVertice);

        double anguloSVG = 0;

        Lista<VerticeGrafica<T>> vertices = new Lista<>();
        grafica.paraCadaVertice((vertice) -> vertices.agrega(vertice));

        Lista<Coord> verticesGraficados = new Lista<>();

        String aristasSVG = "";
        String verticesSVG = "";
        int posicion = 0;

        for(VerticeGrafica<T> vertice : vertices){
            int componenteX = (int) Math.round(radioCircunferencia * Math.cos(Math.toRadians(anguloSVG)));
            int componenteY = (int) Math.round(radioCircunferencia * Math.sin(Math.toRadians(anguloSVG)));
            componenteX += radioGrafica;
            componenteY += radioGrafica;

            verticesSVG += graficaVertice(vertice.get(), componenteX, componenteY, radioVertice);
            Coord coord = new Coord(componenteX, componenteY, posicion++, vertice.get());

            for(VerticeGrafica<T> vecino : vertice.vecinos()){
                Coord coordVecino = getCoordenada(vecino, verticesGraficados);
                if(coordVecino != null){
                    aristasSVG += graficaConexion(coord, coordVecino, radioGrafica);

                }      
            }

            verticesGraficados.agrega(coord);
            anguloSVG += angulo;
        }

        int medida = radioGrafica * 2;
        return dibujaSVG.generaElInicioDelArchivo() + dibujaSVG.generaElInicioDelSVG(medida, medida) + aristasSVG + verticesSVG + dibujaSVG.generaElTerminoDelSVG();
    }

    

    /**
     * Método que nos sirve para calcular la medida del ancho de los vértices.
     * Esto con la finalidad de que todos los tengan la misma medida. La medida
     * se basa en el máximo de dígitos que un vértice puede tener.
     */
    protected int calculaRadioVertices() {
        return ((nMaximoEnVertice * medidaContenidoVertice) / 2) + medidaBordeVertice;
    }

    /**
     * Método que genera el String que representa un vertice con el
     * elemento recibido. Utiliza las medidas recibidas.
     */
    protected String graficaVertice(T elemento, int origenX, int origenY, int radio) {
        return dibujaSVG.generaCirculoConTexto(origenX, origenY, radio, "black", "white", medidaContenidoVertice, "black", elemento.toString());
    }

    /**
     * Método privado que nos dice si el vértice ya ha sido graficado
     * a lo que nos da sus coordenadas, en otro caso arroja null.
     */
    private Coord getCoordenada(VerticeGrafica<T> vertice, Lista<Coord> coordenadas) {
        for(Coord coord : coordenadas){
            if (coord.elemento.equals(vertice.get())){
                return coord;
            }
        }
            
        return null;

    }

    /**
     * Genera el String con el SVG que representa la unión entre dos
     * vértices de la estructura de datos.
     * Si los vértices se encuentran contiguos, solo graficamos una línea recta
     * entre ellos. Si no, graficamos una curva, para que se vean más
     * claramente las aristas.
     */
    protected String graficaConexion(Coord vertice1, Coord vertice2, int radioGrafica) {
        if (Math.abs(vertice1.posicion - vertice2.posicion) == 1){
            return dibujaSVG.generaLineaSVG(vertice1.x, vertice1.y, vertice2.x - vertice1.x, vertice2.y - vertice1.y, "black");
        }
        return dibujaSVG.generaLineaSVG(vertice1.x, vertice1.y, vertice2.x - vertice1.x, vertice2.y - vertice1.y, "red");                    
    }

    
}
