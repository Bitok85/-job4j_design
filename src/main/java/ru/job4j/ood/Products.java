package ru.job4j.ood;

import java.util.Comparator;

public class Products implements ProductMerch {
    /**
     * Класс по работе с продуктами инициализирует объект. Не исключено, что для разной работе с продуктами
     * потребуются свои специфические компараторы, которые есть смысл описать в отдельных классах.
     */
    @Override
    public void merch() {
        Comparator comparator = Comparator.naturalOrder();
    }
}
