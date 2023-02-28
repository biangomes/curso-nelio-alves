package secao14.exercicios.application;

import secao14.exercicios.entities.Circle;
import secao14.exercicios.entities.Rectangule;
import secao14.exercicios.entities.Shape;
import secao14.exercicios.entities.enums.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class MainShape {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        List<Shape> list = new ArrayList<Shape>();

        System.out.println("Enter the number of shapes: ");
        int n = sc.nextInt();

        for (int i=0; i<n; i++) {
            System.out.println("Shape of #" + i + " data: ");
            System.out.print("Rectangule or Circle (r/c)? ");
            char ch = sc.next().charAt(0);
            System.out.print("Color (BLACK/BLUE/RED): ");
            Color color = Color.valueOf(sc.next());

            if (ch == 'r') {
                System.out.println("Enter the width: ");
                double width = sc.nextDouble();
                System.out.println("Enter the height: ");
                double height = sc.nextDouble();

                list.add(new Rectangule(color, width, height));
            } else {
                System.out.print("Enter the radius: ");
                list.add(new Circle(color, sc.nextDouble()));
            }
        }

        for (Shape shape : list) {
            System.out.println(shape);
        }

        sc.close();
    }
}
