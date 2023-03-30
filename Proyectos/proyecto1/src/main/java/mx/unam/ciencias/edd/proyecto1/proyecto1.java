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
 * Clase main del proyecto1.
 */
public class proyecto1 {

    /**
     * Metodo para imprimir las líneas ya ordenadas o en su defecto
     * guardar las líneas ordenadas en la salida 
     * en caso de que se haya ingresado un archivo de salida con la bandera -o
     */
     public static void regresaSalida(Lista<limpiaLineaTexto> lineas, String salida) {
         if (salida == null)
             for (limpiaLineaTexto linea : lineas)
                 System.out.println(linea.toString());
         else
             try {
                meteSacaLostxt.guarda(salida, lineas);
             } catch(IOException ioe) {
                 System.out.printf("\n Explote y no guarde nada , proyecto1.java linea 30.");
                 System.exit(1);
             }
     }
 

     /**
     * Metodo para ordenar las líneas de texto de los archivos ingresados
     * 
     * @param lineas la lista de lineas de texto de los archivos ingresados.
     * @param reversa el orden en el que se ordenaran las lineas.
     * @return las lineas de texto ordenadas de forma reversa.
     */
     private static Lista<limpiaLineaTexto> ordena(Lista<limpiaLineaTexto> lineas, boolean reversa) {
         return reversa ? ordenameLaLinea.ordenaReversa(lineas) : ordenameLaLinea.ordena(lineas);
     }
 

     /**
     * Metodo para leer la(s) entrada(s)
     * 
     * @param fuentesEntrada la lista de parametros ingresados.
     * @return regresa una lista con las líneas leídas.
     */
     private static Lista<limpiaLineaTexto> leeLineas(Lista<String> fuentesEntrada) {
         Lista<BufferedReader> entradas = null;
         Lista<limpiaLineaTexto> lineas = new Lista<>();
 
         try {
             entradas = meteSacaLostxt.dameLasEntradas(fuentesEntrada);
             lineas = meteSacaLostxt.leemeLaEntrada(entradas);
             meteSacaLostxt.cierrameLasEntradas(entradas);
         } catch(IOException ioe) {
            meteSacaLostxt.cierrameLasEntradas(entradas);
             System.out.println("Trone como ejote , proyecto1.java linea 64.");
             System.exit(1);
         }
 
         return lineas;
     }
 
     /* El unico e inigualable metodo main */
     public static void main(String[] args) {
         banderas losargumentos = new banderas(args);
         boolean reversa = losargumentos.printReverse();
         String archivoSalida = null;
         try {
             archivoSalida = losargumentos.banderaGuarda();
         } catch(IllegalArgumentException iae) {
             System.out.println("\nLa bandera -o debe estar seguida de un " +
                                "archivo en el cual escribir.\n" +
                                "Uso: java -jar proyecto1.jar -o <archivo>");
             System.exit(1);
         }
 

         Lista<limpiaLineaTexto> lineas = leeLineas(losargumentos.fuentesEntrada());
         Lista<limpiaLineaTexto> ordenadas = ordena(lineas, reversa);
         regresaSalida(ordenadas, archivoSalida);
     }
 }
