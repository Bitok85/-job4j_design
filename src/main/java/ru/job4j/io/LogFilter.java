package ru.job4j.io;

import java.io.*;
import java.util.*;

public class LogFilter {
    public static List<String> filter(String file) {
        int filterValue = 404;
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                String[] tmpLine = line.split(" ");
                if (Integer.parseInt(tmpLine[tmpLine.length - 2]) == filterValue) {
                    rsl.add(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file, true)
                ))) {
            for (String line : log) {
                out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        for (String line : log) {
            System.out.println(line);
        }
        save(log, "404.txt");
    }
}
