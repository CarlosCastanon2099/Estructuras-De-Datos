package mx.unam.ciencias.edd.proyecto2;

import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.IOException;

import mx.unam.ciencias.edd.*;

/**
 * Clase que utilizamos para encapsular los métodos correspondientes al
 * procesamiento de la entrada del programa.
 */
public class Entrada {

    /**
     * Constructor privado para evitar instanciación y utilizar los métodos
     * públicos solo de manera estática.
     */
    private Entrada() {  }

    /* Método privado que se encarga de abrir el flujo de entrada. */
    public static BufferedReader abrirEntrada(String archivo) {
        if (archivo != null)
            try {
                return new BufferedReader(new InputStreamReader(new FileInputStream(archivo)));
            } catch (IOException ioe) {
                System.out.printf("Error. El archivo %s no pudo ser leído.\n", archivo);
                System.exit(1);
            }

        return new BufferedReader(new InputStreamReader(System.in));
    }

    /* Cierra el flujo de entrada. */
    public static void cierraEntrada(BufferedReader entrada) {
        try {
            entrada.close();
        } catch(IOException ioe) {}
    }

    /**
     * Método privado para recuperar los elementos en la entrada que componen
     * la estructura de datos.
     */
    public static Lista<Integer> leerEntrada(BufferedReader entrada) {
        Lista<Integer> coleccion = new Lista<>();
        String numero = "";
        int letraInt;

        try{
            while((letraInt = entrada.read()) != -1){
                char letra = (char) letraInt;

                // Ignoramos hasta el final de la línea si encontramos un #.
                if (letra == '#') {
                    entrada.readLine();
                    continue;
                }
                if(letra <= 32) {
                    if (!numero.isEmpty()){
                        coleccion.agrega(Integer.parseInt(numero));
                    }
                    numero = "";
                }else if(Character.isDigit(letra))
                        numero += String.valueOf(letra);
                else if(Character.isDigit(letra))
                    numero += String.valueOf(letra);
                else{
                    System.out.printf("El archivo contiene el siguiente caracter no permitido: %c\n", letra);
                    System.exit(1);
                }
            }
        } catch (IOException ioe) {
            cierraEntrada(entrada);
            System.out.println("Ocurrió un error al leer la entrada.");
            System.exit(1);
        }

        return coleccion;
    }

    public static Estructura identificaEstructura(BufferedReader entrada) {
        String estructuraString = "";
        char letra;
        try{
            while((letra = (char) entrada.read()) != -1){
                if (letra == '#') {
                    entrada.readLine();
                    continue;
                }

                if(estructuraString.isEmpty() && letra <= 32){
                    continue;
                }else if((65 <= letra && letra <= 90) || (97 <= letra && letra <= 122))
                    estructuraString += letra;
                else
                    return Estructura.getEstructura(estructuraString);
            }
        }catch(IOException ioe){
            System.out.println("Error al leer la entrada.");
            System.exit(1);
        }

        return null;
    }
}
