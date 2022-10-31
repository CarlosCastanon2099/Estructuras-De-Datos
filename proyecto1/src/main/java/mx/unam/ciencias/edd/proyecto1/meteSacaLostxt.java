package mx.unam.ciencias.edd.proyecto1;
import mx.unam.ciencias.edd.*;

import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;


/**
 * Clase para meter y sacar los txt.
 */
public class meteSacaLostxt {

    /* La lista de archivos en formato String . */
    private static Lista<String> listotaDeArchivos = new Lista<String>();


    /* Constructor privado de meteSacaLostxt */
    private meteSacaLostxt() {}

    /** 
     * Metodo para obtener una lista mediante el BufferedReader 
     * de las lineas de texto de el (los) txt ingresado(s)
     * 
     * @param archivos la lista de archivos (lista que contempla una lista de 1 archivo).
     * @return la lista con el BufferedReader correspondiente a cada txt ingresado.
     * @throws IOException si las entradas son nulas.
     */
    public static Lista<BufferedReader> dameLasEntradas(Lista<String> archivos) throws IOException {
        Lista<BufferedReader> entradas = new Lista<BufferedReader>();

        if(archivos.esVacia()){
            entradas.agregaFinal(new BufferedReader(new InputStreamReader(System.in)));
            return entradas;
        }

        for(String archivo : archivos){
            try{
                BufferedReader entrada = new BufferedReader(new InputStreamReader(new FileInputStream(archivo)));
                entradas.agregaFinal(entrada);
            }catch(IOException ioe){
                cierrameLasEntradas(entradas);
                throw new IOException("entradas nulas");
            }
        }

        return entradas;
    }

    /** 
     * Metodo para leer una lista mediante el BufferedReader 
     * de las lineas de texto limpias de el (los) txt ingresado(s)
     * 
     * @param entradas la lista lineas de texto de los txt ingresados.
     * @return la lista de lineas limpeas ordenadas.
     * @throws IOException sinceramente esto no deberia estar debido al metodo anterior pero si no lo pongo
     *                      esto no funciona, entonces lo dejo vivir tranquilamente en esa linea de codigo.
     */
    public static Lista<limpiaLineaTexto> leemeLaEntrada(Lista<BufferedReader> entradas) throws IOException {
        Lista<limpiaLineaTexto> lineas = new Lista<limpiaLineaTexto>();
        String linea;

        for(BufferedReader entrada : entradas)
            while((linea = entrada.readLine()) != null)
                lineas.agregaFinal(new limpiaLineaTexto(linea));

        return lineas;
    }


    /** 
     * Metodo para cerrar el BufferedReader de todas las entradas posibles
     * 
     * @param entradas la lista lineas de texto de los txt ingresados.
     * @return esta es nuestra clausula de escape la cual no regresa nada en caso de que las entradas sean nulas
     */
    public static void cierrameLasEntradas(Lista<BufferedReader> entradas) {
        if (entradas == null){
            return;
        }
            

        for(BufferedReader entrada : entradas){
            try {
                entrada.close();
            } catch(IOException ioe) {}
        }
            
    }



    /** 
     * Metodo para cerrar el BufferedReader de todas las entradas posibles
     * 
     * @param archivo el archivo txt en el que vamos a guardar.
     * @param lineas la lista de lineas limpias que vamos a guardar.
     */
    public static void guarda(String archivo, Lista<limpiaLineaTexto> lineas) throws IOException {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(archivo)));

        for (limpiaLineaTexto linea : lineas)
            out.write(linea.toString() + "\n");

        out.close();
    }

    
    /**
    * Metodo para obtener la lista de archivos.
    * @return la lista de archivos.
    */

    public static Lista<String> getlistotaDeArchivos() {
        return listotaDeArchivos;
    }
    

    
}

    

