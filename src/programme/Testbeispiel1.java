package programme;

import bitub.matrix.Matrix;
import modell.Markise;
import numerik.GradientenOperationen;
import numerik.HoehenOperationen;

public class Testbeispiel1 {

    public static void main(String[] args) {

        Matrix p1 = new Matrix(3, 1);
        p1.setCoefficient(0, 0, 1.0);
        p1.setCoefficient(1, 0, 1.5);
        p1.setCoefficient(2, 0, 3.2);

        Matrix p2 = new Matrix(3, 1);
        p2.setCoefficient(0, 0, 4.8);
        p2.setCoefficient(1, 0, 0.4);
        p2.setCoefficient(2, 0, 3.5);

        Matrix p3 = new Matrix(3, 1);
        p3.setCoefficient(0, 0, 3.3);
        p3.setCoefficient(1, 0, 3.4);
        p3.setCoefficient(2, 0, 3.2);

        Matrix m1 = new Matrix(3, 1);
        m1.setCoefficient(2, 0, 2.7);

        Matrix m2 = new Matrix(3, 1);
        m2.setCoefficient(2, 0, 3.1);
        
        Matrix m3 = new Matrix(3, 1);
        m3.setCoefficient(2, 0, 3.2);

        Markise markise = new Markise(p1, p2, p3, m1, m2);

        Matrix zWerteW = new Matrix(3, 1);
        zWerteW.setCoefficient(0, 0, 1./3.);
        zWerteW.setCoefficient(1, 0, 1./3.);
        zWerteW.setCoefficient(2,0, 1./3.);

        Matrix zWerteV = new Matrix(3, 1);
        zWerteV.setCoefficient(0, 0, 1./2.);
        zWerteV.setCoefficient(1, 0, 0);
        zWerteV.setCoefficient(2,0, 1./2.);

        double hoeheW = HoehenOperationen.berechneHoehe(zWerteW, markise);
        Matrix gradientW = GradientenOperationen.berechneGradient(markise, m3.getCoefficient(2, 0), zWerteW);

        double hoeheV = HoehenOperationen.berechneHoehe(zWerteV, markise);
        Matrix gradientV = GradientenOperationen.berechneGradient(markise, m3.getCoefficient(2, 0), zWerteV);

        System.out.println("---------------------------------------------");
        System.out.println("Hoehe von W: "+ hoeheW);
        System.out.println("Gradient von W: "+ gradientW.getCoefficient(0,0) +"\t"+ gradientW.getCoefficient(0,1));
        System.out.println("---------------------------------------------");
        System.out.println("Hoehe von V: "+ hoeheV);
        System.out.println("Gradient von V: "+ gradientV.getCoefficient(0,0) +"\t"+ gradientV.getCoefficient(0,1));
        System.out.println("---------------------------------------------");
    }
}
