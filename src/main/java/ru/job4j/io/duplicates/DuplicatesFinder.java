package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        Files.walkFileTree(Path.of("./"), new DuplicatesVisitor());
        Map<Path, List<Path>> rslMap = DuplicatesFinder.listOfDuplicates(DuplicatesVisitor.getAllFiles());
        for (Map.Entry<Path, List<Path>> entry : rslMap.entrySet()) {
            System.out.println(entry.getKey().toString());
            entry.getValue().forEach(System.out::println);
        }
    }

    public static Map<Path, List<Path>> listOfDuplicates(Map<FileProperty, List<Path>> map) {
        Map<Path, List<Path>> rslMap = new HashMap<>();
        for (Map.Entry<FileProperty, List<Path>> entry : map.entrySet()) {
            List<Path> duplicates = entry.getValue();
            if (duplicates.size() > 1) {
                for (Path path : duplicates) {
                    if (duplicates.get(0).equals(path)) {
                        List<Path> tmpList = new ArrayList<>();
                        tmpList.add(path.toAbsolutePath());
                        rslMap.put(path.getFileName(), tmpList);
                    } else {
                        rslMap.get(path.getFileName()).add(path.toAbsolutePath());
                    }
                }
            }
        }
        return rslMap;
    }
}
