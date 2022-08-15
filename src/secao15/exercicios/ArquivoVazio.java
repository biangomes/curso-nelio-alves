import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

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

            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
        }

        // TODO: fazer a logica para validar a extensao do arquivo

        /**catch {
            //logica para verificar a extensao do arquivo
            break
        } **/
        
        finally {
            if (sc != null) {
                System.out.println("Fim do método lerArquivo()");
                sc.close();
            }
        }


        //sc.close();
        }
    }