package ru.job4j.io;

import java.util.HashMap;
import  java.util.Map;
import java.util.StringJoiner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines()
                    .filter(this::lineFilter)
                    .forEach(this::splitAndPut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        if (!key.matches(".*\\w.*")) {
            throw new IllegalArgumentException("Key contains only symbols or spaces");
        }
        return values.get(key);
    }

    public int size() {
        return values.size();
    }

    private void splitAndPut(String line) {
        String[] pair = line.split("=");
        if (pair.length != 2 || line.startsWith("=") || line.endsWith("=")) {
            throw new UnsupportedOperationException("String contains more then one '='");
        }
        values.put(pair[0], pair[1]);
    }

    private boolean lineFilter(String line) {
        return !line.startsWith("#") && line.contains("=");
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}
