package secao17.application;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        File file = new File("c:\\in.txt");
        Scanner sc = null;

        try {
            sc = new Scanner(file);
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            // Testa se o sc foi inicializado com valor diferente de null
            if (sc!=null) {
                // Boa prática: finalizar o uso do recurso no bloco finally
                sc.close();
            }
        }
    }
}
