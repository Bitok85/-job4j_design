package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        Files.walkFileTree(Path.of("./"), new DuplicatesVisitor());
        for (Map.Entry<FileProperty, List<Path>> entry : DuplicatesVisitor.getAllFiles().entrySet()) {
            List<Path> duplicates = entry.getValue();
            if (duplicates.size() > 1) {
                for (Path path : duplicates) {
                    if (duplicates.get(0).equals(path)) {
                        System.out.println(path.getFileName());
                    }
                    System.out.println(path.toAbsolutePath());
                }
            }
        }
    }
}
