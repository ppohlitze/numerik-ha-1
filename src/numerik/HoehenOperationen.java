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
	 * Methode zur Berechnung der Höhe von M3 durch Interpolation mit linearem Ansatz
	 * @param markise
	 * @return Höhe von M3
	 */
	public static double berechneHoeheM3(Markise markise) {

		/*
		 * Erzeuge Matrix mit 3 Zeilen und 1 Spalte in der die z-Werte bzw. normalisierten 
		 * 	Koordinaten von M3 gespeichert werden sollen
		 */
	
		Matrix zWerteM3 = new Matrix(3, 1);

		//Inhalt dieser Matrix festlegen (z-Werte von M3)
		zWerteM3.setCoefficient(0, 0, 1. / 2.);
		zWerteM3.setCoefficient(1, 0, 0);
		zWerteM3.setCoefficient(2, 0, 1. / 2.);

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
		 * Erzeuge Matrix und speicher das Ergebnis der Methode erstelleSl darin 
		 * Außerdem setze in das Ergebnis die z-Werte von M3 ein
		 */
		Matrix sl = Dreieck2DLinear.erstelleSl(zWerteM3);
		
		/*
		 * Rückgabe ist das Ergebnis der Multiplikation von dem Stützwertematrix xet 
		 * mit der linearen Formfunktion eines Dreiecks, das Ergebnis ist somit die Höhe von M3
		 */
		return MatrixOperations.multiply(xeT, sl).getCoefficient(0,0);
	}
	
	/**
	 * Methode zur Berechnung der Höhe bzw. der x3-Werte eines beliebigen Punktes mit Hilfe von Interpolation
	 * @param zWerte, zWerte = normalisierte Koordinaten von einem beliebigen Punkt
	 * @param markise, Eckpunkte der Markise
	 * @return Höhe bzw. x3-Wert eines beliebigen Punktes
	 */
	public static double berechneHoehe(Matrix zWerte, Markise markise) {
		
		//Erzeuge eine Matrix mit 1 Zeile und 3 Spalten
		Matrix xeT = new Matrix(1, 6);

		/*
		 * Festlegung des Inhaltes der zuvor erzeugten Matrix,
		 * Inhalt sind die Höhen bzw. die x3-Werte der Eckpunkte P1, P2 und P3
		 */
		xeT.setCoefficient(0, 0, markise.getP1().getCoefficient(2, 0));
		xeT.setCoefficient(0, 1, markise.getP2().getCoefficient(2, 0));
		xeT.setCoefficient(0, 2, markise.getP3().getCoefficient(2, 0));
		xeT.setCoefficient(0, 3, markise.getM1().getCoefficient(2, 0));
		xeT.setCoefficient(0, 4, markise.getM2().getCoefficient(2, 0));
		xeT.setCoefficient(0, 5, markise.getM3().getCoefficient(2, 0));

		/*
		 * Erzeuge Matrix und speicher das Ergebnis der Methode erstelleSq darin 
		 * Außerdem setze in das Ergebnis die z-Werte von einem beliebigen Punkt ein
		 */
		Matrix sq = Dreieck2DQuadratisch.erstelleSq(zWerte);

        
        //Rückgabe ist die Höhe eines beliebigen Punktes
        return MatrixOperations.multiply(xeT, sq).getCoefficient(0,0);
    }
}
