package ru.job4j.serialization;

public class Engine {
    private final String type;
    private final float capacity;

    public Engine(String type, float capacity) {
        this.type = type;
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Engine{"
                + "type='" + type + '\''
                + ", capacity=" + capacity
                + '}';
    }
}
