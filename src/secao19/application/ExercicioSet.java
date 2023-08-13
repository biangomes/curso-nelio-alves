package secao19.application;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

// Aula 248
public class ExercicioSet {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Set<Integer> studentsOfA = new HashSet<>();
        Set<Integer> studentsOfB = new HashSet<>();
        Set<Integer> studentsOfC = new HashSet<>();


        System.out.print("How many students for class A: ");
        int n = sc.nextInt();
        for (int i=0; i<=n; i++) {
            int number = sc.nextInt();
            studentsOfA.add(number);
        }


        System.out.print("How many students for class B: ");
        n = sc.nextInt();
        for (int i=0; i<=n; i++) {
            int number = sc.nextInt();
            studentsOfB.add(number);
        }


        System.out.print("How many students for class C: ");
        n = sc.nextInt();
        for (int i=0; i<=n; i++) {
            int number = sc.nextInt();
            studentsOfC.add(number);
        }


        Set<Integer> total = new HashSet<>(studentsOfA);
        total.addAll(studentsOfB);
        total.addAll(studentsOfC);

        System.out.println("Total students: " + total.size());
        sc.close();
    }
}
