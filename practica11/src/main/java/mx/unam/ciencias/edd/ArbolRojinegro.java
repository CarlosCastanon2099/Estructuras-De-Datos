package mx.unam.ciencias.edd;

/**
 * Clase para árboles rojinegros. Un árbol rojinegro cumple las siguientes
 * propiedades:
 *
 * <ol>
 *  <li>Todos los vértices son NEGROS o ROJOS.</li>
 *  <li>La raíz es NEGRA.</li>
 *  <li>Todas las hojas (<code>null</code>) son NEGRAS (al igual que la raíz).</li>
 *  <li>Un vértice ROJO siempre tiene dos hijos NEGROS.</li>
 *  <li>Todo camino de un vértice a alguna de sus hojas descendientes tiene el
 *      mismo número de vértices NEGROS.</li>
 * </ol>
 *
 * Los árboles rojinegros se autobalancean.
 */
public class ArbolRojinegro<T extends Comparable<T>>
    extends ArbolBinarioOrdenado<T> {

    /**
     * Clase interna protegida para vértices.
     */
    protected class VerticeRojinegro extends Vertice {

        /** El color del vértice. */
        public Color color;

        /**
         * Constructor único que recibe un elemento.
         * @param elemento el elemento del vértice.
         */
        public VerticeRojinegro(T elemento) {
            // Aquí va su código.
            super(elemento);
            color = Color.NINGUNO;
        }

        /**
         * Regresa una representación en cadena del vértice rojinegro.
         * @return una representación en cadena del vértice rojinegro.
         */
        @Override public String toString() {
            // Aquí va su código.
            return String.format("%s{%s}", colorToString(), elemento.toString());
        }

        private String colorToString() {
            if(color == Color.ROJO){
                return "R";
            }else{
                return "N";
            }
        }
            
        /**
         * Compara el vértice con otro objeto. La comparación es
         * <em>recursiva</em>.
         * @param objeto el objeto con el cual se comparará el vértice.
         * @return <code>true</code> si el objeto es instancia de la clase
         *         {@link VerticeRojinegro}, su elemento es igual al elemento de
         *         éste vértice, los descendientes de ambos son recursivamente
         *         iguales, y los colores son iguales; <code>false</code> en
         *         otro caso.
         */
        @Override public boolean equals(Object objeto) {
            if (objeto == null || getClass() != objeto.getClass())
                return false;
            @SuppressWarnings("unchecked")
                VerticeRojinegro vertice = (VerticeRojinegro)objeto;
            // Aquí va su código.
            return color == vertice.color && super.equals(objeto);
        }
    }

    /**
     * Constructor sin parámetros. Para no perder el constructor sin parámetros
     * de {@link ArbolBinarioOrdenado}.
     */
    public ArbolRojinegro() { super(); }

    /**
     * Construye un árbol rojinegro a partir de una colección. El árbol
     * rojinegro tiene los mismos elementos que la colección recibida.
     * @param coleccion la colección a partir de la cual creamos el árbol
     *        rojinegro.
     */
    public ArbolRojinegro(Coleccion<T> coleccion) {
        super(coleccion);
    }

    /**
     * Construye un nuevo vértice, usando una instancia de {@link
     * VerticeRojinegro}.
     * @param elemento el elemento dentro del vértice.
     * @return un nuevo vértice rojinegro con el elemento recibido dentro del mismo.
     */
    @Override protected Vertice nuevoVertice(T elemento) {
        // Aquí va su código.
        return new VerticeRojinegro(elemento);
    }

    /**
     * Regresa el color del vértice rojinegro.
     * @param vertice el vértice del que queremos el color.
     * @return el color del vértice rojinegro.
     * @throws ClassCastException si el vértice no es instancia de {@link
     *         VerticeRojinegro}.
     */
    public Color getColor(VerticeArbolBinario<T> vertice) {
        // Aquí va su código.
        return verticeRojinegro(vertice).color;
    }

    private VerticeRojinegro verticeRojinegro(VerticeArbolBinario<T> vertice) {
        return (VerticeRojinegro)vertice;
    }

    /**
     * Agrega un nuevo elemento al árbol. El método invoca al método {@link
     * ArbolBinarioOrdenado#agrega}, y después balancea el árbol recoloreando
     * vértices y girando el árbol como sea necesario.
     * @param elemento el elemento a agregar.
     */
    @Override public void agrega(T elemento) {
        // Aquí va su código.
        super.agrega(elemento);
        VerticeRojinegro vertice = verticeRojinegro(ultimoAgregado);
        vertice.color = Color.ROJO;
        rebalancearAgrega(vertice);
    }

    private Color color(VerticeRojinegro vertice) {
        if(vertice != null){
            return vertice.color;
        }else{
            return Color.NEGRO;
        }
    }

    private boolean esIzquierdo(Vertice vertice) {
        return vertice.padre.izquierdo == vertice;
    }

    private void rebalancearAgrega(VerticeRojinegro vertice) {
        if(vertice.padre == null){
            vertice.color = Color.NEGRO;
            return;
        }

        VerticeRojinegro padre = verticeRojinegro(vertice.padre);

        if(color(padre) == Color.NEGRO){
            return;
        }
            
        VerticeRojinegro abuelo = verticeRojinegro(padre.padre);
        VerticeRojinegro tio = verticeRojinegro(esIzquierdo(padre) ? abuelo.derecho : abuelo.izquierdo);

        if(color(tio) == Color.ROJO){
            padre.color = tio.color = Color.NEGRO;
            abuelo.color = Color.ROJO;
            rebalancearAgrega(abuelo);
            return;
        }

        if(esIzquierdo(vertice) && !esIzquierdo(padre)){
            super.giraDerecha(padre);
            VerticeRojinegro temp = vertice;
            vertice = padre;
            padre = temp;
        }else if(esIzquierdo(padre) && !esIzquierdo(vertice)){
            super.giraIzquierda(padre);
            VerticeRojinegro temp = vertice;
            vertice = padre;
            padre = temp;
        }

        padre.color = Color.NEGRO;
        abuelo.color = Color.ROJO;

        if(esIzquierdo(vertice)){
            super.giraDerecha(abuelo);
        }else{
            super.giraIzquierda(abuelo);
        }

    }

    /**
     * Elimina un elemento del árbol. El método elimina el vértice que contiene
     * el elemento, y recolorea y gira el árbol como sea necesario para
     * rebalancearlo.
     * @param elemento el elemento a eliminar del árbol.
     */
    @Override public void elimina(T elemento) {
        // Aquí va su código.
        VerticeRojinegro vertice = verticeRojinegro(busca(elemento));

        if (vertice == null){
            return;
        }
            

        elementos--;
        if((vertice.izquierdo != null) && (vertice.derecho != null)){
            vertice = verticeRojinegro(intercambiaEliminable(vertice));
        }
            
        VerticeRojinegro hijo;
        VerticeRojinegro fantasmon = null;

        if(vertice.izquierdo == null && vertice.derecho == null){
            fantasmon = verticeRojinegro(nuevoVertice(null));
            fantasmon.color = Color.NEGRO;
            fantasmon.padre = vertice;
            vertice.izquierdo = fantasmon;
            hijo = fantasmon;
        }else{
            hijo = verticeRojinegro(vertice.izquierdo != null ? vertice.izquierdo : vertice.derecho);
        }
        
        eliminaVertice(vertice);

        if (color(hijo) == Color.ROJO || color(vertice) == Color.ROJO){
            hijo.color = Color.NEGRO;
        }else{
            rebalancearElimina(hijo);
        }
        
        if (fantasmon != null){
            eliminaVertice(fantasmon);
        }
            
    }

    private void rebalancearElimina(VerticeRojinegro vertice) {
        if (vertice.padre == null){
            return;
        }

        VerticeRojinegro padre = verticeRojinegro(vertice.padre);
        VerticeRojinegro hermano = verticeRojinegro(esIzquierdo(vertice) ? padre.derecho : padre.izquierdo);

        if(color(hermano) == Color.ROJO){
            padre.color = Color.ROJO;
            hermano.color = Color.NEGRO;

            if(esIzquierdo(vertice)){
                super.giraIzquierda(padre);
            }else{
                super.giraDerecha(padre);
            }
            
            padre = verticeRojinegro(vertice.padre);
            hermano = verticeRojinegro(esIzquierdo(vertice) ? padre.derecho : padre.izquierdo);
        }

        VerticeRojinegro hermanoIzquierdo = verticeRojinegro(hermano.izquierdo);
        VerticeRojinegro hermanoDerecho = verticeRojinegro(hermano.derecho);

        if(color(padre) == Color.NEGRO && color(hermano) == Color.NEGRO && color(hermanoIzquierdo) == Color.NEGRO && color(hermanoDerecho) == Color.NEGRO){
            hermano.color = Color.ROJO;
            rebalancearElimina(padre);
            return;
        }

        if(color(hermano) == Color.NEGRO && color(hermanoIzquierdo) == Color.NEGRO && color(hermanoDerecho) == Color.NEGRO && color(padre) == Color.ROJO){
            hermano.color = Color.ROJO;
            padre.color = Color.NEGRO;
            return;
        }

        if((esIzquierdo(vertice) && color(hermanoIzquierdo) == Color.ROJO && color(hermanoDerecho) == Color.NEGRO) || (!esIzquierdo(vertice) && color(hermanoIzquierdo) == Color.NEGRO && color(hermanoDerecho) == Color.ROJO)){
            hermano.color = Color.ROJO;

            if(color(hermanoIzquierdo) == Color.ROJO){
                hermanoIzquierdo.color = Color.NEGRO;
            }

            if(color(hermanoDerecho) == Color.ROJO){
                hermanoDerecho.color = Color.NEGRO;
            }
                
            if(esIzquierdo(vertice)){
                super.giraDerecha(hermano);
            }else{
                super.giraIzquierda(hermano);
            }
            
            hermano = verticeRojinegro(esIzquierdo(vertice) ? padre.derecho : padre.izquierdo);

            hermanoIzquierdo = verticeRojinegro(hermano.izquierdo);
            hermanoDerecho = verticeRojinegro(hermano.derecho);
        }

        hermano.color = padre.color;
        padre.color = Color.NEGRO;

        if (esIzquierdo(vertice)) {
            hermanoDerecho.color = Color.NEGRO;
            super.giraIzquierda(padre);
        } else {
            hermanoIzquierdo.color = Color.NEGRO;
            super.giraDerecha(padre);
        }
    }

    /**
     * Lanza la excepción {@link UnsupportedOperationException}: los árboles
     * rojinegros no pueden ser girados a la izquierda por los usuarios de la
     * clase, porque se desbalancean.
     * @param vertice el vértice sobre el que se quiere girar.
     * @throws UnsupportedOperationException siempre.
     */
    @Override public void giraIzquierda(VerticeArbolBinario<T> vertice) {
        throw new UnsupportedOperationException("Los árboles rojinegros no " +
                                                "pueden girar a la izquierda " +
                                                "por el usuario.");
    }

    /**
     * Lanza la excepción {@link UnsupportedOperationException}: los árboles
     * rojinegros no pueden ser girados a la derecha por los usuarios de la
     * clase, porque se desbalancean.
     * @param vertice el vértice sobre el que se quiere girar.
     * @throws UnsupportedOperationException siempre.
     */
    @Override public void giraDerecha(VerticeArbolBinario<T> vertice) {
        throw new UnsupportedOperationException("Los árboles rojinegros no " +
                                                "pueden girar a la derecha " +
                                                "por el usuario.");
    }
}
