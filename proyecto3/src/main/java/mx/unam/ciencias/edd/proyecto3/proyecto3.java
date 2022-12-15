package mx.unam.ciencias.edd.proyecto3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import mx.unam.ciencias.edd.*;
import mx.unam.ciencias.edd.proyecto3.dibujantes.*;
import mx.unam.ciencias.edd.proyecto3.dibujantes.arboles.*;
import mx.unam.ciencias.edd.proyecto3.dibujantes.otros.*;

/**
 * Clase Main del proyecto 3.
 */
public class proyecto3 {
    /**
     * Genera la gráfica constituida por los vértices recibidos
     * Si el número de elementos no es par lanza un error y termina la ejecución.
     */
    private static <T> Grafica<T> construyeGrafica(Lista<T> datos) {
        if(datos.getElementos()%2 != 0){
            System.out.println("Numero de elementos impar, ingresa una cantidad par por favor =D");
            System.exit(1);
        }

        Grafica<T> grafica = new Grafica<>();
        for (T vertice : datos){
            if (!grafica.contiene(vertice)){
                grafica.agrega(vertice);
            }
        }

        int i = 0;
        T verticeAnterior = null;
        for(T vertice : datos){
            if(i++ % 2 == 0){
                verticeAnterior = vertice;
            }else if(verticeAnterior.equals(vertice))
            continue;
            else if(!grafica.sonVecinos(verticeAnterior, vertice))
            grafica.conecta(verticeAnterior, vertice);
        }
            
        return grafica;

    }

    public static void main(String[] args){
        if(args.length > 1){
            System.out.println("\nEntrada no correcta" + "\nLas Instrucciones vienen en el README.md");
            System.exit(1);
        }

        // Abrimos la entrada.
        String archivo = null;
        if (args.length == 1){
            archivo = args[0];
        }
    
        BufferedReader flujoEntrada = parametrosPrograma.abrirEntrada(archivo);
        estructuras estructuraDeDatos = parametrosPrograma.doxeaLaEstructura(flujoEntrada);

        if(estructuraDeDatos == null){
            parametrosPrograma.cierraEntrada(flujoEntrada);
            System.out.println("Tu entrada es vacia, no hay nada que graficar, ingresa una estructura (válida) por favor =D");
            System.exit(1);
        }

        if(estructuraDeDatos == estructuras.estructuraFalsa){
            parametrosPrograma.cierraEntrada(flujoEntrada);
            System.out.println("Tu estructura de datos no es válida, ingresa una estructura válida por favor =D");
            System.exit(1);
        }

        Lista<Integer> datos = parametrosPrograma.leerEntrada(flujoEntrada);
        parametrosPrograma.cierraEntrada(flujoEntrada);


        dibujameLaEstructura<Integer> graficador;
        switch(estructuraDeDatos){
            case estructuraLista:
            graficador = new dibujanteDeListas<>(datos);
            break;

            case estructuraPila:
            graficador = new dibujanteDePilas<>(datos);
            break;

            case estructuraCola:
            graficador = new dibujanteDeColas<>(datos);
            break;

            case estructuraArbolBinarioOrdenado:
            graficador = new dibujanteDeArbolBinarioOrdenado<>(new ArbolBinarioOrdenado<>(datos));
            break;

            case estructuraArbolBinarioCompleto:
            graficador = new dibujanteDeArbolBinarioCompleto<>(new ArbolBinarioCompleto<>(datos));
            break;

            case estructuraArbolRojinegro:
            graficador = new dibujanteDeArbolRojinegro<>(new ArbolRojinegro<>(datos));
            break;

            case estructuraArbolAvl:
            graficador = new dibujanteDeArbolAVL<>(new ArbolAVL<>(datos));
            break;

            case estructuraGrafica:
            graficador = new dibujanteDeGraficas<>(construyeGrafica(datos));
            break;

            default:
            graficador = null;

        }

        int eleccion = 0;

        System.out.println("El Codigo de tu SVG es: " + "\n" );
        System.out.println(graficador.graficar());
        try {
            int nombreArchivo = 1;
            String ruta = nombreArchivo + ".svg";
            String contenido = graficador.graficar();
            File file = new File(ruta);
            File definitivo = file;

             if (definitivo.exists()) {
                definitivo.createNewFile();
            }
            
            FileWriter fw = new FileWriter(definitivo);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(contenido);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        System.out.println("\nTu SVG se genero y guardo exitosamente en la raiz de esta carpeta " + "\n" );


    }


}
