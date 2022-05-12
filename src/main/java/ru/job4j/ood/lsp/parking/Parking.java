package ru.job4j.ood.lsp.parking;

import java.util.List;

public class Parking implements Park {

    List<Integer> parkPlaces;

    public Parking(List<Integer> parkPlaces) {
        this.parkPlaces = parkPlaces;
    }

    @Override
    public void park(Car car) {

    }

    @Override
    public boolean accept(Car car) {
        return false;
    }
}
