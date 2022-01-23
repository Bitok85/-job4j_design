package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    private static String sourceDir;
    private static String exclude;
    private static String targetDir;

    private static Map<File, String> fileZipPath = new HashMap<>();
    private static List<File> listOfExceptions = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        sourceDir = argsName.get("d");
        exclude = argsName.get("e");
        targetDir = argsName.get("o");
        validation(args);
        searchExceptions();
        fileMapGen(new File(sourceDir));
        packZip();
    }

    private static void searchExceptions() throws IOException {
        Path start = Paths.get(sourceDir);
        Search.search(start, p -> p.toFile().getName().endsWith(exclude))
                .forEach(p -> listOfExceptions.add(p.toFile()));
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

    public static void packZip() {
        for (Map.Entry<File, String> entry : fileZipPath.entrySet()) {
            packSingleFile(entry.getKey(), new File(entry.getValue()));
        }
    }

    private static void fileMapGen(File file) {
        if (file.isFile() && !listOfExceptions.contains(file)) {
            fileZipPath.put(file, zipPath(file.getAbsolutePath()) + ".zip");
        } else if (file.isDirectory() && !file.getName().equals(new File(targetDir).getName())) {
            File zipEntryDir = new File(zipPath(file.getAbsolutePath()));
            if (!zipEntryDir.mkdir()) {
                System.out.println("Cannot create directory " + zipEntryDir.toString());
            }
            File[] subDir = file.listFiles();
            if (subDir != null) {
                Arrays.stream(subDir)
                        .forEach(Zip::fileMapGen);
            }
        }
    }

    private static String zipPath(String file) {
        String sourceTail = new File(sourceDir).getName();
        return new File(file).getName().equals(sourceTail)
                ? targetDir + File.separator + sourceTail
                : targetDir + File.separator + file.substring(sourceDir.length() + 1, file.length());
    }

    private static void validation(String[] params) {
        if (params.length != 3) {
            throw new IllegalArgumentException("Input all parameters");
        }
        if (!new File(sourceDir).exists()) {
            throw new IllegalArgumentException("Source directory doesn't exist");
        }
        if (!new File(targetDir).exists()) {
            throw new IllegalArgumentException("Target directory doesn't exist");
        }
    }
}
