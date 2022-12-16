package mx.unam.ciencias.edd.proyecto3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

	/**
	 * Generate a maze using Prime's algorithm
	 * Based on: https://stackoverflow.com/a/29758926/3992939
	 *
	 * @author c0der
	 * 25 Jun 2020
	 *
	 */
	public class PrimeMazeGenerator {

		private static final int[][] DIRECTIONS = { //distance of 2 to each side
				{ 0 ,-2}, // north
				{ 0 , 2}, // south
				{ 2 , 0}, // east
				{-2 , 0}, // west
		};

		private final Tablero[][] cuadritos;
		private final Random random;

		public PrimeMazeGenerator(Tablero[][] cuadritos) {
			this.cuadritos = cuadritos;
			random = new Random();
		}

		void primMazeGeneration() {

			//Start with a grid full of cuadritosViews in state wall (not a path).
			for(int i = 0; i < cuadritos.length; i++){
				for(int j = 0; j < cuadritos[0].length ; j++){
					cuadritos[i][j].estadoMuro(true);
				}
			}

			//Pick a random cell
			int x = random.nextInt(cuadritos.length);
			int y = random.nextInt(cuadritos[0].length);

			cuadritos[x][y].estadoMuro(false); //set cell to path
			//Compute cell frontier and add it to a frontier collection
			Set<Tablero> lineasFronteras = new HashSet<>(lineasFronterasOf(cuadritos[x][y]));

			while (!lineasFronteras.isEmpty()){

				//Pick a random cell from the frontier collection
				Tablero lineasFrontera = lineasFronteras.stream().skip(random.nextInt(lineasFronteras.size())).findFirst().orElse(null);

				//Get its neighbors: cuadritos in distance 2 in state path (no wall)
				List<Tablero> vecinosInmediatos =  passageCellsOf(lineasFrontera);

				if(!vecinosInmediatos.isEmpty()) {
					//Pick a random vecino
					Tablero vecino = vecinosInmediatos.get(random.nextInt(vecinosInmediatos.size()));
					//Connect the frontier cell with the vecino
					connect(lineasFrontera, vecino);
				}

				//Compute the frontier cuadritos of the chosen frontier cell and add them to the frontier collection
				lineasFronteras.addAll(lineasFronterasOf(lineasFrontera));
				//Remove frontier cell from the frontier collection
				lineasFronteras.remove( lineasFrontera);
				
			}
		}

		//Frontier cuadritos: wall cuadritos in a distance of 2
		private List<Tablero> lineasFronterasOf(Tablero cell) {

			return cellsAround(cell, true);
		}

		//Frontier cuadritos: passage (no wall) cuadritos in a distance of 2
		private List<Tablero> passageCellsOf(Tablero cell) {

			return cellsAround(cell, false);
		}

		private List<Tablero> cellsAround(Tablero cell, boolean esUnMuro) {

			List<Tablero> frontier = new ArrayList<>();
			for(int[] direction : DIRECTIONS){
				int newRow = cell.getFilas() + direction[0];
				int newCol = cell.getColumnas() + direction[1];
				if(isValidPosition(newRow, newCol) && cuadritos[newRow][newCol].esUnMuro() == esUnMuro){
					frontier.add(cuadritos[newRow][newCol]);
				}
			}

			return frontier;
		}

		//connects cuadritos which are distance 2 apart
		private void connect( Tablero frontiercuadritosView, Tablero neighbour) {

			int inBetweenRow = (neighbour.getFilas() + frontiercuadritosView.getFilas())/2;
			int inBetweenCol = (neighbour.getColumnas() + frontiercuadritosView.getColumnas())/2;
			frontiercuadritosView.estadoMuro(false);
			cuadritos[inBetweenRow][inBetweenCol].estadoMuro(false);
			neighbour.estadoMuro(false);
		}

		private boolean isValidPosition(int filas, int col) {
			return filas >= 0 && filas < cuadritos.length
						&& col >= 0 && col < cuadritos[0].length;
		}

		public PrimeMazeGenerator setDelay(long delay) {
			this.delay = delay;
			return this;
		}
	}
