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

		Matrix ueT;
		double hoehe = 0;

		if (markise.getM3() != null) {
			//Erzeuge eine Matrix mit 1 Zeile und 3 Spalten
			ueT = new Matrix(1, 6);

			/*
			 * Festlegung des Inhaltes der zuvor erzeugten Matrix,
			 * Inhalt sind die Höhen bzw. die x3-Werte der Eckpunkte P1, P2 und P3
			 */
			ueT.setCoefficient(0, 0, markise.getP1().getCoefficient(2, 0));
			ueT.setCoefficient(0, 1, markise.getP2().getCoefficient(2, 0));
			ueT.setCoefficient(0, 2, markise.getP3().getCoefficient(2, 0));
			ueT.setCoefficient(0, 3, markise.getM1().getCoefficient(2, 0));
			ueT.setCoefficient(0, 4, markise.getM2().getCoefficient(2, 0));
			ueT.setCoefficient(0, 5, markise.getM3().getCoefficient(2, 0));

			Matrix sq = Dreieck2DQuadratisch.erstelleSq(zWerte);

			hoehe = MatrixOperations.multiply(ueT, sq).getCoefficient(0,0);
		} else {
			//Erzeuge eine Matrix mit 1 Zeile und 3 Spalten
			ueT = new Matrix(1, 3);

			/*
			 * Festlegung des Inhaltes der zuvor erzeugten Matrix,
			 * Inhalt sind die Höhen bzw. die x3-Werte der Eckpunkte P1, P2 und P3
			 */
			ueT.setCoefficient(0, 0, markise.getP1().getCoefficient(2, 0));
			ueT.setCoefficient(0, 1, markise.getP2().getCoefficient(2, 0));
			ueT.setCoefficient(0, 2, markise.getP3().getCoefficient(2, 0));

			Matrix sl = Dreieck2DLinear.erstelleSl(zWerte);
			hoehe = MatrixOperations.multiply(ueT, sl).getCoefficient(0,0);

			Matrix m3 = new Matrix(3, 1);
			m3.setCoefficient(2, 0, hoehe);
			markise.setM3(m3);
		}

        /*
         * Rückgabe ist die Höhe eines beliebigen Punkten bzw. das Ergebnis der Multiplikation der Höhen der 
         * Eckpunkte mit den normalisierten Koordinaten des beliebigen Punktes
         */
        return hoehe;
    }
}
