package secao18.services;

public class BrazilTaxService implements TaxService {

    @Override
    public double tax(double amount) {
        return 0.02 + 0.10 * amount;
    }

}
