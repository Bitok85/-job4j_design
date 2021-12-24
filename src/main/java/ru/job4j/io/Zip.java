package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    private final static int PARAMSQUANTITY = 3;

    public static void packFiles(List<File> sources, File target) {
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

    private static List<File> listOf(File folder) {
        List<File> rslList = new ArrayList<>();
        File[] fileArray = folder.listFiles();
        rslList.addAll(Arrays.asList(fileArray));
        for (File file : fileArray) {
            if (file.isDirectory()) {
                rslList.addAll(listOf(file));
            }
        }
        return rslList;
    }

    public static void main(String[] args) {
        if (args.length != PARAMSQUANTITY) {
            throw new IllegalArgumentException("Missing some of income parameters");
        }
        ArgsName argsName = ArgsName.of(args);
        File sourceDir = new File(argsName.get("d"));
        if (!sourceDir.isDirectory() && sourceDir.length() == 0) {
            throw new IllegalArgumentException("Invalid source directory");
        }
        packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
    }
}
