package ru.job4j.serialization;

import java.util.Arrays;

public class Auto {
    private final boolean newOld;
    private final int yearOfManufacture;
    private final Engine engine;
    private final String[] crashTests;

    public Auto(boolean newOld, int yearOfManufacture, Engine engine, String[] crashTests) {
        this.newOld = newOld;
        this.yearOfManufacture = yearOfManufacture;
        this.engine = engine;
        this.crashTests = crashTests;
    }

    @Override
    public String toString() {
        return "Auto{"
                + "newOld=" + newOld
                + ", yearOfManufacture=" + yearOfManufacture
                + ", engine=" + engine
                + ", crashTests=" + Arrays.toString(crashTests)
                + '}';
    }
}
