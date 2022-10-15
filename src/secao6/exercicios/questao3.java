package secao6.exercicios;

import java.util.Scanner;
public class questao3 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int somaAlcool=0; int somaGasolina=0; int somaDiesel=0;

        int tipoDeCombustivel = sc.nextInt();

        while (tipoDeCombustivel != 4) {
            tipoDeCombustivel = sc.nextInt();

            if (tipoDeCombustivel == 1) {
                somaAlcool++;
            } else if (tipoDeCombustivel == 2) {
                somaGasolina++;
            } else if (tipoDeCombustivel == 3) {
                somaDiesel++;
            } else if (tipoDeCombustivel == 4) {
                break;
            }
        }

        System.out.println("MUITO OBRIGADO");
        System.out.println("Alcool: " + somaAlcool);
        System.out.println("Gasolina: " + somaGasolina);
        System.out.println("Diesel: " + somaDiesel);

    }
}
