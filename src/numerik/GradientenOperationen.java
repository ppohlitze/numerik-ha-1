package numerik;

import bitub.matrix.Matrix;
import bitub.matrix.MatrixOperations;
import modell.Markise;

/**
 * Klasse zur Berechnung des Gradienten an einem beliebigen Punkt mit Hilfe von numerischer Differentation
 * @author pianicklisch
 *
 */
public abstract class GradientenOperationen {

	/**
	 * Methode zur Berechnung des Gradienten
	 * @param markise, mit den Eck- und Streckenmittelpunkten
	 * @param zWerte, zWerte = normalisierte Koordinaten von einem beliebigen Punkt
	 * @return der Gradient von einem beliebigen Punkt bzw. das Ergebnis der Multiplikation des Stützwertevektors mit der 
	 * Ableitung der Formfunktionvektors nach den globalen Koordinaten
	 */
    public static Matrix berechneGradient(Markise markise, Matrix zWerte) {

    	//Erzeuge eine Matrix mit 1 Zeile und 6 Spalten
        Matrix ueT = new Matrix(1, 6);
        
        /*
         * Festlegung des Inhalts des Stützwertevektors, 
         * Inhalt sind die x3-Werte bzw. u-Werte der Eckpunkte und der Streckenmittelpunkte
         */
        ueT.setCoefficient(0, 0, markise.getP1().getCoefficient(2, 0));
        ueT.setCoefficient(0, 1, markise.getP2().getCoefficient(2, 0));
        ueT.setCoefficient(0, 2, markise.getP3().getCoefficient(2, 0));
        ueT.setCoefficient(0, 3, markise.getM1().getCoefficient(2, 0));
        ueT.setCoefficient(0, 4, markise.getM2().getCoefficient(2, 0));
        ueT.setCoefficient(0, 5, markise.getM3().getCoefficient(2, 0));

        //Erzeuge Matrix ableitungGnachN und weise ihr das Ergebnis der Methode berechneAbleitungGnachN zu
        Matrix xz = berechneXz(markise);

        //Erzeuge Matrix ableitungNnachG und weise ihr das Ergebnis der Methode berechneAbleitungNnachG zu
        Matrix zx = berechneZx(xz);

        //Erzeuge Matrix ableitungFnachG und weise ihr das Ergebnis der Methode berechneAbleitungFnachG zu
        Matrix sx = berechneSx(zx, zWerte);

        /*
         * Rückgabe des Gradienten an einem beliebigen Punkt bzw. das Ergebnis der Multiplikation des 
         * Stützwertevektors mit der Ableitung des Formfunktionsvektors nach den globalen Koordinaten
         */
        return MatrixOperations.multiply(ueT, sx);
    }

    /**
     * Methode zur Berechnung der Ableitung der globalen Koordinaten nach den normalisierten Koordinaten
     * @param markise, mit den 3 Eckpunkten
     * @return Ergebnis der Multiplikation der Stützwertematrix mit der Einheitsmatrix bzw.
     * dem linear analytisch abgeleiteten Formfunktionsvektor
     */
    private static Matrix berechneXz(Markise markise) {

    	/*
    	 * Erzeuge eine Matrix mit 3 Zeilen und 3 Spalten. 
    	 * Diese Matrix soll den linearen analytisch abgeleiteten Formfunktionsvektor, also die 
    	 * Einheitsmatrix darstellen
    	 */
        Matrix sLz = Dreieck2DLinear.erstelleSlz();
        

        //Erzeuge eine Matrix mit 2 Zeilen und 3 Spalten
        Matrix xeT = new Matrix(2, 3);
        
        //Festlegung des Inhalts der zuvor erzeugten Matrix. Inhalt sind jeweils die x1- und x2-Werte der Eckpunkte
        xeT.setCoefficient(0, 0, markise.getP1().getCoefficient(0, 0));
        xeT.setCoefficient(0, 1, markise.getP2().getCoefficient(0, 0));
        xeT.setCoefficient(0, 2, markise.getP3().getCoefficient(0, 0));
        xeT.setCoefficient(1, 0, markise.getP1().getCoefficient(1, 0));
        xeT.setCoefficient(1, 1, markise.getP2().getCoefficient(1, 0));
        xeT.setCoefficient(1, 2, markise.getP3().getCoefficient(1, 0));

        /*
         * Rückgabe von X,z bzw. das Ergebnisses der Multiplikation der Stützwertematrix mit der Einheitsmatrix bzw.
         * dem linearen analytisch abgeleiteten Formfunktionsvektor
         */
        return MatrixOperations.multiply(xeT, sLz);
    }

    /**
     * Methode zur Berechnung der Ableitung der normalisierten Koordinaten nach den globalen Koordinaten
     * @param xz, Ergebnis der zuvor berechneten Methode
     * @return Ergebnis der Multiplikation der Hilfsmatrix mit dem Ergebnis der Multiplikation
     * der Hilfsmatrix mit der ableutungGnachN
     */
    private static Matrix berechneZx(Matrix xz) {

    	//Erzeuge Matrix mit 3 Zeilen und 2 Spalten
        Matrix hilfsmatrix = new Matrix(3, 2);
        
        //Festlegung des Inhaltes der Hilfmatrix
        hilfsmatrix.setCoefficient(0,0, 1);
        hilfsmatrix.setCoefficient(0, 1, 0);
        hilfsmatrix.setCoefficient(1, 0,0);
        hilfsmatrix.setCoefficient(1, 1, 1);
        hilfsmatrix.setCoefficient(2, 0, -1);
        hilfsmatrix.setCoefficient(2, 1, -1);

        //Erzeuge Matrix xZu und weise ihr das Ergebnis der Multiplikation von ableitungGnachN mit der Hilfsmatrix zu
        Matrix xZu = MatrixOperations.multiply(xz, hilfsmatrix);

        //Erzeuge Matrix zXu und weise ihr die Inverse von xZu zu
        Matrix zXu = MatrixOperations.inverse(xZu);

        //Rückgabe von Z,x bzw. das Ergebnis der Multiplikation der Hilfsmatrix mit zXu
        return MatrixOperations.multiply(hilfsmatrix, zXu);
    }

    /**
     * Methode zur Berechnung der Ableitung der Formfunktion nach globalen Koordinaten
     * @param zx, Ergebnis der zuvor berechneten Methode
     * @param zWerte, zWerte = normalisierte Koordinaten von einem beliebigen Punkt
     * @return Ergebnis der Multiplikation der quadratisch analytisch abgeleiteten Formfunktion 
     * mit dem Ergebnis der zuvor berechneten Methode
     */
    private static Matrix berechneSx(Matrix zx, Matrix zWerte) {

    	//Erzeuge Matrix formfunktion und weise ihr die Erstellte Matrix aus der Klasse Dreieck2DQuadratisch zu
        Matrix sQz = Dreieck2DQuadratisch.erstelleSQz(zWerte);

        /*
         * Rückgabe von S,x bzw. das Ergebnisses der Multiplikation der quadratisch analytisch abgeleiteten 
         * Formfunktion mit dem Ergebnis der zuvor berechneten Methode
         */
        return MatrixOperations.multiply(sQz, zx);
    }
}
