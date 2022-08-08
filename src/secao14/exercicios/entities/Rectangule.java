package secao14.exercicios.entities;

import secao14.exercicios.entities.enums.Color;

public class Rectangule extends Shape {

    private Double width;
    private Double height;

    public Rectangule() {
        super();
    }

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
    public Double area() {
        return width * height;
    }

    @Override
    public String toString() {
        return "Width: " + String.format("%.2f", width)
                + " Height: " + String.format("%.2f", height)
                + " Area = " + width + " x " + height
                + " = " + String.format("%.2f", area());
    }
}
