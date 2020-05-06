package numerik;

import bitub.matrix.Matrix;
import bitub.matrix.MatrixOperations;
import modell.Markise;

public abstract class GradientenOperationen {

    public static Matrix berechneGradient(Markise markise, double hoeheV, Matrix zWerte) {

        Matrix steutzwerteVektor = new Matrix(1, 6);
        steutzwerteVektor.setCoefficient(0, 0, markise.getP1().getCoefficient(2, 0));
        steutzwerteVektor.setCoefficient(0, 1, markise.getP2().getCoefficient(2, 0));
        steutzwerteVektor.setCoefficient(0, 2, markise.getP3().getCoefficient(2, 0));
        steutzwerteVektor.setCoefficient(0, 3, markise.getM1().getCoefficient(2, 0));
        steutzwerteVektor.setCoefficient(0, 4, markise.getM2().getCoefficient(2, 0));
        steutzwerteVektor.setCoefficient(0, 5, hoeheV);

        Matrix ableitungGnachN = berechneAbleitungGnachN(markise);

        Matrix ableitungNnachG = berechneAbleitungNnachG(ableitungGnachN);

        Matrix ableitungFnachG = berechneAbleitungFnachG(ableitungNnachG, zWerte);

        return MatrixOperations.multiply(steutzwerteVektor, ableitungFnachG);
    }

    private static Matrix berechneAbleitungGnachN(Markise markise) {

        Matrix einheitsmatrix = new Matrix(3, 3);
        einheitsmatrix.setCoefficient(0,0, 1);
        einheitsmatrix.setCoefficient(0, 1, 0);
        einheitsmatrix.setCoefficient(0, 2,0);
        einheitsmatrix.setCoefficient(1, 0, 0);
        einheitsmatrix.setCoefficient(1, 1, 1);
        einheitsmatrix.setCoefficient(1, 2, 0);
        einheitsmatrix.setCoefficient(2, 0, 0);
        einheitsmatrix.setCoefficient(2, 1, 0);
        einheitsmatrix.setCoefficient(2, 2, 1);

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

        Matrix formfunktionsvektor = new Matrix(6, 3);
        formfunktionsvektor.setCoefficient(0,0, (4 * zWerte.getCoefficient(0,0) - 1));
        formfunktionsvektor.setCoefficient(0,1, 0);
        formfunktionsvektor.setCoefficient(0,2, 0);
        formfunktionsvektor.setCoefficient(1,0, 0);
        formfunktionsvektor.setCoefficient(1,1, (4 * zWerte.getCoefficient(1,0) - 1));
        formfunktionsvektor.setCoefficient(1,2, 0);
        formfunktionsvektor.setCoefficient(2,0, 0);
        formfunktionsvektor.setCoefficient(2,1, 0);
        formfunktionsvektor.setCoefficient(2,2, (4 * zWerte.getCoefficient(2,0) - 1));
        formfunktionsvektor.setCoefficient(3,0, (4 * zWerte.getCoefficient(1,0)));
        formfunktionsvektor.setCoefficient(3,1, (4 * zWerte.getCoefficient(0,0)));
        formfunktionsvektor.setCoefficient(3,2, 0);
        formfunktionsvektor.setCoefficient(4,0, 0);
        formfunktionsvektor.setCoefficient(4,1, (4 * zWerte.getCoefficient(2,0)));
        formfunktionsvektor.setCoefficient(4,2, (4 * zWerte.getCoefficient(1,0)));
        formfunktionsvektor.setCoefficient(5,0, (4 * zWerte.getCoefficient(2,0)));
        formfunktionsvektor.setCoefficient(5,1, 0);
        formfunktionsvektor.setCoefficient(5,2, (4 * zWerte.getCoefficient(0,0)));

        return MatrixOperations.multiply(formfunktionsvektor, ableitungGnachN);
    }
}
