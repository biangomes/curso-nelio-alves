package secao7;

import java.util.Scanner;

public class funcoes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("entre com tres numeros: ");
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();

        int higher = max(a, b, c);

//        if (a > b && a > c) {
//            System.out.println("maior número = " + a);
//        } else if (b > c) {
//            System.out.println("maior número = " + b);
//        } else {
//            System.out.println("maior número = " + c);
//        }

        showResult(higher);
        sc.close();
    }

    // Função que retorna o maior de três números
    public static int max(int a, int b, int c) {
        int maior=0;

        if (a > b && a > c) {
            maior=a;
        } else if (b > c) {
            maior = b;
        } else {
            maior = c;
        }

        return maior;
    }

    public static void showResult(int value) {
        System.out.println("O maior valor é: " + value);
    }
}
