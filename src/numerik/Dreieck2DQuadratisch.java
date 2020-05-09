package numerik;

import bitub.matrix.Matrix;

public abstract class Dreieck2DQuadratisch {

    public static Matrix berechneAbleitungS(Matrix zWerte) {

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

        return formfunktionsvektor;
    }
}
