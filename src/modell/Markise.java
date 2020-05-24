package modell;

import bitub.matrix.Matrix;

/**
 * Klasse zur Erstellung einer Markise mit 3 Eckpunkten und 3 Streckenmittelpunkten. 
 * @author pianicklisch
 *
 */
public class Markise {

	//Eckpunkte der Markise vom Typ Matrix
    private Matrix p1;
    private Matrix p2;
    private Matrix p3;

    //Streckenmittelpunkte der Markise vom Typ Matrix
    private Matrix m1;
    private Matrix m2;
    private Matrix m3;

	/**
	 * Konstruktor für eine Markise,
	 * m3 wird hier nicht berücksichtigt, da m3 hier noch nicht bekannt ist
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param m1
	 * @param m2
	 */
    public Markise(Matrix p1, Matrix p2, Matrix p3, Matrix m1, Matrix m2) {
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.m1 = m1;
		this.m2 = m2;
	}
    
    /**
     * gibt den Wert von P1 zurück
     * @return P1
     */
	public Matrix getP1() {
		return p1;
	}

	/**
	 * gibt den Wert von P2 zurück
	 * @return P2
	 */
	public Matrix getP2() {
		return p2;
	}

	/**
	 * gibt den Wert von P3 zurück
	 * @return P3
	 */
	public Matrix getP3() {
		return p3;
	}

	/**
	 * gibt den Wert von M1 zurück
	 * @return M1
	 */
	public Matrix getM1() {
		return m1;
	}

	/**
	 * gibt den Wert von M2 zurück
	 * @return M2
	 */
	public Matrix getM2() {
		return m2;
	}

	/**
	 * gibt den Wert von M3 zurück
	 * @return M3
	 */
	public Matrix getM3() {
		return m3;
	}

	/**
	 * setzt den Wert von M3
	 * @param m3
	 */
	public void setM3(Matrix m3) {
		this.m3 = m3;
	}
}
