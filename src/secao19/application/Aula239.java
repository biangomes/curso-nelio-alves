package secao19.application;

import secao19.entities.Product;
import secao19.service.CalculationService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Aula239 {
    public static void main(String[] args) {

        //List<Integer> list = new ArrayList<>();
        List<Product> productList = new ArrayList<>();

        String path = ".//files//aula239.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();
            while (line != null) {
                /* Adiciona em list
                list.add(Integer.parseInt(line)); */

                // vetor de string, baseado no caracter "vírgula". recorta as "colunas"
                String[] fields = line.split(",");
                productList.add(new Product(fields[0], Double.parseDouble(fields[1])));
                line = br.readLine();
            }

            //Integer x = CalculationService.max(list);
            Product p = CalculationService.max(productList);
            System.out.printf("Most expensive: " + p);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
