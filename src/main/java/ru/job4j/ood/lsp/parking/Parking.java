package ru.job4j.ood.lsp.parking;

import java.util.List;

public class Parking implements Park {

    private List<Integer> parkCells;

    int passCarCells;
    int truckCells;

    public Parking(int passCarCells, int truckCells) {
        this.passCarCells = passCarCells;
        this.truckCells = truckCells;
        parkCells = List.of(passCarCells, truckCells);
    }

    @Override
    public boolean park(List<Car> cars) {
        return false;
    }

    @Override
    public boolean accept(Car car) {
        return false;
    }

    public List<Integer> getParkCells() {
        return parkCells;
    }
}
