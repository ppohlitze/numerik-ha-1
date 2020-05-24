package numerik;

import bitub.matrix.Matrix;
import bitub.matrix.MatrixOperations;
import modell.Markise;

/**
 * Klasse zur Berechnung des Gradienten an einem beliebigen Punkt mittels numerischer Differentiation
 * @author pianicklisch
 *
 */
public abstract class GradientenOperationen {

	/**
	 * Methode zur Berechnung des Gradienten mittels numerischer Differentiation
	 * @param markise, mit den Eck- und Streckenmittelpunkten
	 * @param zWerte, zWerte = normalisierte Koordinaten von einem beliebigen Punkt
	 * @return der Gradient an einem beliebigen Punkt
	 * Schritt 4 nach Übung 2
	 */
    public static Matrix berechneGradient(Markise markise, Matrix zWerte) {

    	//Erzeuge eine Matrix mit 1 Zeile und 6 Spalten, ueT = Stützwertevektor
        Matrix ueT = new Matrix(1, 6);
        
        /*
         * Festlegung des Inhalts des Stützwertevektors ueT, 
         * Inhalt sind die x3-Werte bzw. Höhen der Eckpunkte und der Streckenmittelpunkte
         */
        ueT.setCoefficient(0, 0, markise.getP1().getCoefficient(2, 0));
        ueT.setCoefficient(0, 1, markise.getP2().getCoefficient(2, 0));
        ueT.setCoefficient(0, 2, markise.getP3().getCoefficient(2, 0));
        ueT.setCoefficient(0, 3, markise.getM1().getCoefficient(2, 0));
        ueT.setCoefficient(0, 4, markise.getM2().getCoefficient(2, 0));
        ueT.setCoefficient(0, 5, markise.getM3().getCoefficient(2, 0));

        /*
         * Erzeuge Matrix xz und weise ihr das Ergebnis der Methode berechneXz zu
         * xz = Ableitung der globalen nach den normalisierten Koordinaten
         * Schritt 1 nach Übung 2
         */
        Matrix xz = berechneXz(markise);

        /*
         * Erzeuge Matrix zx und weise ihr das Ergebnis der Methode berechneZx zu
         * zx = Ableitung der normalisierten nach den globalen Koordinaten
         * Schritt 2 nach Übung 2
         */
        Matrix zx = berechneZx(xz);

        /*
         * Erzeuge Matrix sx und weise ihr das Ergebnis der Methode berechneSx zu
         * sx = Ableitung der Formfunktionen nach den globalen Koordinaten
         * Schritt 3 nach Übung 2
         */
        Matrix sx = berechneSx(zx, zWerte);

        /*
         * Rückgabe des Gradienten an einem beliebigen Punkt
         * Schritt 4 nach Übung 2
         */
        return MatrixOperations.multiply(ueT, sx);
    }

    /**
     * Methode zur Berechnung von xz = Ableitung der globalen Koordinaten nach den normalisierten
     * @param markise, mit den 3 Eckpunkten
     * @return xz = Ableitung der globalen Koordinaten nach den normalisierten
     * Schritt 1 nach Übung 2
     */
    private static Matrix berechneXz(Markise markise) {

    	/*
    	 * Erzeuge eine Matrix slz und speicher in ihr das Ergebnis der Methode erstelleSlz,
    	 * das Ergebnis ist die 3x3 Einheitsmatrix
    	 */
        Matrix slz = Dreieck2DLinear.erstelleSlz();
        

        //Erzeuge eine Matrix mit 2 Zeilen und 3 Spalten
        Matrix xeT = new Matrix(2, 3);
        
        //Festlegung des Inhalts der zuvor erzeugten Matrix. Inhalt sind jeweils die x1- und x2-Werte der Eckpunkte
        xeT.setCoefficient(0, 0, markise.getP1().getCoefficient(0, 0));
        xeT.setCoefficient(0, 1, markise.getP2().getCoefficient(0, 0));
        xeT.setCoefficient(0, 2, markise.getP3().getCoefficient(0, 0));
        xeT.setCoefficient(1, 0, markise.getP1().getCoefficient(1, 0));
        xeT.setCoefficient(1, 1, markise.getP2().getCoefficient(1, 0));
        xeT.setCoefficient(1, 2, markise.getP3().getCoefficient(1, 0));

        
        // Rückgabe von xz
        return MatrixOperations.multiply(xeT, slz);
    }

    /**
     * Methode zur Berechnung von zx = Ableitung der normalisierten Koordinaten nach den globalen
     * @param xz, Ergebnis der zuvor berechneten Methode
     * @return zx = Ableitung der normalisierten Koordinaten nach den globalen
     * Schritt 2 nach Übung 2
     */
    private static Matrix berechneZx(Matrix xz) {

    	//Erzeuge Matrix mit 3 Zeilen und 2 Spalten
        Matrix hilfsmatrix = new Matrix(3, 2);
        
        /*
         * Festlegung des Inhaltes der zuvor erstellten Matrix, 
         * Inhalt ist die nach Koordinaten reduzierte Hilfsmatrix
         */
        hilfsmatrix.setCoefficient(0,0, 1);
        hilfsmatrix.setCoefficient(0, 1, 0);
        hilfsmatrix.setCoefficient(1, 0,0);
        hilfsmatrix.setCoefficient(1, 1, 1);
        hilfsmatrix.setCoefficient(2, 0, -1);
        hilfsmatrix.setCoefficient(2, 1, -1);

        //Erzeuge Matrix xZu und weise ihr das Ergebnis der Multiplikation von xz mit der Hilfsmatrix zu
        Matrix xZu = MatrixOperations.multiply(xz, hilfsmatrix);

        //Erzeuge Matrix zXu und weise ihr die Inverse von xZu zu
        Matrix zXu = MatrixOperations.inverse(xZu);

        //Rückgabe von zx
        return MatrixOperations.multiply(hilfsmatrix, zXu);
    }

    /**
     * Methode zur Berechnung von sx = Ableitung der Formfunktionen nach globalen Koordinaten
     * @param zx, Ergebnis der zuvor berechneten Methode
     * @param zWerte, zWerte = normalisierte Koordinaten von einem beliebigen Punkt
     * @return sx = Ableitung der Formfunktionen nach den globalen Koordinaten
     * Schritt 3 nach Übung 2
     */
    private static Matrix berechneSx(Matrix zx, Matrix zWerte) {

    	//Erzeuge Matrix sqz und weise ihr das Ergebnis der Methode erstelleSqz zu
        Matrix sqz = Dreieck2DQuadratisch.erstelleSqz(zWerte);

        /*
         * Rückgabe von sx
         */
        return MatrixOperations.multiply(sqz, zx);
    }
}
