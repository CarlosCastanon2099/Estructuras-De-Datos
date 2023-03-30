package mx.unam.ciencias.edd;

/**
 * Clase para pilas genéricas.
 */
public class Pila<T> extends MeteSaca<T> {

    /**
     * Regresa una representación en cadena de la pila.
     * @return una representación en cadena de la pila.
     */
    @Override public String toString() {
        // Aquí va su código.
        if(esVacia()){
            return "";
        }

        String libros = "";
        Nodo libro = cabeza;

        while(libro != null){
            libros += libro.elemento + "\n";
            libro = libro.siguiente;
        }

        return libros;
    }

    /**
     * Agrega un elemento al tope de la pila.
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
            d.siguiente = cabeza;
            cabeza = d;
            }
            

    }
}
