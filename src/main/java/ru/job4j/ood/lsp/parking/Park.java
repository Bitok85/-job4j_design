package ru.job4j.ood.lsp.parking;

import java.util.List;

public interface Park {

    int PASS_CAR_SIZE = 1;

    boolean park(Car car);

    boolean accept(Car car);
}
