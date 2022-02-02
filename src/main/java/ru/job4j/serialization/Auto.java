package ru.job4j.serialization;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "auto")
@XmlAccessorType(XmlAccessType.FIELD)
public class Auto {

    @XmlAttribute
    private  boolean newOld;

    @XmlAttribute
    private  int yearOfManufacture;
    private Engine engine;

    @XmlElementWrapper(name = "crashTests")
    @XmlElement(name = "crashTest")
    private  String[] crashTests;

    public Auto() { }

    public Auto(boolean newOld, int yearOfManufacture, Engine engine, String[] crashTests) {
        this.newOld = newOld;
        this.yearOfManufacture = yearOfManufacture;
        this.engine = engine;
        this.crashTests = crashTests;
    }

    public boolean isNew() {
        return newOld;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public Engine getEngine() {
        return engine;
    }

    public String[] getCrashTests() {
        return crashTests;
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
