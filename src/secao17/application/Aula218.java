package secao17.application;

import java.io.File;
import java.util.Scanner;

public class Aula218 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Entre com um caminho de diretório: ");
        String strPath = sc.nextLine();

        File path = new File(strPath);

        // Coletando todas as pastas contidas no caminho informado pelo usuario
        File[] folders = path.listFiles(File::isDirectory);
        System.out.println("FOLDERS:");
        for (File folder : folders) {
            System.out.println(folder);
        }

        // Listagem de ARQUIVOS
        File[] files = path.listFiles(File::isFile);
        System.out.println("FILES:");
        for (File file : files) {
            System.out.println(file);
        }

        // Criando SUBPASTAS
        boolean success = new File(strPath + "\\subdir").mkdir();
        System.out.println("Diretório criado com sucesso? " + success);

        sc.close();
    }
}
