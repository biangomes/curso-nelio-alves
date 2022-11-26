package secao18.entities;

public class Circle extends Shape {
    private Double radius;

    public static final double PI=3.14;

    public Circle(Double radius) {
        this.radius=radius;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    @Override
    public Double area() {
        return PI * Math.pow(this.radius, 2);
    }

    @Override
    public String toString() {
        return "Área do círculo: " + String.format("%.2f", area());
    }
}
