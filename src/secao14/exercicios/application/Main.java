package secao14.exercicios.application;

import secao14.exercicios.entities.Employee;
import secao14.exercicios.entities.OutsourcedEmployee;

import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.println("Number of employees: ");
        int n = sc.nextInt();

        //Employee[] employees = new Employee[n];
        List<Employee> list = new ArrayList<Employee>();


        // Armazenando os dados em uma lista
        for (int i=0; i<n; i++) {
            System.out.printf("#### FUNCIONÁRIO %d ####", i);
            System.out.println("");
            System.out.print("Outsourced (y/n)? ");
            char ch = sc.next().charAt(0);
            System.out.println("Name: ");
            sc.nextLine();      // consumir a quebra de linha
            String name = sc.nextLine();
            System.out.println("Hours: ");
            int hours = sc.nextInt();
            System.out.println("Value per hour: ");
            double valuePerHour = sc.nextDouble();


            if (ch == 'y') {
                System.out.println("Additional charge: ");
                double additionalCharge = sc.nextDouble();
                Employee emp = new OutsourcedEmployee(name, hours, valuePerHour, additionalCharge);
                list.add(emp);
            } else {
                Employee emp = new Employee(name, hours, valuePerHour);
                list.add(emp);
            }

        }


        // Mostrando os dados da lista
        System.out.println("#### PAYMENTS ####");
        for (Employee emp : list) {
            System.out.println(emp.getName() + " - R$ " + String.format("%.2f", emp.payment()));
        }


        sc.close();
    }
}
