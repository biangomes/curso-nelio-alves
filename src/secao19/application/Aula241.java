package secao19.application;

import secao19.entities.Circle;
import secao19.entities.Rectangle;
import secao19.entities.Shape;

import java.util.ArrayList;
import java.util.List;

public class Aula241 {
    public static void main(String[] args) {

        List<Shape> shapes = new ArrayList<>();
        shapes.add(new Rectangle(3.0, 2.0));
        shapes.add(new Circle(3.0));

        System.out.println("Total area: " + totalArea(shapes));

    }

    public static double totalArea(List<? extends Shape> list) {
        double sum = 0.0;

        for (Shape s : list) {
            sum += s.area();
        }

        return sum;
    }
}
