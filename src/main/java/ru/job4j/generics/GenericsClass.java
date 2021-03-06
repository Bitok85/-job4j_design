package ru.job4j.generics;

public class GenericsClass<K, V> {
    private K key;
    private V value;

    public GenericsClass(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "GenericsClass{"
                + "key=" + key
                + ", value=" + value
                + '}';
    }

    public static void main(String[] args) {
        GenericsClass<String, String> gen = new GenericsClass<>("First key", "First value");
        System.out.println(gen);
        GenericsClass<Integer, String> gen2 = new GenericsClass<>(123, "Second value");
        System.out.println(gen2);
    }
}
