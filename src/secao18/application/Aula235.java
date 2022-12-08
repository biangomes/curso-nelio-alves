package secao18.application;

import secao18.services.BrazilInterestService;
import secao18.services.InterestService;
import secao18.services.UsaInterestService;

import java.util.Locale;
import java.util.Scanner;

public class Aula235 {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        InterestService bis = new BrazilInterestService(2.0);
        System.out.print("Amount: ");
        double amount = sc.nextDouble();
        System.out.print("How many months: ");
        int months = sc.nextInt();

        System.out.printf("Brazil Payment: R$ %.2f", bis.payment(amount, months));

        InterestService uis = new UsaInterestService(1.0);
        System.out.printf("%nUSA Payment: $ %.2f", uis.payment(amount, months));


        sc.close();
    }
}
