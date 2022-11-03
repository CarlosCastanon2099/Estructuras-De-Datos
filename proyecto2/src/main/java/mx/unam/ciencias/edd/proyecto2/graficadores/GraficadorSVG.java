package mx.unam.ciencias.edd.proyecto2.graficadores;

/**
 * Clase con varios métodos que nos permiten generar el SVG 
 *  para las gráficas de las estructuras de datos.
 */
public class GraficadorSVG {

    /**
     * Constructor privado para asegurar que la clase se utilice únicamente de
     * manera estática.
     */
    private GraficadorSVG() {}

    /**
     * Método que regresa la cadena para declarar que estamos utilizando XML.
     */
    public static String generaElInicioDelArchivo() {
        return "<?xml version='1.0' encoding='UTF-8' ?>";
    }

    /**
     * Metodo para generar el inicio de un SVG con sus respectivas medidas.
     */
    public static String generaElInicioDelSVG(int medidaX, int medidaY) {
        return String.format("<svg width='%d' height='%d'><g>", medidaX, medidaY);
    }

    /**
     * Metodo para generar el termino de un SVG 
     */
    public static String generaElTerminoDelSVG() {
        return "</g></svg>";
    }

    /**
     * Metodo para generar el SVG de una línea con las coordenadas y el color recibidos.
     */
    public static String generaLineaSVG(int origenX, int origenY, int cambioX, int cambioY, String color) {
        return String.format("<line x1='%d' y1='%d' x2='%d' y2='%d'" + " stroke='%s' stroke-width='3' />", origenX, origenY, origenX + cambioX, origenY + cambioY, color);
    }


    /**
     * Metodo para generar el SVG de un triángulo isósceles horizontal contenido dentro del
     * rectángulo que se genera con un punto de origen, y el cambio en los ejes
     * X y Y.
     */
    public static String generaTriangulo(int origenX, int origenY, int medidaX, int medidaY, String color) {
        return String.format("<polygon points='%d,%d %d,%d %d,%d' fill='%s' />", origenX, (medidaY / 2) + origenY, origenX + medidaX, origenY, origenX + medidaX, origenY + medidaY, color);
    }

    /**
     * Genera el SVG de un rectángulo que contiene texto centrado en ambos
     * ejes.
     */
    public static String generaRectanguloConTexto(int origenX, int origenY, int medidaX, int medidaY, String colorBorde, String colorRelleno, int tamanoFuente, String colorFuente, String contenido) {
        String svg = String.format("<rect x='%d' y='%d' width='%d' height='%d' stroke='%s' fill='%s' />", origenX, origenY, medidaX, medidaY, colorBorde, colorRelleno);
        svg += graficaTexto((medidaX / 2) + origenX, (medidaY /2) + origenY, tamanoFuente, colorFuente, contenido);
        return svg;
    }

    /**
     * Genera el SVG de un círculo.
     */
    public static String generaCirculo(int centroX, int centroY, int radio,
                                        String colorBorde, String colorRelleno) {
        return String.format("<circle cx='%d' cy='%d' r='%d' stroke='%s' stroke-width='3' fill='%s' />", centroX, centroY, radio, colorBorde, colorRelleno);
    }

    /**
     * Genera el SVG de un círculo que contiene texto centrado en ambos ejes.
     */
    public static String generaCirculoConTexto(int centroX, int centroY, int radio, String colorBorde, String colorRelleno, int tamanoFuente, String colorFuente, String contenido) {
        String svg = generaCirculo(centroX, centroY, radio, colorBorde, colorRelleno);
        svg += graficaTexto(centroX, centroY, tamanoFuente, colorFuente, contenido);
        return svg;
    }

    /**
     * Genera el SVG de un cuadro de texto.
     */
    public static String graficaTexto(int centroX, int centroY, int tamanoFuente, String colorFuente, String contenido) {
        return String.format("<text x='%d' y='%d' text-anchor='middle'" + " font-family='Courier New' font-size='%d' fill='%s'>%s</text>", centroX, centroY + 5, tamanoFuente, colorFuente, contenido);
    }
}
