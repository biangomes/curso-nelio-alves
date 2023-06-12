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

        // Princípio get/put - Covariância

        List<Integer> intList = new ArrayList<Integer>();
        intList.add(10);
        intList.add(5);

        List<? extends Number> list = intList;      // cópia de intList com um supertipo

        Number x = list.get(0);
        System.out.println("Lista de inteiros: " + intList);
        System.out.println("get: " + x);

        list.add(20);
        System.out.println("put: " + list);

    }

    public static double totalArea(List<? extends Shape> list) {
        double sum = 0.0;

        for (Shape s : list) {
            sum += s.area();
        }

        return sum;
    }
}
