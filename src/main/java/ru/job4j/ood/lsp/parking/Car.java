package ru.job4j.ood.lsp.parking;

public abstract class Car {

private int size;

    public Car(int size) {
        this.size = size;
    }

    public abstract void sizeCheck(Car car);

}
