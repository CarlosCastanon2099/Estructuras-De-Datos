package mx.unam.ciencias.edd.proyecto3;

import java.beans.PropertyChangeSupport;


public class Tablero{

    private final int filas, columnas;
    private boolean esUnMuro;
    //support to fire property change events
    private PropertyChangeSupport pcs;

    public Tablero(int filas, int columnas)  {
       this(filas, columnas, false);
    }

	public Tablero(int filas, int columnas, boolean esUnMuro) {
		this.filas = filas;
		this.columnas = columnas;
		this.esUnMuro = esUnMuro;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Tablero)) return false;
		Tablero other = (Tablero)obj;
		return filas == other.getFilas() && columnas == other.getColumnas();
	}

	public void setPropertChangeSupport(PropertyChangeSupport pcs) {
		this.pcs = pcs;
	}

	private void firePropertyChange(String name, Object oldValue, Object newValue) {
		if(pcs != null) {
			pcs.firePropertyChange(name, oldValue, newValue);
		}
	}

	/**
	* Get {@link #esUnMuro}
	*/
	public boolean esUnMuro() {
		return esUnMuro;
	}

	/**
	* Set {@link #esUnMuro}
	*/
	public void estadoMuro(boolean esUnMuro) {
    	Object old = this.esUnMuro;
    	this.esUnMuro = esUnMuro;
    	firePropertyChange("Wall", old, esUnMuro);
	}

	/**
	* Get {@link #filas}
	*/
	public int getFilas() {
		return filas;
	}

	/**
	* Get {@link #columnas}
	*/
	public int getColumnas() {
		return columnas;
	}

	@Override
	public String toString() {
		return  "["+ (esUnMuro ? "Wall " : "Path " ) +  filas + "-" + columnas + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return 17*filas + 31*columnas;
	}
}