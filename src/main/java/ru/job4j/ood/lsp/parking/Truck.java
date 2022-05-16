package ru.job4j.ood.lsp.parking;

public class Truck extends Car {

    private int size;

    public Truck(int size) {
        if (size < 2) {
            throw new IllegalArgumentException("Truck size must be > 1");
        }
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }

}
