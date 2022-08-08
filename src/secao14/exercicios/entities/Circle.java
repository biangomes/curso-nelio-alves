package secao14.exercicios.entities;

import secao14.exercicios.entities.enums.Color;

public class Circle extends Shape {

    private Double radius;

    public Circle() {
        super();
    }

    public Circle(Color color, Double radius) {
        super(color);
        this.radius = radius;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    @Override
    public Double area() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public String toString() {
        return "Radius: " + String.format("%.2f", radius)
                + " Area = " + radius + " x " + String.format("%.2f", Math.PI)
                + " = " + String.format("%.2f", area());
    }
}
