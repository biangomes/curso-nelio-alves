package secao17.exercicio.application;

import secao17.exercicio.entities.Produto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        //String nome; double precoUnitario; int qtd;
        List<Produto> lista = new ArrayList<>();

        System.out.println("Digite o caminho do arquivo CSV: ");
        String pathCsvIn = sc.nextLine();

        File fileCsv = new File(pathCsvIn);
        String pathFileCsv = fileCsv.getParent();

        // Cria a pasta
        boolean success = new File(pathFileCsv + "\\out").mkdir();

        String targetFileCsv = pathCsvIn + "\\out\\summary.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(pathFileCsv))) {

            String itemCsv = br.readLine();

            while (itemCsv != null) {
                System.out.println(itemCsv);
                itemCsv = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }

        sc.close();
    }
}
