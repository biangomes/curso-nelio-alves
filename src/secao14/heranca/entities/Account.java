package secao14.heranca.entities;

public abstract class Account {

    private Integer number;
    private String holder;
    /*
    private Double balance;

    *   Se o atributo balance estiver protected, ele nao será acessado pela subclasse BusinessAccount.
    *   Desta forma, o modificador de acesso dele será protected, pois ele precisa estar seguro também.

     */
    protected Double balance;

    public Account() {}

    public Account(Integer number, String holder, Double balance) {
        this.number = number;
        this.holder = holder;
        this.balance = balance;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public Double getBalance() {
        return balance;
    }


    public void withdraw(double amount) {
        balance = balance - amount - 5.0;       // taxa de R$5,00 para cada saque
    }

    public void deposit(double amount) {
        balance = balance + amount;
    }
}
