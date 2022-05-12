package ru.job4j.ood.lsp.parking;

public class PassengerCar extends Car {

    private static final int PASS_CAR_SIZE = 1;

    @Override
    public int getSize(Car car) {
        return  PASS_CAR_SIZE;
    }
}
