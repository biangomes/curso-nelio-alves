package secao6.exercicios;

import java.util.Scanner;

public class questao1 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int senha = 2002;

        System.out.println("Digite a senha: ");
        int senhaDigitada = sc.nextInt();

        while (senhaDigitada != senha) {
            System.out.println("Digite NOVAMENTE a senha digitada: ");
            senhaDigitada = sc.nextInt();
        }
    }
}
