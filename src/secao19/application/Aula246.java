package secao19.application;

import secao19.entities.Access;
import secao19.entities.LogEntry;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Aula246 {
    public static void main(String[] args) throws ParseException {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter file full path: ");
        String path = sc.nextLine();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {

            Set<LogEntry> set = new HashSet<>();

            // Leitura da primeira linha do arquivo
            String line = bufferedReader.readLine();

            while (line != null) {
                String[] fields = line.split(" ");
                String username = fields[0];
                Date moment = Date.from(Instant.parse(fields[1]));
                set.add(new LogEntry(username, moment));

                line = bufferedReader.readLine();
            }

            System.out.println("Total users: " + set.size());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        sc.close();
    }
}
