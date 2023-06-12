package secao19.application;

import java.util.ArrayList;
import java.util.List;

public class Contravariancia {
    public static void main(String[] args) {

        // Princípio get/put - Contravariância
        System.out.println("====================");
        System.out.println("Princípio get/put - Contravariância");

        List<Object> myObjs = new ArrayList<Object>();
        myObjs.add("Bea");
        myObjs.add("Ana");
        System.out.println("Lista de Objects: " + myObjs);

        List<? super Number> myNums = myObjs;
        myNums.add(10);
        myNums.add(3.14);
        System.out.println("Lista de super Number: " + myNums);

//        Number y = myNums.get(0);
//        System.out.println("get: " + y);
    }
}
