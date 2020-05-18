package numerik;

import bitub.matrix.Matrix;

/**
 * Klasse zur Erstellung der quadratisch analytisch abgeleiten Formfunktion
 * @author pianicklisch
 *
 */
public abstract class Dreieck2DQuadratisch {

    public static Matrix erstelleSq(Matrix zWerte) {
        Matrix sq = new Matrix(6, 1);
        sq.setCoefficient(0,0, (zWerte.getCoefficient(0, 0) * ((2 * zWerte.getCoefficient(0, 0)) - 1)));
        sq.setCoefficient(1,0, (zWerte.getCoefficient(1, 0) * ((2 * zWerte.getCoefficient(1, 0)) - 1)));
        sq.setCoefficient(2,0, (zWerte.getCoefficient(2, 0) * ((2 * zWerte.getCoefficient(2, 0)) - 1)));
        sq.setCoefficient(3,0, (4 * zWerte.getCoefficient(0, 0) * zWerte.getCoefficient(1, 0)));
        sq.setCoefficient(4,0, (4 * zWerte.getCoefficient(1, 0) * zWerte.getCoefficient(2, 0)));
        sq.setCoefficient(5,0, (4 * zWerte.getCoefficient(2, 0) * zWerte.getCoefficient(0, 0)));

        return sq;
    }

	/**
	 * Methode zur Erstellung der quadratisch analytisch abgeleiten Formfunktion
	 * @param zWerte, zWerte = normalisierte Koordinaten von einem beliebigen Punkt
	 * @return analytisch abgeleiten Formfunktion von einem beliebigen Punkt
	 */
    public static Matrix erstelleSQz(Matrix zWerte) {

    	/*
    	 *  Erzeuge eine Matrix mit 6 Zeilen und 3 Spalten, 
    	 *  in der das Ergebnis der Methode gespeichert werden soll.
    	 */
        Matrix sQz = new Matrix(6, 3);
        
        //Inhalt mit ggfl. Formeln der Matrix festlegen
        sQz.setCoefficient(0,0, (4 * zWerte.getCoefficient(0,0) - 1));
        sQz.setCoefficient(0,1, 0);
        sQz.setCoefficient(0,2, 0);
        sQz.setCoefficient(1,0, 0);
        sQz.setCoefficient(1,1, (4 * zWerte.getCoefficient(1,0) - 1));
        sQz.setCoefficient(1,2, 0);
        sQz.setCoefficient(2,0, 0);
        sQz.setCoefficient(2,1, 0);
        sQz.setCoefficient(2,2, (4 * zWerte.getCoefficient(2,0) - 1));
        sQz.setCoefficient(3,0, (4 * zWerte.getCoefficient(1,0)));
        sQz.setCoefficient(3,1, (4 * zWerte.getCoefficient(0,0)));
        sQz.setCoefficient(3,2, 0);
        sQz.setCoefficient(4,0, 0);
        sQz.setCoefficient(4,1, (4 * zWerte.getCoefficient(2,0)));
        sQz.setCoefficient(4,2, (4 * zWerte.getCoefficient(1,0)));
        sQz.setCoefficient(5,0, (4 * zWerte.getCoefficient(2,0)));
        sQz.setCoefficient(5,1, 0);
        sQz.setCoefficient(5,2, (4 * zWerte.getCoefficient(0,0)));

        //Rückgabe der analytisch abgeleiteten Formfunktion von einem beliebigen Punkt
        return sQz;
    }
}
