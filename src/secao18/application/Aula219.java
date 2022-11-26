package secao18.application;

import secao18.entities.Circle;
import secao18.entities.Rectangule;
import secao18.entities.Shape;

public class Aula219 {
    public static void main(String[] args) {

        Shape c1 = new Circle(2.0);
        Shape c2 = new Rectangule(4.0, 4.0);

        System.out.println(c1.toString());
        System.out.println(c2.toString());


    }
}
