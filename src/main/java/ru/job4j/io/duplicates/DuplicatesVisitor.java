package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private static Map<FileProperty, List<Path>> allFiles = new HashMap<>();

    public static Map<FileProperty, List<Path>> getAllFiles() {
        return allFiles;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty((long) attrs.size(), file.getFileName().toString());
        List<Path> duplicates = new ArrayList<>();
        if (!allFiles.containsKey(fileProperty)) {
            duplicates.add(file);
            allFiles.put(fileProperty, duplicates);
        } else {
            allFiles.get(fileProperty).add(file);
        }
        return super.visitFile(file, attrs);
    }
}
