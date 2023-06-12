package secao19.application;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class Aula243 {
    public static void main(String[] args) {

        Set<String> set = new LinkedHashSet<>();

        set.add("TV");
        set.add("Tablet");
        set.add("iPhone 13");
        set.add("Xbox Series S|X");
        set.add("Playstation 5");
        set.add("Alexa");

        set.removeIf(x -> x.length() < 3);

        for (String p : set) {
            System.out.println(p);
        }
    }
}
