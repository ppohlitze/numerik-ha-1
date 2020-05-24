package numerik;

import bitub.matrix.Matrix;

/**
 * Klasse zur Erstellung der quadratischen Formfunktion eines Dreiecks und dessen analytische Ableitung
 * @author pianicklisch
 *
 */
public abstract class Dreieck2DQuadratisch {

	/**
	 * Methode zur Erstellung der quadratischen Formfunktion eines Dreiecks an einem beliebigen Punkt
	 * @param zWerte
	 * @return sq bzw. die quadratische Formfunktion eines Dreiecks an einem beliebigen Punkt
	 */
    public static Matrix erstelleSq(Matrix zWerte) {
    	
    	/*
    	 *  Erzeuge eine Matrix mit 6 Zeilen und 1 Spalten, 
    	 *  in der das Ergebnis der Methode, also die quadratische Formfunktion 
    	 *  eines Dreiecks an einem beliebigen Punkt, gespeichert werden soll.
    	 */
        Matrix sq = new Matrix(6, 1);
        
        //Inhalt der zuvor erzeugten Matrix festlegen
        sq.setCoefficient(0,0, (zWerte.getCoefficient(0, 0) * ((2 * zWerte.getCoefficient(0, 0)) - 1)));
        sq.setCoefficient(1,0, (zWerte.getCoefficient(1, 0) * ((2 * zWerte.getCoefficient(1, 0)) - 1)));
        sq.setCoefficient(2,0, (zWerte.getCoefficient(2, 0) * ((2 * zWerte.getCoefficient(2, 0)) - 1)));
        sq.setCoefficient(3,0, (4 * zWerte.getCoefficient(0, 0) * zWerte.getCoefficient(1, 0)));
        sq.setCoefficient(4,0, (4 * zWerte.getCoefficient(1, 0) * zWerte.getCoefficient(2, 0)));
        sq.setCoefficient(5,0, (4 * zWerte.getCoefficient(2, 0) * zWerte.getCoefficient(0, 0)));

        //Rückgabe der quadratischen Formfunktion eines Dreiecks an einem beliebigen Punkt
        return sq;
    }

	/**
	 * Methode zur Erstellung der quadratisch analytisch abgeleiten Formfunktion eines Dreiecks an einem 
	 * beliebigen Punkt
	 * @param zWerte, zWerte = normalisierte Koordinaten von einem beliebigen Punkt
	 * @return analytisch abgeleiten Formfunktion eines Dreiecks an einem beliebigen Punkt
	 */
    public static Matrix erstelleSqz(Matrix zWerte) {

    	/*
    	 *  Erzeuge eine Matrix mit 6 Zeilen und 3 Spalten, 
    	 *  in der das Ergebnis der Methode gespeichert werden soll.
    	 */
        Matrix sqz = new Matrix(6, 3);
        
        //Inhalt der zuvor erzeugten Matrix festlegen
        sqz.setCoefficient(0,0, (4 * zWerte.getCoefficient(0,0) - 1));
        sqz.setCoefficient(0,1, 0);
        sqz.setCoefficient(0,2, 0);
        sqz.setCoefficient(1,0, 0);
        sqz.setCoefficient(1,1, (4 * zWerte.getCoefficient(1,0) - 1));
        sqz.setCoefficient(1,2, 0);
        sqz.setCoefficient(2,0, 0);
        sqz.setCoefficient(2,1, 0);
        sqz.setCoefficient(2,2, (4 * zWerte.getCoefficient(2,0) - 1));
        sqz.setCoefficient(3,0, (4 * zWerte.getCoefficient(1,0)));
        sqz.setCoefficient(3,1, (4 * zWerte.getCoefficient(0,0)));
        sqz.setCoefficient(3,2, 0);
        sqz.setCoefficient(4,0, 0);
        sqz.setCoefficient(4,1, (4 * zWerte.getCoefficient(2,0)));
        sqz.setCoefficient(4,2, (4 * zWerte.getCoefficient(1,0)));
        sqz.setCoefficient(5,0, (4 * zWerte.getCoefficient(2,0)));
        sqz.setCoefficient(5,1, 0);
        sqz.setCoefficient(5,2, (4 * zWerte.getCoefficient(0,0)));

        //Rückgabe der analytisch abgeleiteten Formfunktion eines Dreiecks an einem beliebigen Punkt
        return sqz;
    }
}
