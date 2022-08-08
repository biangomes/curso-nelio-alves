package secao14.exercicios.entities;

public class Rectangule extends Shape {

    private Double largura;
    private Double altura;

    public Rectangule() {
        super();
    }

    public Double getLado() {
        return largura;
    }

    public void setLado(Double largura) {
        this.largura = largura;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    @Override
    public Double area() {
        return largura * altura;
    }
}
