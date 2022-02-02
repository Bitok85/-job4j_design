package ru.job4j.serialization;

import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "engine")

public class Engine {

    @XmlAttribute
    private String type;

    @XmlAttribute
    private float capacity;

    public Engine() { }

    public Engine(String type, float capacity) {
        this.type = type;
        this.capacity = capacity;
    }

    public String getType() {
        return type;
    }

    public float getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "Engine{"
                + "type='" + type + '\''
                + ", capacity=" + capacity
                + '}';
    }
}
