package mx.unam.ciencias.edd;

import java.util.Iterator;

/**
 * <p>Clase para árboles binarios completos.</p>
 *
 * <p>Un árbol binario completo agrega y elimina elementos de tal forma que el
 * árbol siempre es lo más cercano posible a estar lleno.</p>
 */
public class ArbolBinarioCompleto<T> extends ArbolBinario<T> {

    /* Clase interna privada para iteradores. */
    private class Iterador implements Iterator<T> {

        /* Cola para recorrer los vértices en BFS. */
        private Cola<Vertice> cola;

        /* Inicializa al iterador. */
        private Iterador() {
            // Aquí va su código.
            cola = new Cola<Vertice>();

            if(raiz != null){
                cola.mete(raiz);
            }
		
        }

        /* Nos dice si hay un elemento siguiente. */
        @Override public boolean hasNext() {
            // Aquí va su código.
            if(cola.esVacia()){
                return false;
            }else{
                return true;
            }
        }

        /* Regresa el siguiente elemento en orden BFS. */
        @Override public T next() {
            // Aquí va su código.
            Vertice prov = cola.saca(); 

            if(prov.izquierdo != null){
                cola.mete(prov.izquierdo);   
            }

            if(prov.derecho != null){
                cola.mete(prov.derecho);    
            }

            return prov.elemento;
        }
    }

    /**
     * Constructor sin parámetros. Para no perder el constructor sin parámetros
     * de {@link ArbolBinario}.
     */
    public ArbolBinarioCompleto() { super(); }

    /**
     * Construye un árbol binario completo a partir de una colección. El árbol
     * binario completo tiene los mismos elementos que la colección recibida.
     * @param coleccion la colección a partir de la cual creamos el árbol
     *        binario completo.
     */
    public ArbolBinarioCompleto(Coleccion<T> coleccion) {
        super(coleccion);
    }

    /**
     * Agrega un elemento al árbol binario completo. El nuevo elemento se coloca
     * a la derecha del último nivel, o a la izquierda de un nuevo nivel.
     * @param elemento el elemento a agregar al árbol.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    @Override public void agrega(T elemento) {
        // Aquí va su código.
        if(elemento == null){
            throw new IllegalArgumentException();
        }

    	Vertice verticeD = nuevoVertice(elemento);
	    elementos++;

	    if(raiz == null){
		    raiz = verticeD;
	    }else{
		    Vertice verticeFinal = getPrimerVerticeConHoyo(raiz);
		    if((elementos) % 2 == 0 ){
			    verticeFinal.izquierdo = verticeD;
			    verticeD.padre = verticeFinal;
		    }else{
			    verticeFinal.derecho = verticeD;
			    verticeD.padre = verticeFinal;
		    }
	    }
    }

    private Vertice getPrimerVerticeConHoyo(Vertice v) {
	    Cola<Vertice> cola = new Cola<Vertice>();
	    cola.mete(v);

	    while(!cola.esVacia()){
		    Vertice temp;
		    temp = cola.saca();

		    if(temp.izquierdo == null){
			    return temp;
		    }else{
			    cola.mete(temp.izquierdo);
		    }

		    if(temp.derecho == null){
			    return temp;
		    }else{ 
			    cola.mete(temp.derecho);
		    }
	    }

	    return null;
    }

    /**
     * Elimina un elemento del árbol. El elemento a eliminar cambia lugares con
     * el último elemento del árbol al recorrerlo por BFS, y entonces es
     * eliminado.
     * @param elemento el elemento a eliminar.
     */
    @Override public void elimina(T elemento) {
        // Aquí va su código.
        Vertice vertice = vertice(busca(elemento));

        if (vertice == null)
            return;

        elementos--;

        if(elementos == 0){
            raiz = null;
            return;
        }

        Vertice ultimoVertice = vertice(ultimoVerticeAgregado());
        vertice.elemento = ultimoVertice.elemento;

        if(ultimoVertice.padre.izquierdo == ultimoVertice){
            ultimoVertice.padre.izquierdo = null;
        }else{
            ultimoVertice.padre.derecho = null;
        }
        
            
        
    }

    private VerticeArbolBinario<T> ultimoVerticeAgregado() {
        Cola<Vertice> cola = new Cola<Vertice>();
        cola.mete(raiz);

        Vertice ultimoVert = raiz;
        Vertice verticeAct;

        while(!cola.esVacia()){
            verticeAct = cola.saca();
            ultimoVert = verticeAct;

            if(verticeAct.izquierdo != null){
                cola.mete(verticeAct.izquierdo);
            }
                
            if(verticeAct.derecho != null){
                cola.mete(verticeAct.derecho);
            }  
        }

        return ultimoVert;
    }

    /**
     * Regresa la altura del árbol. La altura de un árbol binario completo
     * siempre es ⌊log<sub>2</sub><em>n</em>⌋.
     * @return la altura del árbol.
     */
    @Override public int altura() {
        // Aquí va su código.
        if(raiz == null){
            return -1;
        }

        return (int) (Math.floor(Math.log(elementos)/Math.log(2)));
		
    }

    /**
     * Realiza un recorrido BFS en el árbol, ejecutando la acción recibida en
     * cada elemento del árbol.
     * @param accion la acción a realizar en cada elemento del árbol.
     */
    public void bfs(AccionVerticeArbolBinario<T> accion) {
        // Aquí va su código.
        if(raiz == null){
            return;
        }
		    
	    Cola <Vertice> cola = new Cola<Vertice>();
	    cola.mete(raiz);

	    while (!cola.esVacia()){
		    Vertice aux;
		   
		    aux = cola.saca();
		    accion.actua(aux);

		    if(aux.izquierdo != null){
                cola.mete(aux.izquierdo);
            }
			    
		    if(aux.derecho != null){
                cola.mete(aux.derecho);
            }

	    }

    }

    /**
     * Regresa un iterador para iterar el árbol. El árbol se itera en orden BFS.
     * @return un iterador para iterar el árbol.
     */
    @Override public Iterator<T> iterator() {
        return new Iterador();
    }
}
