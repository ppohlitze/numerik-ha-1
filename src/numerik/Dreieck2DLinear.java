package numerik;

import bitub.matrix.Matrix;

/**
 * Klasse zur Erstellung der linearen Formfunktion eines Dreiecks und dessen analytische Ableitung
 * @author pianicklisch
 *
 */
public abstract class Dreieck2DLinear {

	/**
	 * Methode zur Erstellung der linearen Formfunktion sl eines Dreiecks
	 * @param zWerte
	 * @return lineare Formfunktion sl mit den z-Werten bzw. normalisierten 
	 * Koordinaten eines beliebigen Punktes
	 */
    public static Matrix erstelleSl(Matrix zWerte) {
    	
    	/*
    	 * Rückgabe lineare Formfunktion sl der Form mit dem Inhalt 
    	 * z-Werte bzw. normalisierte Koordinaten eines beliebigen Punktes
    	 */
        return zWerte;
    }
	
    /**
     * Methode zur Erstellung der linear analytisch abgeleiten Formfunktion slz
     * @return slz bzw. Einheitsmatrix
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
        
        //Rückgabe slz bzw. Einheitsmatrix
        return slz;
	}
}
