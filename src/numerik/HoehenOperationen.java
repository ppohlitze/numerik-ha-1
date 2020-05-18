package numerik;

import bitub.matrix.Matrix;
import bitub.matrix.MatrixOperations;
import modell.Markise;

/**
 * Klasse zur Berechnung der Höhe bzw. der x3-Werte eines beliebigen Punktes mit Hilfe von Interpolation
 * @author pianicklisch
 *
 */
public abstract class HoehenOperationen {
	
	/**
	 * Methode zur Berechnung der Höhe bzw. der x3-Werte eines beliebigen Punktes mit Hilfe von Interpolation
	 * @param zWerte, zWerte = normalisierte Koordinaten von einem beliebigen Punkt
	 * @param markise, Eckpunkte der Markise
	 * @return Ergebnis der Multiplikation Höhen bzw. der x3-Werte von P1, P2 und P3 mit den normalisierten Koordinaten 
	 * eines beliebigen Punktes
	 */
	public static double berechneHoehe(Matrix zWerte, Markise markise) {
		
		//Erzeuge eine Matrix mit 1 Zeile und 3 Spalten
        Matrix xeT = new Matrix(1, 3);
        
        /*
         * Festlegung des Inhaltes der zuvor erzeugten Matrix,
         * Inhalt sind die Höhen bzw. die x3-Werte der Eckpunkte P1, P2 und P3
         */
        xeT.setCoefficient(0, 0, markise.getP1().getCoefficient(2, 0));
        xeT.setCoefficient(0, 1, markise.getP2().getCoefficient(2, 0));
        xeT.setCoefficient(0, 2, markise.getP3().getCoefficient(2, 0));

        /*
         * Rückgabe ist die Höhe eines beliebigen Punkten bzw. das Ergebnis der Multiplikation der Höhen der 
         * Eckpunkte mit den normalisierten Koordinaten des beliebigen Punktes
         */
        return MatrixOperations.multiply(xeT, zWerte).getCoefficient(0,0);
    }
}
