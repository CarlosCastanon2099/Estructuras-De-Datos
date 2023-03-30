package mx.unam.ciencias.edd;

import java.util.Comparator;

/**
 * Clase para ordenar y buscar arreglos genéricos.
 */
public class Arreglos {

    /* Constructor privado para evitar instanciación. */
    private Arreglos() {}

    /**
     * Ordena el arreglo recibido usando QickSort.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo el arreglo a ordenar.
     * @param comparador el comparador para ordenar el arreglo.
     */
    public static <T> void
    quickSort(T[] arreglo, Comparator<T> comparador) {
        // Aquí va su código.
        quickSort(arreglo, comparador, 0, arreglo.length-1);

    }
    private static <T> void
    quickSort(T[] arreglo, Comparator<T> comparador, int a, int b) {
        if(b <= a){
            return;
        }
            
        int izq = a + 1;
        int der = b;
        int result;

        while (izq < der){
            if((result = comparador.compare(arreglo[izq], arreglo[a])) > 0 && comparador.compare(arreglo[der], arreglo[a]) <= 0){
                int tempA = izq++;
                int tempB = der--;
                T temp = arreglo[tempA];
                arreglo[tempA] = arreglo[tempB];
                arreglo[tempB] = temp;
                }else if(result <= 0){
                    izq++;
                }else{
                    der--;     
            }
        }
        
        if (comparador.compare(arreglo[izq], arreglo[a]) > 0){
            izq--;
        }
            
        T temp = arreglo[a];
        arreglo[a] = arreglo[izq];
        arreglo[izq] = temp;
        
        quickSort(arreglo, comparador, a, izq-1);
        quickSort(arreglo, comparador, izq+1, b);
    }
 

    /**
     * Ordena el arreglo recibido usando QickSort.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo un arreglo cuyos elementos son comparables.
     */
    public static <T extends Comparable<T>> void
    quickSort(T[] arreglo) {
        quickSort(arreglo, (a, b) -> a.compareTo(b));
    }

    /**
     * Ordena el arreglo recibido usando SelectionSort.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo el arreglo a ordenar.
     * @param comparador el comparador para ordernar el arreglo.
     */
    public static <T> void
    selectionSort(T[] arreglo, Comparator<T> comparador) {
        // Aquí va su código.
        for(int i = 0; i < arreglo.length; i++){
            int min = i;
            for(int j = i + 1; j < arreglo.length; j++){
                if(comparador.compare(arreglo[j], arreglo[min]) < 0){
                    min = j;
                }      
            
            }

            T temp = arreglo[i];
            arreglo[i] = arreglo[min];
            arreglo[min] = temp;
            
        }
    }

    /**
     * Ordena el arreglo recibido usando SelectionSort.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo un arreglo cuyos elementos son comparables.
     */
    public static <T extends Comparable<T>> void
    selectionSort(T[] arreglo) {
        selectionSort(arreglo, (a, b) -> a.compareTo(b));
    }

    /**
     * Hace una búsqueda binaria del elemento en el arreglo. Regresa el índice
     * del elemento en el arreglo, o -1 si no se encuentra.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo el arreglo dónde buscar.
     * @param elemento el elemento a buscar.
     * @param comparador el comparador para hacer la búsqueda.
     * @return el índice del elemento en el arreglo, o -1 si no se encuentra.
     */
    public static <T> int
    busquedaBinaria(T[] arreglo, T elemento, Comparator<T> comparador) {
        // Aquí va su código.
        int min = 0;
	    int max = arreglo.length-1;
	    int metallica;

	    while(min < max+1) {
		    metallica = min + (( max - min ) / 2);

		    if(comparador.compare(arreglo[metallica], elemento) == 0 ){
                return metallica;
            }			
	 	    if(comparador.compare(elemento, arreglo[metallica]) < 0 ){
			    max = metallica-1;
		    }else{
			    min = metallica+1;
		    }
	    }

	    return -1;
      
    }

    /**
     * Hace una búsqueda binaria del elemento en el arreglo. Regresa el índice
     * del elemento en el arreglo, o -1 si no se encuentra.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo un arreglo cuyos elementos son comparables.
     * @param elemento el elemento a buscar.
     * @return el índice del elemento en el arreglo, o -1 si no se encuentra.
     */
    public static <T extends Comparable<T>> int
    busquedaBinaria(T[] arreglo, T elemento) {
        return busquedaBinaria(arreglo, elemento, (a, b) -> a.compareTo(b));
    }
}
