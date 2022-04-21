package ru.job4j.cache;

import java.util.Locale;
import java.util.Scanner;

public class Emulator {

    private final static String STOP = "exit";
    private final static String PRINT = "output";

    public static void main(String[] args) throws Exception {
        String tmpFile;
        Scanner sc = new Scanner(System.in);
        System.out.println("Input directory");
        DirFileCache dirFileCache = new DirFileCache(sc.nextLine());
        do {
            System.out.println("Input caching file name or 'exit' or output 'filename' to output");
            tmpFile = sc.nextLine();
            if (tmpFile.contains(PRINT)) {
                System.out.println(dirFileCache.get(tmpFile.split(" ")[1]));
            } else {
                dirFileCache.load(tmpFile);
            }
        }
        while (!STOP.equals(tmpFile.split(" ")[0]));
    }
}

