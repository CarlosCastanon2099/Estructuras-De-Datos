package mx.unam.ciencias.edd;

/**
 * Clase para métodos estáticos con dispersores de bytes.
 */
public class Dispersores {

    /* Constructor privado para evitar instanciación. */
    private Dispersores() {}

    /**
     * Función de dispersión XOR.
     * @param llave la llave a dispersar.
     * @return la dispersión de XOR de la llave.
     */
    public static int dispersaXOR(byte[] llave) {
        // Aquí va su código.
        int lugar = 0;
        int result = 0;
        

        while(lugar < llave.length){
            result ^= mezcla(regresaEntero(llave, lugar++), regresaEntero(llave, lugar++), regresaEntero(llave, lugar++), regresaEntero(llave, lugar++));
        }

        return result;

    }

    private static int mezcla(int a, int b, int c, int d) {
        return (a << 24) | (b << 16) | (c << 8)  | d;

    }

    private static int regresaEntero(byte[] llave, int lugar) {
        if(lugar < llave.length){
            return (0xFF & llave[lugar]);
        }

        return 0;

    }

    /**
     * Función de dispersión de Bob Jenkins.
     * @param llave la llave a dispersar.
     * @return la dispersión de Bob Jenkins de la llave.
     */
    public static int dispersaBJ(byte[] llave) {
        // Aquí va su código.
        int a = 0x9E3779B9;
        int b = 0x9E3779B9;
        int c = 0xFFFFFFFF;

        int lugar = 0;
        boolean maquinaEncendida = true;

        while(maquinaEncendida){
            a += mezcla(regresaEntero(llave, lugar+3), regresaEntero(llave, lugar+2), regresaEntero(llave, lugar+1), regresaEntero(llave, lugar));
            lugar += 4;

            b += mezcla(regresaEntero(llave, lugar+3), regresaEntero(llave, lugar+2), regresaEntero(llave, lugar+1), regresaEntero(llave, lugar));
            lugar += 4;

            if(llave.length - lugar >= 4){
                c += mezcla(regresaEntero(llave, lugar+3), regresaEntero(llave, lugar+2), regresaEntero(llave, lugar+1), regresaEntero(llave, lugar));
            }else{
                maquinaEncendida = false;
                c += llave.length;
                c += mezcla(regresaEntero(llave, lugar+2), regresaEntero(llave, lugar+1), regresaEntero(llave, lugar), 0);
            }

            lugar += 4;

            a -= b + c;
            a ^= (c >>> 13);

            b -= c + a;
            b ^= (a << 8);

            c -= a + b;
            c ^= (b >>> 13);

            a -= b + c;
            a ^= (c >>> 12);

            b -= c + a;
            b ^= (a << 16);

            c -= a + b;
            c ^= (b >>> 5);

            a -= b + c;
            a ^= (c >>> 3);

            b -= c + a;
            b ^= (a << 10);

            c -= a + b;
            c ^= (b >>> 15);
        }

        return c;

    }

    /**
     * Función de dispersión Daniel J. Bernstein.
     * @param llave la llave a dispersar.
     * @return la dispersión de Daniel Bernstein de la llave.
     */
    public static int dispersaDJB(byte[] llave) {
        // Aquí va su código.
        int h = 5381;
        
        for(int i = 0; i < llave.length; i++){
            h += (h << 5) + regresaEntero(llave, i);
        }
        
        return h;
        
    }
}
