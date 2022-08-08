package secao14.heranca.entities;

public class BusinessAccount extends Account {

    private Double loanLimit;

    public BusinessAccount() {
        super();
    }

    public BusinessAccount(Integer number, String holder, Double balance, Double loanLimit) {
        super(number, holder, balance);
        this.loanLimit = loanLimit;
    }

    public Double getLoanLimit() {
        return loanLimit;
    }

    public void setLoanLimit(Double loanLimit) {
        this.loanLimit = loanLimit;
    }

    @Override
    public void withdraw(double amount) {
        super.withdraw(amount);     // reutiliza a operaçao withdraw da classe-mae
        balance -= 2.0;             // e retira mais R$2,00
    }

    public void loan(double amount) {
        if (loanLimit >= amount) {
            balance = balance + amount - 10.0;
        }
    }
}