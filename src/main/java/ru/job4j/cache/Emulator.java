package ru.job4j.cache;

public class Emulator {

    private static final String CACHE_DIR = "./src/data";

    public static void main(String[] args) throws Exception {
        DirFileCache dirFileCache = new DirFileCache(CACHE_DIR);
        dirFileCache.load("text.txt");
        System.out.println(dirFileCache.get("text.txt"));
    }
}

