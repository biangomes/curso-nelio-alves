package secao15.exercicios;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ArquivoVazio {
    public static void main(String[] args) {

        System.out.println("Método lerArquivo() iniciando");
        lerArquivo();
    }


    // Criando um metodo estatico para ler arquivos
    public static void lerArquivo() {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Caminho do arquivo .txt com duas barras invertidas.\nExemplo: C\\Users\\ \n");
            String caminhoDoArquivo = sc.next();

            File file = new File(caminhoDoArquivo);
            sc = new Scanner(file);

            // Logica para validar a extensao do arquivo
            String fileExtension = file.toString();
            int index = fileExtension.lastIndexOf('.');
            if (index > 0) {
                String extension = fileExtension.substring(index+1);
                //System.out.println("Extensão: " + extension);
            }

            // Leitura das linhas do arquivo
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
        }

        // TODO: fazer a logica para validar a extensao do arquivo
        catch (FileNotFoundException e){
            System.out.println("Arquivo não encontrado: " + e.getMessage());
         }
        catch (IllegalArgumentException e) {
            System.out.println("Illegal Argument Exception: " + e.getMessage());
        }
        finally {
            if (sc != null) {
                System.out.println("Fim do método lerArquivo()");
                sc.close();
            }
        }


        //sc.close();
    }
}