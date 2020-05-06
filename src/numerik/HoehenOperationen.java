package numerik;

import bitub.matrix.Matrix;
import bitub.matrix.MatrixOperations;
import modell.Markise;

public abstract class HoehenOperationen {
	
	public static double berechneHoehe(Matrix zWerte, Markise markise) {
        Matrix stuetzwerte = new Matrix(1, 3);
        stuetzwerte.setCoefficient(0, 0, markise.getP1().getCoefficient(2, 0));
        stuetzwerte.setCoefficient(0, 1, markise.getP2().getCoefficient(2, 0));
        stuetzwerte.setCoefficient(0, 2, markise.getP3().getCoefficient(2, 0));

        return MatrixOperations.multiply(stuetzwerte, zWerte).getCoefficient(0,0);
    }
}
