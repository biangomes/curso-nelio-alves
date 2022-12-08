package secao19.application;

import secao19.service.PrintService;

import java.util.Scanner;

public class Aula238 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        PrintService ps = new PrintService();

        System.out.print("How many values: ");
        int n = sc.nextInt();

        for (int i=0; i<n; i++) {
            String value = sc.next();
            ps.addValue(value);
        }

        ps.print();
        System.out.println("First: " + ps.first());

        sc.close();
    }
}
