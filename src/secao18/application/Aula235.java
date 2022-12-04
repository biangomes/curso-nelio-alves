package secao18.application;

import secao18.services.BrazilInterestService;

import java.util.Locale;
import java.util.Scanner;

public class Aula235 {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        BrazilInterestService bis = new BrazilInterestService(2.0);
        System.out.print("Amount: ");
        double amount = sc.nextDouble();
        System.out.print("How many months: ");
        int months = sc.nextInt();

        System.out.printf("Payment: R$ %.2f", bis.payment(amount, months));
    }
}
