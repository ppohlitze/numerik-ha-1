package programme;

import bitub.matrix.Matrix;
import modell.Markise;
import numerik.GradientenOperationen;
import numerik.HoehenOperationen;

public class Testbeispiel2 {

    public static void main(String[] args) {

    	 Matrix p1 = new Matrix(3, 1);
         p1.setCoefficient(0, 0, 1.0);
         p1.setCoefficient(1, 0, 2.0);
         p1.setCoefficient(2, 0, 3.0); //add value here

         Matrix p2 = new Matrix(3, 1);
         p2.setCoefficient(0, 0, 8.0);
         p2.setCoefficient(1, 0, 4.0);
         p2.setCoefficient(2, 0, 4.0); //add value here

         Matrix p3 = new Matrix(3, 1);
         p3.setCoefficient(0, 0, 3.5);
         p3.setCoefficient(1, 0, 4.0);
         p3.setCoefficient(2, 0, 3.5); //add value here

         Matrix m1 = new Matrix(3, 1);
         m1.setCoefficient(2, 0, 2.7); //add value here

         Matrix m2 = new Matrix(3, 1);
         m2.setCoefficient(2, 0, 3.1); //add value here

         Markise markise = new Markise(p1, p2, p3, m1, m2);

         Matrix zWerteW = new Matrix(3, 1);
         zWerteW.setCoefficient(0, 0, 1./2.);
         zWerteW.setCoefficient(1, 0, 1./4.);
         zWerteW.setCoefficient(2,0, 1./4.);

         double hoeheW = HoehenOperationen.berechneHoehe(zWerteW, markise);
         System.out.println("Hoehe von W: "+ hoeheW);

         Matrix gradientW = GradientenOperationen.berechneGradient(markise, hoeheW, zWerteW);
         System.out.println("Gradient von W: "+ gradientW.getCoefficient(0,0) +"\t"+ gradientW.getCoefficient(0,1));

         System.out.println("---------------------------------------------");

         Matrix zWerteV = new Matrix(3, 1);
         zWerteV.setCoefficient(0, 0, 2./3.);
         zWerteV.setCoefficient(1, 0, 1./3.);
         zWerteV.setCoefficient(2,0, 0);

         double hoeheV =  HoehenOperationen.berechneHoehe(zWerteV, markise);
         System.out.println("Hoehe von V: "+ hoeheV);

         Matrix gradientV = GradientenOperationen.berechneGradient(markise, hoeheV, zWerteV);
         System.out.println("Gradient von V: "+ gradientV.getCoefficient(0,0) +"\t"+ gradientV.getCoefficient(0,1));
    }
}
