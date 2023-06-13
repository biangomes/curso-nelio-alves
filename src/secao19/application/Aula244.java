package secao19.application;

import secao19.entities.Product;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class Aula244 {
    public static void main(String[] args) {

        Set<Product> set = new HashSet<>();

        set.add(new Product("TV", 4000.00));
        set.add(new Product("Notebook", 7800.00));
        set.add(new Product("Tablet", 2000.00));


        Product product = new Product("Notebook", 7800.00);

        System.out.println(set.contains(product));
    }
}
