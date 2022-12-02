package secao18.application;

import secao18.entities.AbstractShape;
import secao18.entities.Circle;
import secao18.entities.Color;
import secao18.entities.Rectangule;

public class Aula219 {
    public static void main(String[] args) {

        AbstractShape c1 = new Circle(Color.BLACK, 2.0);
        AbstractShape c2 = new Rectangule(Color.WHITE, 3.0, 4.0);

        System.out.println("Circle color: " + c1.getColor());
        System.out.println("Circle area: " + String.format("%.3f", c1.area()));
        System.out.println("Rectangule color: " + c2.getColor());
        System.out.println("Rectangule area: " + String.format("%.3f", c2.area()));
    }
}
