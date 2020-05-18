package numerik;

import bitub.matrix.Matrix;

/**
 * Klasse zur Erstellung der linear analytisch abgeleiten Formfunktion
 * @author pianicklisch
 *
 */
public abstract class Dreieck2DLinear {
	
/**
 * Methode zur Erstellung der linear analytisch abgeleiten Formfunktion
 * @return sLz bzw. Einheitsmatrix
 */

	public static Matrix erstelleSLz() {
		
		// Erzeuge eine 3x3 Matrix
		Matrix sLz = new Matrix(3, 3);
		
		//Festlegung des Inhaltes dieser Matrix, Inhalt = Einheitsmatrix
		double[] zeile1 = {1, 0, 0};
        double[] zeile2 = {0, 1, 0};
        double[] zeile3 = {0, 0, 1};
        sLz.setRow(0, zeile1);
        sLz.setRow(1, zeile2);
        sLz.setRow(2, zeile3);
        
        //Rückgabe sLz bzw. Einheitsmatrix
        return sLz;
	}
}
