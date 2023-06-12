package secao19.application;

import java.util.HashSet;
import java.util.Set;

public class Aula243 {
    public static void main(String[] args) {

        Set<String> set = new HashSet<>();

        set.add("TV");
        set.add("Tablet");
        set.add("iPhone 13");

        System.out.println(set.contains("iPhone 13"));

        for (String p : set) {
            System.out.println(p);
        }
    }
}
