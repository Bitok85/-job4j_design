package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    public void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("args data line is empty");
        }
        for (int i = 0; i < args.length; i++) {
            args[i] = ArgsName.stringParse(args[i]);
            String key = args[i].split("=")[0];
            String value = args[i].split("=")[1];
            values.put(key, value);
        }
    }

    private static String stringParse(String str) {
        if (!str.contains("=")
                || !(str.split("=").length == 2)
                || !str.startsWith("-")
        ) {
            throw new IllegalArgumentException("Invalid jvm key or value");
        }
        StringBuilder rsl = new StringBuilder();
        rsl.append(str);
        rsl.deleteCharAt(0);
        return rsl.toString();
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

}
