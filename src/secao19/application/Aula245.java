package secao19.application;

import secao19.entities.Product;

import java.util.Set;
import java.util.TreeSet;

public class Aula245 {
    public static void main(String[] args) {

        Set<Product> set = new TreeSet<>();

        set.add(new Product("TV", 4000.00));
        set.add(new Product("Notebook", 7800.00));
        set.add(new Product("Tablet", 2000.00));


        //Product product = new Product("Notebook", 7800.00);
        for (Product p : set) {
            System.out.println(p);
        }
    }
}
