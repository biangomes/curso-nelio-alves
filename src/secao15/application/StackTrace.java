package secao15.application;

import java.util.InputMismatchException;
import java.util.Scanner;

public class StackTrace {
    public static void main(String[] args) {
        //System.out.println("*** METHOD STARTS *** ");

        method1();
        System.out.println("End of program");
    }

    public static void method1() {
        System.out.println("*** METHOD1 STARTS *** ");
        method2();
        System.out.println("*** METHOD1 ENDS ***");
    }

    public static void method2() {
        System.out.println("*** METHOD2 STARTS *** ");
        Scanner sc = new Scanner(System.in);

        try {
            String[] vect = sc.nextLine().split(" ");
            int posicao = sc.nextInt();
            System.out.println(vect[posicao]);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Posição inválida!");
            e.printStackTrace();
            sc.next();
        }
        catch (InputMismatchException e) {
            System.out.println("Input error!");
        }

        sc.close();

        System.out.println("*** METHOD2 ENDS ***");
    }
}
