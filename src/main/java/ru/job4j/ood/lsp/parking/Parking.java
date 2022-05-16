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
        boolean result = false;
        if (car.getSize() == PassengerCar.PASS_CAR_SIZE) {
            result = passCarPark(car);
        } else {
            result = truckPark(car);
        }
        return result;
    }

    @Override
    public boolean accept(Car car) {
        return car.getSize() >= PassengerCar.PASS_CAR_SIZE;
    }

    public List<Car> getParkCells() {
        return new ArrayList<>(parkCells);
    }

    private boolean passCarPark(Car car) {
        boolean result = false;
        if (passCarCells > 0) {
            parkCells.add(car);
            passCarCells--;
            result = true;
        }
        return result;
    }

    private boolean truckPark(Car car) {
        boolean result = false;
        if (truckCells > 0) {
            parkCells.add(car);
            truckCells--;
            result = true;
        } else if (passCarCells >= car.getSize()) {
            parkCells.add(car);
            passCarCells -= car.getSize();
            result = true;
        }
        return result;
    }
}
