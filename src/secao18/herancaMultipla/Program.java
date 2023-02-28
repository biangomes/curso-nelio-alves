package secao18.herancaMultipla;

public class Program {
    public static void main(String[] args) {

        ConcretePrinter p = new ConcretePrinter("1080");
        p.processDoc("My Letter");
        p.print("My Letter");

        System.out.println();

        Scanner s = new Scanner("2004");
        s.processDoc("My Email");
        System.out.println("Scan result: " + s.scan());

        System.out.println();

        ComboDevice cd = new ComboDevice("2081");
        cd.processDoc("My Dissertation");
        cd.print("My Dissertation");
        System.out.println("Scan result: " + cd.scan());
    }
}
