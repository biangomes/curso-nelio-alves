package secao18.entities;

public class Rectangule extends AbstractShape {

    private Double width;
    private Double height;

    public Rectangule(Color color, Double width, Double height) {
        super(color);
        this.width = width;
        this.height = height;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    @Override
    public double area() {
        return this.height * Math.pow(this.width, 2);
    }

    @Override
    public String toString() {
        return "Área do retângulo: " + String.format("%.2f", area());
    }
}
