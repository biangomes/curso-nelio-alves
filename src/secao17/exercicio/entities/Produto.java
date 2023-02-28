package secao17.exercicio.entities;

public class Produto {
    private String nome;
    private Double precoUnitario;
    private Integer qtd;

    public Produto() {}
    public Produto(String nome, double precoUnitario, int qtd) {
        this.nome=nome;
        this.precoUnitario=precoUnitario;
        this.qtd=qtd;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Integer getQtd() {
        return qtd;
    }

    public void setQtd(Integer qtd) {
        this.qtd = qtd;
    }

    public double total() {
        return this.precoUnitario * this.qtd;
    }
}
