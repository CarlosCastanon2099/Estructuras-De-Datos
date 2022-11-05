package mx.unam.ciencias.edd.proyecto2;

import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.IOException;

import mx.unam.ciencias.edd.*;

/**
 * Clase para tener control de los métodos correspondientes a
 * la entrada del programa.
 */
public class parametrosPrograma {

    /**
     * Constructor privado para evitar instanciasiones
     */
    private parametrosPrograma() {}

    /* Método privado que se encarga de abrir el flujo de entrada. */
    public static BufferedReader abrirEntrada(String archivo){
        if(archivo != null)
            try{
                return new BufferedReader(new InputStreamReader(new FileInputStream(archivo)));
            }catch(IOException ioe){
                System.out.printf("El archivo no ha podido ser leido.\n", archivo);
                System.exit(1);
            }

        return new BufferedReader(new InputStreamReader(System.in));
    }

    /* Cierra el flujo de entrada. */
    public static void cierraEntrada(BufferedReader entrada){
        try{
            entrada.close();
        }catch(IOException ioe) {}
    }

    /**
     * Método privado para recuperar los elementos en la entrada que componen
     * la estructura de datos.
     */
    public static Lista<Integer> leerEntrada(BufferedReader entrada){
        Lista<Integer> coleccion = new Lista<>();
        String numero = "";
        int letraInt;

        try{

            while((letraInt = entrada.read()) != -1){
                char caracter = (char) letraInt;

                if (caracter == '#'){
                    entrada.readLine();
                    continue;
                }
                if(caracter <= 32){
                    if (!numero.isEmpty()){
                        coleccion.agrega(Integer.parseInt(numero));
                    }
                    numero = "";

                }else if(Character.isDigit(caracter))
                        numero += String.valueOf(caracter);
                else if(Character.isDigit(caracter))
                    numero += String.valueOf(caracter);
                else{
                    System.out.printf("El caracter siguiente no es valido borralo e intentalo otravez por favor: %c\n", caracter);
                    System.exit(1);
                }
            }
        }catch (IOException ioe){
            cierraEntrada(entrada);
            System.out.println("Algo salio terriblemente mal al leer la entrada");
            System.exit(1);
        }

        return coleccion;
    }

    public static estructuras doxeaLaEstructura(BufferedReader entrada){
        String contenidoDeLaEstructura = "";
        char caracter;
        try{
            while((caracter = (char) entrada.read()) != -1){
                if (caracter == '#') {
                    entrada.readLine();
                    continue;
                }

                if(contenidoDeLaEstructura.isEmpty() && caracter <= 32){
                    continue;
                }else if((65 <= caracter && caracter <= 90) || (97 <= caracter && caracter <= 122))
                    contenidoDeLaEstructura += caracter;
                else
                    return estructuras.invocaLaEstructura(contenidoDeLaEstructura);
                
            }
        }catch(IOException ioe){
            System.out.println("Algo salio terriblemente mal al leer la entrada");
            System.exit(1);
        }

        return null;

    }

}
