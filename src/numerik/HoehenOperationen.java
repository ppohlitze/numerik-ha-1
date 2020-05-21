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

	public static double berechneX3M3(Markise markise) {

		Matrix zWerteM3 = new Matrix(3, 1);

		zWerteM3.setCoefficient(0, 0, 1. / 2.);
		zWerteM3.setCoefficient(1, 0, 0);
		zWerteM3.setCoefficient(2, 0, 1. / 2.);

		//Erzeuge eine Matrix mit 1 Zeile und 3 Spalten
		Matrix ueT = new Matrix(1, 3);

		/*
		 * Festlegung des Inhaltes der zuvor erzeugten Matrix,
		 * Inhalt sind die Höhen bzw. die x3-Werte der Eckpunkte P1, P2 und P3
		 */
		ueT.setCoefficient(0, 0, markise.getP1().getCoefficient(2, 0));
		ueT.setCoefficient(0, 1, markise.getP2().getCoefficient(2, 0));
		ueT.setCoefficient(0, 2, markise.getP3().getCoefficient(2, 0));

		Matrix sl = Dreieck2DLinear.erstelleSl(zWerteM3);
		return MatrixOperations.multiply(ueT, sl).getCoefficient(0,0);
	}
	
	/**
	 * Methode zur Berechnung der Höhe bzw. der x3-Werte eines beliebigen Punktes mit Hilfe von Interpolation
	 * @param zWerte, zWerte = normalisierte Koordinaten von einem beliebigen Punkt
	 * @param markise, Eckpunkte der Markise
	 * @return Ergebnis der Multiplikation Höhen bzw. der x3-Werte von P1, P2 und P3 mit den normalisierten Koordinaten 
	 * eines beliebigen Punktes
	 */
	public static double berechneHoehe(Matrix zWerte, Markise markise) {
		//Erzeuge eine Matrix mit 1 Zeile und 3 Spalten
		Matrix ueT = new Matrix(1, 6);

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

        /*
         * Rückgabe ist die Höhe eines beliebigen Punkten bzw. das Ergebnis der Multiplikation der Höhen der 
         * Eckpunkte mit den normalisierten Koordinaten des beliebigen Punktes
         */
        return MatrixOperations.multiply(ueT, sq).getCoefficient(0,0);
    }
}
