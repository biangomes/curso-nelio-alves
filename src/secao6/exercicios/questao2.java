package secao6.exercicios;

import java.util.Scanner;

public class questao2 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("coordenadas x e y");
        int x = sc.nextInt();
        int y = sc.nextInt();

        while (x != 0 && y != 0) {
            if (x > 0 && y > 0) {
                System.out.println("primeiro quadrante");
            } else if (x > 0 && y < 0) {
                System.out.println("segundo quadrante");
            } else if (x < 0 && y < 0) {
                System.out.println("terceiro quadrante");
            } else if (x < 0 && y > 0) {
                System.out.println("quarto quadrante");
            }

            x = sc.nextInt();
            y = sc.nextInt();
        }
    }
}
