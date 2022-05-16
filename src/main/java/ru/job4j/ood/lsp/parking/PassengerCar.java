package ru.job4j.ood.lsp.parking;

public class PassengerCar extends Car {

    public static final int PASS_CAR_SIZE = 1;

    @Override
    public int getSize() {
        return  PASS_CAR_SIZE;
    }
}
