package numerik;

import bitub.matrix.Matrix;
import bitub.matrix.MatrixOperations;
import modell.Markise;

public abstract class GradientenOperationen {

    public static Matrix berechneGradient(Markise markise, double hoeheM3, Matrix zWerte) {

        Matrix stuetzwerteVektor = new Matrix(1, 6);
        stuetzwerteVektor.setCoefficient(0, 0, markise.getP1().getCoefficient(2, 0));
        stuetzwerteVektor.setCoefficient(0, 1, markise.getP2().getCoefficient(2, 0));
        stuetzwerteVektor.setCoefficient(0, 2, markise.getP3().getCoefficient(2, 0));
        stuetzwerteVektor.setCoefficient(0, 3, markise.getM1().getCoefficient(2, 0));
        stuetzwerteVektor.setCoefficient(0, 4, markise.getM2().getCoefficient(2, 0));
        stuetzwerteVektor.setCoefficient(0, 5, hoeheM3);

        Matrix ableitungGnachN = berechneAbleitungGnachN(markise);

        Matrix ableitungNnachG = berechneAbleitungNnachG(ableitungGnachN);

        Matrix ableitungFnachG = berechneAbleitungFnachG(ableitungNnachG, zWerte);

        return MatrixOperations.multiply(stuetzwerteVektor, ableitungFnachG);
    }

    private static Matrix berechneAbleitungGnachN(Markise markise) {

        Matrix einheitsmatrix = new Matrix(3, 3);
        double[] zeile1 = {1, 0, 0};
        double[] zeile2 = {0, 1, 0};
        double[] zeile3 = {0, 0, 1};
        einheitsmatrix.setRow(0, zeile1);
        einheitsmatrix.setRow(1, zeile2);
        einheitsmatrix.setRow(2, zeile3);

        Matrix stuetzwerte = new Matrix(2, 3);
        stuetzwerte.setCoefficient(0, 0, markise.getP1().getCoefficient(0, 0));
        stuetzwerte.setCoefficient(0, 1, markise.getP2().getCoefficient(0, 0));
        stuetzwerte.setCoefficient(0, 2, markise.getP3().getCoefficient(0, 0));
        stuetzwerte.setCoefficient(1, 0, markise.getP1().getCoefficient(1, 0));
        stuetzwerte.setCoefficient(1, 1, markise.getP2().getCoefficient(1, 0));
        stuetzwerte.setCoefficient(1, 2, markise.getP3().getCoefficient(1, 0));

        return MatrixOperations.multiply(stuetzwerte, einheitsmatrix);
    }

    private static Matrix berechneAbleitungNnachG(Matrix ableitungGnachN) {

        Matrix hilfsmatrix = new Matrix(3, 2);
        hilfsmatrix.setCoefficient(0,0, 1);
        hilfsmatrix.setCoefficient(0, 1, 0);
        hilfsmatrix.setCoefficient(1, 0,0);
        hilfsmatrix.setCoefficient(1, 1, 1);
        hilfsmatrix.setCoefficient(2, 0, -1);
        hilfsmatrix.setCoefficient(2, 1, -1);

        Matrix xZu = MatrixOperations.multiply(ableitungGnachN, hilfsmatrix);

        Matrix zXu = MatrixOperations.inverse(xZu);

        return MatrixOperations.multiply(hilfsmatrix, zXu);
    }

    private static Matrix berechneAbleitungFnachG(Matrix ableitungGnachN, Matrix zWerte) {

        Matrix formfunktionsvektor = Dreieck2DQuadratisch.berechneAbleitungS(zWerte);

        return MatrixOperations.multiply(formfunktionsvektor, ableitungGnachN);
    }
}
