package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class Parking implements Park {

    private List<Car> parkCells;

    private int passCarCells;
    private int truckCells;

    public Parking(int passCarCells, int truckCells) {
        this.passCarCells = passCarCells;
        this.truckCells = truckCells;
        parkCells = new ArrayList<>(passCarCells + truckCells);
    }

    @Override
    public boolean park(Car car) {
        return false;
    }

    @Override
    public boolean accept(Car car) {
        return false;
    }

    public List<Car> getParkCells() {
        return parkCells;
    }
}
