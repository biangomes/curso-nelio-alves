package secao19.service;

import java.util.ArrayList;
import java.util.List;

public class PrintService {
    private List<Object> lista = new ArrayList<>();

    public void addValue(Object value) {
        lista.add(value);
    }

    public Object first() {
        if (lista.isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        return lista.get(0);
    }

    public void print() {
        System.out.print("[");

        if (!lista.isEmpty()) {
            System.out.print(lista.get(0));
        }
        for (int i=1; i<lista.size(); i++) {
            System.out.println(", " + lista.get(i));
        }

    }
}
