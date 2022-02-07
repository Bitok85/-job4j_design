package ru.job4j.io.search;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainSearch {
    private static final Logger LOG = LoggerFactory.getLogger(MainSearch.class.getName());
    private static List<Path> searchRsl = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        validation(argsName, args);
        searchAction(argsName.get("t"), argsName.get("n"), argsName.get("d"));
        logWrite(argsName.get("o"));
    }

    private static void validation(ArgsName argsNames, String[] params) {
        if (params.length != 4) {
            LOG.error("Input all parameters");
        }
        if (!Files.exists(Paths.get(argsNames.get("d")))) {
            LOG.error("Directory doesn't exist");
        }
        if (!"name".equals(argsNames.get("t"))
                && !"mask".equals(argsNames.get("t"))
                && !"regex".equals(argsNames.get("t"))
        ) {
            LOG.error("Input correct type of search (-name, -mask, -regex)");
        }
    }

    private static String maskToReg(String name) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String el : name.split("")) {
            if (el.equals("?")) {
                stringBuilder.append("[\\w\\-]{1}+");
            } else if (el.equals("*")) {
                stringBuilder.append("[\\w\\-]+");
            } else {
                stringBuilder.append(el);
            }
        }
        if (stringBuilder.charAt(0) == '+') {
            stringBuilder.deleteCharAt(0);
        }
        return stringBuilder.toString();
    }

    private static void searchAction(String type, String name, String dir) throws IOException {
        Path path = Paths.get(dir);
        if (type.equals("name")) {
            searchRsl.addAll(Search.search(path, p -> p.getFileName().toString().equals(name)));
        } else if (type.equals("mask")) {
            Pattern pattern = Pattern.compile(maskToReg(name));
            searchRsl.addAll(Search.search(path, p -> {
                Matcher matcher = pattern.matcher(p.getFileName().toString());
                return matcher.matches();
            }));
        } else {
            Pattern pattern = Pattern.compile(name);
            searchRsl.addAll(Search.search(path, p -> {
                Matcher matcher = pattern.matcher(p.getFileName().toString());
                return matcher.matches();
            }));
        }
    }

    private static void logWrite(String out) throws IOException {
        searchRsl.forEach(p -> {
            try {
                Files.write(Paths.get(out), (p.toString() + System.lineSeparator()).getBytes(),
                        StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            } catch (IOException e) {
                LOG.error("Exception", e);
            }
        });
    }
}
