package secao18.entities;

public class Circle extends AbstractShape {
    private Double radius;

    public static final double PI=3.14;

    public Circle(Color color, Double radius) {
        super(color);
        this.radius=radius;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    @Override
    public double area() {
        return PI * Math.pow(this.radius, 2);
    }

    @Override
    public String toString() {
        return "Área do círculo: " + String.format("%.2f", area());
    }
}
