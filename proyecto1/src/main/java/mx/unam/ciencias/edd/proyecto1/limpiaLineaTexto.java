package mx.unam.ciencias.edd.proyecto1;
import mx.unam.ciencias.edd.*;

import java.text.Normalizer;

/**
 * Clase para limpiar las lineas de texto, esto para que todo caracter con acento, ñ, etc...
 * no interfiera en el funcionamiento del programa y el funcionamiento sea uno correcto.
 */
public class limpiaLineaTexto {

    /* La cadena original de la Linea de el Texto. */
    private String cadenaOriginal;
    
    /* La cadena limpia de la Linea de el Texto. */
    private String cadenaLimpia;

    /**
     * Crea una nueva línea de texto a partir de la cadena recibida.
     * @param cadena la cadena a partir de la cual se crea la línea.
     */
    public limpiaLineaTexto(String cadena) {
        cadenaOriginal = cadena;
        cadenaLimpia = Normalizer.normalize(cadenaOriginal,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").replaceAll("[^\\p{L}]", " ").replaceAll("\\s+", " ").trim().toLowerCase();
    }

    /**
     * Metodo para regresar la cadena original.
     * @return la cadena original.
     */
    public String getCadenaOriginal() {
        return cadenaOriginal;
    }

    /**
     * Metodo para regresar la cadena limpia.
     * @return la cadena limpia.
     */
    public String getCadenaLimpia() {
        return cadenaLimpia;
    }

    /**
     * Metodo para regresar una representación en cadena de la línea de texto.
     * @return una representación en cadena de la línea de texto.
     */
    @Override public String toString() {
        return cadenaOriginal;
    }
}