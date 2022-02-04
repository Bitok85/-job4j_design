package ru.job4j.io;

import ru.job4j.io.search.ArgsName;
import ru.job4j.io.search.Search;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    private static String sourceDir;
    private static String exclude;
    private static String targetDir;

    private static List<Path> pathList = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        sourceDir = argsName.get("d");
        exclude = argsName.get("e");
        targetDir = argsName.get("o");
        validation(args);
        searchExceptions();
        packFiles(pathList, Paths.get(targetDir + ".zip"));
    }

    public static void packFiles(List<Path> pathList, Path target) throws IOException {
        Path zipDir = Files.createFile(target);
        Path pathSource = Paths.get(sourceDir);
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(zipDir))) {
            pathList.stream()
                    .filter(path -> !Files.isDirectory(path))
                    .forEach(path -> {
                        ZipEntry zipEntry = new ZipEntry(pathSource.relativize(path).toString());
                        try {
                            zipOutputStream.putNextEntry(zipEntry);
                            Files.copy(path, zipOutputStream);
                            zipOutputStream.closeEntry();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        }
    }

    private static void searchExceptions() throws IOException {
        Path start = Paths.get(sourceDir);
        pathList.addAll(Search.search(start, p -> !p.toFile().getName().endsWith(exclude)));
    }

    private static void validation(String[] params) {
        if (params.length != 3) {
            throw new IllegalArgumentException("Input all parameters");
        }
        if (!new File(sourceDir).exists()) {
            throw new IllegalArgumentException("Source directory doesn't exist");
        }
    }
}
