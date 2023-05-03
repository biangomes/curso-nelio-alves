package secao19.application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Aula240 {

    public static void main(String[] args) {
//        List<Object> myObj = new ArrayList<Object>();
//        List<Integer> myInt = new ArrayList<Integer>();
//
//        System.out.println(myObj == myInt);

//        List<?> myObj = new ArrayList<Object>();
//        List<Integer> myInt = new ArrayList<Integer>();
//
//        System.out.println(myObj = myInt);

        List<Integer> myInts = Arrays.asList(2, 4, 6);
        printList(myInts);

        List<String> myStrs = Arrays.asList("Bea", "&", "Flavinha");
        printList(myStrs);
    }

    public static void printList(List<?> list) {
        for (Object obj : list) {
            System.out.println(obj);
        }
    }
}
