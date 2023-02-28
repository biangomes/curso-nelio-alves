package secao6;

import java.util.Scanner;

public class aula44 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o número com que deseja iniciar: ");
        int num_escolhido = sc.nextInt();
        int soma=0;

        while (num_escolhido != 0) {
            soma += num_escolhido;
            System.out.println("Digite um número: ");
            num_escolhido = sc.nextInt();
        }
        System.out.println("======FIM======");
        System.out.println("Soma dos números anteriores ao zero: " + soma);

    }
}
