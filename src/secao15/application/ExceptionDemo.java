package secao15.application;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            String[] vect = sc.nextLine().split(" ");
            int posicao = sc.nextInt();
            System.out.println(vect[posicao]);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Posição inválida!");
        }
        catch (InputMismatchException e) {
            System.out.println("Input error!");
        }
        sc.close();
    }
}
