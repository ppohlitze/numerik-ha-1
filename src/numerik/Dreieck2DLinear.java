package numerik;

import bitub.matrix.Matrix;

/**
 * Klasse zur Erstellung der linear analytisch abgeleiten Formfunktion
 * @author pianicklisch
 *
 */
public abstract class Dreieck2DLinear {

    public static Matrix erstelleSl(Matrix zWerte) {
        return zWerte;
    }
	
    /**
     * Methode zur Erstellung der linear analytisch abgeleiten Formfunktion
     * @return sLz bzw. Einheitsmatrix
     */
	public static Matrix erstelleSlz() {
		
		// Erzeuge eine 3x3 Matrix
		Matrix slz = new Matrix(3, 3);
		
		//Festlegung des Inhaltes dieser Matrix, Inhalt = Einheitsmatrix
		double[] zeile1 = {1, 0, 0};
        double[] zeile2 = {0, 1, 0};
        double[] zeile3 = {0, 0, 1};
        slz.setRow(0, zeile1);
        slz.setRow(1, zeile2);
        slz.setRow(2, zeile3);
        
        //Rückgabe sLz bzw. Einheitsmatrix
        return slz;
	}
}
