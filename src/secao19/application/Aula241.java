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
        System.out.println("====================");
        System.out.println("Princípio get/put - Variância");

        List<Integer> intList = new ArrayList<Integer>();
        intList.add(10);
        intList.add(5);

        List<? extends Number> list = intList;      // cópia de intList com um supertipo

        Number x = list.get(0);
        System.out.println("Lista de inteiros: " + intList);
        System.out.println("get: " + x);

//        list.add(20);
//        System.out.println("put: " + list);

        // Princípio get/put - Contravariância
        System.out.println("====================");
        System.out.println("Princípio get/put - Contravariância");

        List<Object> myObjs = new ArrayList<Object>();
        myObjs.add("Bea");
        myObjs.add("Ana");
        System.out.println("Lista de Objects: " + myObjs);

        List<? super Number> myNums = myObjs;
        myNums.add(10);
        myNums.add(3.14);
        System.out.println("Lista de super Number: " + myNums);

        Number y = myNums.get(0);
        System.out.println("get: " + y);

    }

    public static double totalArea(List<? extends Shape> list) {
        double sum = 0.0;

        for (Shape s : list) {
            sum += s.area();
        }

        return sum;
    }
}
