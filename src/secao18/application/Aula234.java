package secao18.application;

import secao18.entities.Employee;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Aula234 {
    public static void main(String[] args) {

        //List<String> list = new ArrayList<>();
        List<Employee> list = new ArrayList<>();
        //String path = "files/names.txt";
        String path = "files/funcionarios.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            //String name = br.readLine();
            String employeeCsv = br.readLine();

            while (employeeCsv != null) {
                // Vetor que separa pela vírgula, em que cada posicao eh um campo
                String[] fields = employeeCsv.split(",");
                list.add(new Employee(fields[0], Double.parseDouble(fields[1])));
                employeeCsv = br.readLine();
            }
            Collections.sort(list);

            for (Employee emp : list) {
                System.out.println(emp.getName() + ", " + emp.getSalary());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
