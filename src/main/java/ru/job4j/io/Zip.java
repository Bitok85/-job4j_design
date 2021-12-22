package ru.job4j.io;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public static void packFiles(String[] args, List<File> sources, File target) {
        if (!checkSourceDir(args)) {
            throw new IllegalArgumentException("Source directory doesn't exist");
        }
        if (!target.isDirectory()) {
            throw new IllegalArgumentException("Target directory doesn't exist");
        }
    }

    public static void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static File[] fileTreeTraversal(File folder) {
        File[] folderEntries = folder.listFiles();
        for (File entry : folderEntries) {
            if (entry.isDirectory()) {
                fileTreeTraversal(entry);
                continue;
            }
        }
        return folderEntries;
    }

    private static boolean checkSourceDir(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        File sourceDir = new File(argsName.get("d"));
        return sourceDir.isDirectory() && sourceDir.length() > 0;
    }

    public static void main(String[] args) {
        packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
    }
}
