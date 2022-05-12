package ru.job4j.ood.lsp.parking;

import java.util.List;

public interface Park {

    boolean park(List<Car> cars);

    boolean accept(Car car);
}
