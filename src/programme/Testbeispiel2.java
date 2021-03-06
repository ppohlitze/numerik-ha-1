package programme;

import bitub.matrix.Matrix;
import modell.Markise;
import numerik.GradientenOperationen;
import numerik.HoehenOperationen;

/**
 * Klasse, die die Höhen und Gradienten von Punkt V und Punkt W aus dem 2. Testbeispiel berechnet und auf der Konsole ausgibt
 * @author pianicklisch
 *
 */
public class Testbeispiel2 {

	/**
	 * Main-Methode, die die Höhen und Gradienten von Punkt V und Punkt W aus dem 2. Testbeispiel berechnet und auf der Konsole ausgibt
	 * @param args
	 */
    public static void main(String[] args) {
    	
    	// Erzeuge Matrix mit 3 Zeilen und 1 Spalte
    	Matrix p1 = new Matrix(3, 1);
    	 
    	 /*
          * Festlegung des Inhaltes der zuvor erstellten Matrix,
          * Inhalt sind die x1, x2 und x3-Werte von P1
          */
         p1.setCoefficient(0, 0, 1.0);
         p1.setCoefficient(1, 0, 2.0);
         p1.setCoefficient(2, 0, 3.0);

         //Erzeuge Matrix mit 3 Zeilen und 1 Spalte
         Matrix p2 = new Matrix(3, 1);
         
         /*
          * Festlegung des Inhaltes der zuvor erstellten Matrix,
          * Inhalt sind die x1, x2 und x3-Werte von P2
          */
         p2.setCoefficient(0, 0, 8.0);
         p2.setCoefficient(1, 0, 4.0);
         p2.setCoefficient(2, 0, 3.5);

         //Erzeuge Matrix mit 3 Zeilen und 1 Spalte
         Matrix p3 = new Matrix(3, 1);
         
         /*
          * Festlegung des Inhaltes der zuvor erstellten Matrix,
          * Inhalt sind die x1, x2 und x3-Werte von P3
          */
         p3.setCoefficient(0, 0, 3.5);
         p3.setCoefficient(1, 0, 4.0);
         p3.setCoefficient(2, 0, 3.0);

         //Erzeuge Matrix mit 3 Zeilen und 1 Spalte
         Matrix m1 = new Matrix(3, 1);
        
         /*
          * Festlegung des Inhaltes der zuvor erstellten Matrix,
          * Inhalt ist der x3-Wert von M1
          */
         m1.setCoefficient(2, 0, 2.0);

         //Erzeuge Matrix mit 3 Zeilen und 1 Spalte
         Matrix m2 = new Matrix(3, 1);
         
         /*
          * Festlegung des Inhaltes der zuvor erstellten Matrix,
          * Inhalt ist der x3-Werte von M2
          */
         m2.setCoefficient(2, 0, 2.1);

         //Erzeuge eine Matrix, die als Inhalt die zuvor erstellten Punkte P1 bis M2 übergeben bekommt
         Markise markise = new Markise(p1, p2, p3, m1, m2);

         //Erzeuge Matrix mit 3 Zeilen und 1 Spalte
         Matrix zWerteW = new Matrix(3, 1);
         
         /*
          * Festlegung des Inhaltes der zuvor erzeugten Matrix
          * Inhalt sind die normalisierten Koordinaten von W
          */
         zWerteW.setCoefficient(0, 0, 1./2.);
         zWerteW.setCoefficient(1, 0, 1./4.);
         zWerteW.setCoefficient(2,0, 1./4.);
         
         //Erzeuge Matrix mit 3 Zeilen und 1 Spalte
         Matrix zWerteV = new Matrix(3, 1);
         
         /*
          * Festlegung des Inhaltes der zuvor erzeugten Matrix
          * Inhalt sind die normalisierten Koordinaten von V
          */
         zWerteV.setCoefficient(0, 0, 2./3.);
         zWerteV.setCoefficient(1, 0, 1./3.);
         zWerteV.setCoefficient(2,0, 0);

         //Erzeuge Matrix mit 3 Zeilen und 1 Spalte
         Matrix m3 = new Matrix(3, 1);

         /*
          * Festlegung des Inhaltes der zuvor erzeugten Matrix
          * Inhalt ist (0, 0, zuvor berechnete Höhe von M3)
          */
         m3.setCoefficient(0, 0, 0);
         m3.setCoefficient(1, 0, 0);
         m3.setCoefficient(2, 0, HoehenOperationen.berechneHoeheM3(markise));

         //weist dem Attribut m3 der Markise die Matrix m3 zu
         markise.setM3(m3);

         //Erzeuge eine Variable in der das Ergebnis der Höhenberechnung von W gespeichert werden soll
         double hoeheW = HoehenOperationen.berechneHoehe(zWerteW, markise);
         
         //Erzeuge eine Matrix in der das Ergebnis der Gradientenberechnung von W gespeichert werden soll
         Matrix gradientW = GradientenOperationen.berechneGradient(markise, zWerteW);
         
         //Erzeuge eine Variable in der das Ergebnis der Höhenberechnung von V gespeichert werden soll
         double hoeheV =  HoehenOperationen.berechneHoehe(zWerteV, markise);
         
         //Erzeuge eine Matrix in der das Ergebnis der Gradientenberechnung von V gespeichert werden soll
         Matrix gradientV = GradientenOperationen.berechneGradient(markise, zWerteV);
         
         //Ausgabe der Höhen und Gradienten von W und V auf der Konsole
         System.out.println("Testbeispiel 2:");
         System.out.println("-----------------------------------------------------------");
         System.out.println("Hoehe von W: "+ hoeheW);
         System.out.println("Gradient von W: "+ gradientW.getCoefficient(0,0) +"\t"+ gradientW.getCoefficient(0,1));
         System.out.println("-----------------------------------------------------------");
         System.out.println("Hoehe von V: "+ hoeheV);
         System.out.println("Gradient von V: "+ gradientV.getCoefficient(0,0) +"\t"+ gradientV.getCoefficient(0,1));
         System.out.println("-----------------------------------------------------------");
    }
}
