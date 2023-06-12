package secao19.application;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class Aula243 {
    public static void main(String[] args) {

        Set<Integer> a = new TreeSet<>(Arrays.asList(0, 2, 4, 6, 8, 10));
        Set<Integer> b = new TreeSet<>(Arrays.asList(1, 3, 5, 7, 9, 10));

        // União
        Set<Integer> c = new TreeSet<>(a);      // cria uma cópia do conjunto a
        c.addAll(b);        // uniao do conjunto c com b
        System.out.println(c);      // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

        // Intersecção
        Set<Integer> d = new TreeSet<>(a);
        d.retainAll(b);     // interseccao do conjunto d com b
        System.out.println(d);      // [10]

        // Diferença
        Set<Integer> e = new TreeSet<>(a);
        e.removeAll(b);     // diferença do conjunto e com b
        System.out.println(e);      // [0, 2, 4, 6, 8]
    }
}
