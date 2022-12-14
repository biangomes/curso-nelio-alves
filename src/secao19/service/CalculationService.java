package secao19.service;

import java.util.List;

public class CalculationService {

    public <T extends Comparable<T>> T max(List<T> list) {

        // Logica defensiva
        if (list.isEmpty()) {
            throw new IllegalStateException("List can't be empty");
        }

        T max = list.get(0);
        for (T item : list) {
            if (item.compareTo(max) > 0) {
                max = item;
            }
        }

        return max;
    }
}