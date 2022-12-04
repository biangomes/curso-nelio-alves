package secao18.herancaMultipla;

public class Program {
    public static void main(String[] args) {

        Printer p = new Printer("1080");
        p.processDoc("My Letter");
        p.print("My Letter");

        Scanner s = new Scanner("2004");
        s.processDoc("My Email");
        System.out.println("Scan result: " + s.scan());
    }
}
