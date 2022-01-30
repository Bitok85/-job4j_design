package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class CSVReader {

    private static List<String> outList = new ArrayList<>();
    private static final String PRINT = "stdout";

    public static void handle(ArgsName argsName) throws Exception {
        File file = new File(argsName.get("path"));
        String filterNames = argsName.get("filter");
        String delimiter = argsName.get("delimiter");
        try (Scanner sc = new Scanner(file)) {
            String[] firstRow = sc.nextLine().split(delimiter);
            List<Integer> columns = CSVReader.colIndexes(firstRow, filterNames);
            StringJoiner sj = new StringJoiner(";");
            columns.forEach(p -> sj.add(firstRow[p]));
            outList.add(sj.toString());
            while (sc.hasNextLine()) {
                StringJoiner tmpSj = new StringJoiner(";");
                String[] rowArr = sc.nextLine().split(delimiter);
                columns.forEach(p -> tmpSj.add(rowArr[p]));
                outList.add(tmpSj.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void action(String out) throws IOException {
        if (PRINT.equals(out)) {
            outList.forEach(System.out::println);
        } else {
            Files.write(Paths.get(out), outList, StandardOpenOption.CREATE);
        }
    }

    private static List<Integer> colIndexes(String[] firstRow, String filterNames) {
        List<Integer> rsl = new ArrayList<>();
        String[] filter = filterNames.split(",");
        for (int i = 0; i < firstRow.length; i++) {
            for (String str : filter) {
                if (str.equals(firstRow[i])) {
                    rsl.add(i);
                    break;
                }
            }
        }
        return rsl;
    }

    private static void validation(ArgsName paramsName, String[] params) {
        if (params.length != 4) {
            throw new IllegalArgumentException("Some of input parameters are missing");
        }
        if (!Files.exists(Paths.get(paramsName.get("path")))) {
            throw new IllegalArgumentException("File doesn't exist");
        }
        if (!PRINT.equals(paramsName.get("out"))
                && !Files.exists(Paths.get(paramsName.get("out")).getParent())) {
            throw new IllegalArgumentException("Incorrect output parameter. Input " + PRINT + " "
                    + " for console input or correct file path");
        }
    }

    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        validation(argsName, args);
        handle(argsName);
        action(argsName.get("out"));
    }

}
