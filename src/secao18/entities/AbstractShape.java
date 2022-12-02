package secao18.entities;

public abstract class AbstractShape implements Shape {
    
    private Color color;

    public AbstractShape(Color color) {
        this.color = color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    
}
