package mx.unam.ciencias.edd.proyecto2;

import java.io.BufferedReader;

import mx.unam.ciencias.edd.*;
import mx.unam.ciencias.edd.proyecto2.graficadores.*;
import mx.unam.ciencias.edd.proyecto2.graficadores.arboles.*;
import mx.unam.ciencias.edd.proyecto2.graficadores.otros.*;

/**
 * Clase Main del proyecto 2.
 */
public class proyecto2 {
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
    
        BufferedReader flujoEntrada = Entrada.abrirEntrada(archivo);
        Estructura estructuraDeDatos = Entrada.identificaEstructura(flujoEntrada);

        if(estructuraDeDatos == null){
            Entrada.cierraEntrada(flujoEntrada);
            System.out.println("Tu entrada es vacia, no hay nada que graficar, ingresa una estructura (válida) por favor =D");
            System.exit(1);
        }

        if(estructuraDeDatos == Estructura.estructuraFalsa){
            Entrada.cierraEntrada(flujoEntrada);
            System.out.println("Tu estructura de datos no es válida, ingresa una estructura válida por favor =D");
            System.exit(1);
        }

        Lista<Integer> datos = Entrada.leerEntrada(flujoEntrada);
        Entrada.cierraEntrada(flujoEntrada);


        GraficadorEstructura<Integer> graficador;
        switch(estructuraDeDatos){
            case estructuraLista:
            graficador = new GraficadorLista<>(datos);
            break;

            case estructuraPila:
            graficador = new GraficadorPila<>(datos);
            break;

            case estructuraCola:
            graficador = new GraficadorCola<>(datos);
            break;

            case estructuraArbolBinarioOrdenado:
            graficador = new GraficadorArbolBinarioOrdenado<>(new ArbolBinarioOrdenado<>(datos));
            break;

            case estructuraArbolBinarioCompleto:
            graficador = new GraficadorArbolBinarioCompleto<>(new ArbolBinarioCompleto<>(datos));
            break;

            case estructuraArbolRojinegro:
            graficador = new GraficadorArbolRojinegro<>(new ArbolRojinegro<>(datos));
            break;

            case estructuraArbolAvl:
            graficador = new GraficadorArbolAVL<>(new ArbolAVL<>(datos));
            break;

            case estructuraGrafica:
            graficador = new GraficadorGrafica<>(construyeGrafica(datos));
            break;

            default:
            graficador = null;

        }

        
        System.out.println(graficador.graficar());


    }

    
}
