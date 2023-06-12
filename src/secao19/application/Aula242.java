package secao19.application;

public class Aula242 {
    public static void main(String[] args) {

        String nomeDaMae = "Rita";
        String nomeDoPai = "Sérgio";

        boolean ehIgual = nomeDaMae.equals(nomeDoPai);
        System.out.println(ehIgual);


        String nomeDoIrmao = "Tadeu";
        String nomeDaIrma = "Cássia";
        System.out.println(nomeDoIrmao.hashCode() + "\n" + nomeDaIrma.hashCode());

        boolean hashCodeEhOMesmo = (nomeDaIrma.hashCode()) == (nomeDoIrmao.hashCode());
        System.out.println(hashCodeEhOMesmo);
    }
}
