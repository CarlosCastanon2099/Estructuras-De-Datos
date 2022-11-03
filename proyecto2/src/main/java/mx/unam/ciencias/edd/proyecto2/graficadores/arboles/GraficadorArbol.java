package mx.unam.ciencias.edd.proyecto2.graficadores.arboles;

import mx.unam.ciencias.edd.*;
import mx.unam.ciencias.edd.proyecto2.graficadores.*;
/**
 * Clase abstracta para graficar la estructura de datos ArbolBinario. 
 */
public abstract class GraficadorArbol<T> extends GraficadorEstructura<T> {

    protected int medidaBordeSvg;
    protected int medidaContenidoVertice;

    protected int medidaBordeVertice;
    protected int nMaximoEnVertice;

    protected int desplazaX;
    protected int desplazaY;

    ArbolBinario<T> arbol;

    protected boolean esVacia() {
        return arbol.esVacia();
    }

    /**
     * Clase interna privada para saber las coordenadas de un vértice
     */
    private class Coord{
        public int x;
        public int y;

        public Coord(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    /**
     * El constructor del graficador. 
     */
    public GraficadorArbol(ArbolBinario<T> arbol){
        medidaContenidoVertice = 20;
        medidaBordeSvg = 10;
        medidaBordeVertice = 10;
        desplazaX = 30;
        desplazaY = 50;
        nMaximoEnVertice = 3;
        this.arbol = arbol;

    }

    /**
     * Metodo para obténer el String de la gráfica SVG de la estructura en cuestion
     */
    public String graficarEstructura(){
        Pila<VerticeArbolBinario<T>> pilaVertices = new Pila<>();
        Pila<Integer> pilaNivel = new Pila<>();
        Pila<Coord> pilaCoords = new Pila<>();

        VerticeArbolBinario<T> vertice;
        if(arbol.esVacia()){
            vertice = null;
        }else{
            vertice = arbol.raiz();
        }
            
        meteRamaIzquierda(pilaVertices, vertice, pilaNivel, 0);

        String vertices = "";
        String aristas = "";

        int radio = calculaRadioVertices();
        int alturaSVG = arbol.altura() * (desplazaY + (radio * 2)) + (radio * 2) + (medidaBordeSvg * 2);
        int anchoSVG = medidaBordeSvg;

        int cambioAltura = radio * 2 + desplazaY;
        int cambioAncho = radio * 2 + desplazaX;

        while(!pilaVertices.esVacia()){
            vertice = pilaVertices.saca();

            int nivel = pilaNivel.saca();
            int coordX = anchoSVG + radio;
            int coordY = medidaBordeSvg + nivel * cambioAltura + radio;

            vertices += graficaVertice(vertice, coordX, coordY, radio);
            anchoSVG += cambioAncho;

            if(vertice.hayDerecho()){
                meteRamaIzquierda(pilaVertices, vertice.derecho(), pilaNivel, nivel + 1);
            }

            aristas += conectaVertices(pilaCoords, vertice, coordX, coordY);

        }

        return GraficadorSVG.generaElInicioDelArchivo() + GraficadorSVG.generaElInicioDelSVG(anchoSVG - desplazaX + medidaBordeSvg, alturaSVG) + aristas + vertices + GraficadorSVG.generaElTerminoDelSVG();
    }

    /**
     * Metodo para calcular la medida del radio de cada vértice, a partir del elemento 
     * cuya representación en cadena sea más larga.
     */
    protected int calculaRadioVertices(){
        int medidaTexto = nMaximoEnVertice * medidaContenidoVertice;
        int radio = (int) Math.ceil(medidaTexto / 2);
        
        return radio + medidaBordeVertice;

    }

    /**
     * Metodo para generar el String que representa en SVG el vértice recibido,
     */
    protected String graficaVertice(VerticeArbolBinario<T> vertice, int centroX, int centroY, int radio){
        return GraficadorSVG.generaCirculoConTexto(centroX, centroY, radio, "black", "white", medidaContenidoVertice, "black", vertice.get().toString());
    }

    /**
     * Metodo para guardar un vértice en una pila y toda su rama izquierda.
     */
    private void meteRamaIzquierda(Pila<VerticeArbolBinario<T>> pilaVertices, VerticeArbolBinario<T> vertice, Pila<Integer> pilaNivel, int nivel){
        if(vertice == null){
            return;
        }
            
        VerticeArbolBinario<T> verticeAux = vertice;
        int nivelAux = nivel;

        pilaVertices.mete(verticeAux);
        pilaNivel.mete(nivelAux);

        while(verticeAux.hayIzquierdo()){
            verticeAux = verticeAux.izquierdo();
            pilaVertices.mete(verticeAux);
            pilaNivel.mete(++nivelAux);
        }

    }

    /**
     * Método para conectar un vértice con su(s) padre o hijo(s) según sea el caso.
     * Conexiones las cuales son de izquierda a derecha 
     */
    private String conectaVertices(Pila<Coord> pila, VerticeArbolBinario<T> vertice, int coordX, int coordY) { 
        String aristas = "";

        if(vertice.hayIzquierdo()){
            Coord hijoI = pila.saca();
            aristas += graficaConexion(hijoI.x, hijoI.y, coordX, coordY);
        }

        if(esDerecho(vertice)){
            Coord hijoI = pila.saca();
            aristas += graficaConexion(hijoI.x, hijoI.y, coordX, coordY);
        }

        if(vertice.hayDerecho()){
            pila.mete(new Coord(coordX, coordY));
        }
            
        if(esIzquierdo(vertice)){
            pila.mete(new Coord(coordX, coordY));
        }

        return aristas;

    }

    
    /**
     * Metodo Auxiliar para generar el String con el SVG que representa la unión entre dos
     * vértices del árbol.
     */
    protected String graficaConexion(int centroX1, int centroY1, int centroX2, int centroY2){
        return GraficadorSVG.generaLineaSVG(centroX1, centroY1, centroX2 - centroX1, centroY2 - centroY1, "black");
    }



    // Metodos para AVL

    /**
     * Metodo para saber si un vértice es derecho de otro.
     */
    protected final boolean esDerecho(VerticeArbolBinario<T> vertice) {
        if(vertice == arbol.raiz()){
            return false;
        }

        if(!vertice.padre().hayDerecho()){
            return false;
        }
            
        return vertice.padre().derecho() == vertice;

    }
    
    /**
     * Metodo para saber si un vértice es izquierdo de otro.
     */
    protected final boolean esIzquierdo(VerticeArbolBinario<T> vertice) {
        if(vertice == arbol.raiz()){
            return false;
        }
            
        if(!vertice.padre().hayIzquierdo()){
            return false;
        }
            
        return vertice.padre().izquierdo() == vertice;

    }

    

    
}
