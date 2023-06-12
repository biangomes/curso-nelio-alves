package secao19.application;

import java.util.Set;
import java.util.TreeSet;

public class Aula243 {
    public static void main(String[] args) {

        Set<String> set = new TreeSet<>();

        set.add("TV");
        set.add("Tablet");
        set.add("iPhone 13");
        set.add("Xbox Series S|X");
        set.add("Playstation 5");

        System.out.println(set.contains("iPhone 13"));

        for (String p : set) {
            System.out.println(p);
        }
    }
}
