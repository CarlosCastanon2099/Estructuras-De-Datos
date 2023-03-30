package mx.unam.ciencias.edd.proyecto2;

/**
 * Enumeración con las estructuras de datos que podemos representar.
 */
public enum estructuras {
    estructuraLista,
    estructuraPila,
    estructuraCola,
    estructuraArbolBinarioOrdenado,
    estructuraArbolBinarioCompleto,
    estructuraArbolRojinegro,
    estructuraArbolAvl,
    estructuraGrafica,
    estructuraFalsa;

    /**
     * Regresa la enumeración correspondiente a la representación como cadena
     * de texto recibida. Regresa estructuraFalsa cuando el texto no es una estructura Verdadera
     */
    public static estructuras invocaLaEstructura(String estructura){
        switch(estructura){
            case "Lista":                
            return estructuraLista;

            case "Pila":                 
            return estructuraPila;

            case "Cola":                 
            return estructuraCola;

            case "ArbolBinarioOrdenado": 
            return estructuraArbolBinarioOrdenado;

            case "ArbolBinarioCompleto": 
            return estructuraArbolBinarioCompleto;

            case "ArbolRojinegro":       
            return estructuraArbolRojinegro;

            case "ArbolAVL":             
            return estructuraArbolAvl;

            case "Grafica":              
            return estructuraGrafica;

            default:                     
            return estructuraFalsa;

        }


    }



}
