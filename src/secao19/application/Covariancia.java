package secao19.application;

import java.util.ArrayList;
import java.util.List;

public class Covariancia {
    public static void main(String[] args) {

        // Princípio get/put - Covariância
        System.out.println("====================");
        System.out.println("Princípio get/put - Variância");

        List<Integer> intList = new ArrayList<Integer>();
        intList.add(10);
        intList.add(5);

        List<? extends Number> list = intList;      // cópia de intList com um supertipo

        Number x = list.get(0);
        System.out.println("Lista de inteiros: " + intList);
        System.out.println("get: " + x);

//        list.add(20);
//        System.out.println("put: " + list);
    }
}
