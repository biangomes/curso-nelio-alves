package secao18.services;

public class UsaTaxService implements TaxService {

    @Override
    public double tax(double amount) {
        return 0.005 + 0.02 * amount;
    }
}
