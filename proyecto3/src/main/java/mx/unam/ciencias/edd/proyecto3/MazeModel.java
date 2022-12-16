package mx.unam.ciencias.edd.proyecto3;

public class MazeModel {

	/**
	 * Collection to represent an entire maze
	 */
	private final Tablero[][] modeloTablero;

	public MazeModel(int rows, int columns) {

		modeloTablero = new Tablero[rows][columns];
		for(int filas=0; filas <modeloTablero.length; filas++) {
			for(int col=0; col<modeloTablero[filas].length; col++) {
				Tablero cuadritos = new Tablero(filas, col);
				modeloTablero[filas][col] = cuadritos;
			}
		}
	}

	/**
	* Get {@link #modeloTablero}
	*/
	public Tablero[][] getcuadritoss() {
		return modeloTablero;
	}
}
