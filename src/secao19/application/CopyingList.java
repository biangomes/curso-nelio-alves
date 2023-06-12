package secao19.application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CopyingList {
    public static void main(String[] args) {

        List<Integer> myInts = Arrays.asList(1, 2, 3, 4);
        List<Double> myDoubles = Arrays.asList(3.14, 9.98);
        List<Object> myObjs = new ArrayList<>();

        copy(myInts, myObjs);
        printList(myObjs);
        copy(myDoubles, myObjs);
        printList(myObjs);
    }

    public static void copy(List<? extends Number> source, List<? super Number> destiny) {
        for (Number number : source) {
            destiny.add(number);
        }
    }

    public static void printList(List<?> anyList) {
        for (Object obj : anyList) {
            System.out.print(obj + " ");
        }
        System.out.println();
    }
}
