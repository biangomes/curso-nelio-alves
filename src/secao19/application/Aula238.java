package secao19.application;

import secao19.service.PrintService;

import java.util.Scanner;

public class Aula238 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        //PrintService ps = new PrintService();
        PrintService<Integer> psInteger = new PrintService<>();

        System.out.print("How many values does list have: ");
        int n = sc.nextInt();

        //ps.addValue("Maria");

        for (int i=0; i<n; i++) {
            int value = sc.nextInt();
            psInteger.addValue(value);
        }

        psInteger.print();
        System.out.println("First: " + psInteger.first());

        sc.close();
    }
}
