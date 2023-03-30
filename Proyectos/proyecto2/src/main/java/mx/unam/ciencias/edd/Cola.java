package mx.unam.ciencias.edd;

/**
 * Clase para colas genéricas.
 */
public class Cola<T> extends MeteSaca<T> {

    /**
     * Regresa una representación en cadena de la cola.
     * @return una representación en cadena de la cola.
     */
    @Override public String toString() {
        // Aquí va su código.
        if(esVacia()){
            return "";
        }
		
        String colaDeLasTortillas = "";
        Nodo tortilla = cabeza;

        while(tortilla != null){
            colaDeLasTortillas += tortilla.elemento + "," ;
            tortilla = tortilla.siguiente;
        }

        return colaDeLasTortillas;
    }

    /**
     * Agrega un elemento al final de la cola.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    @Override public void mete(T elemento) {
        // Aquí va su código.
        if(elemento == null){
            throw new IllegalArgumentException();
        }

        Nodo d = new Nodo(elemento);
        if(cabeza == null){
            cabeza = rabo = d;
        }else{           
            rabo.siguiente = d;
            rabo = rabo.siguiente;
            }
    }
}
