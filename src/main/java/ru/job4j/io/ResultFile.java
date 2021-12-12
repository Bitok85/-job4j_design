package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {

    public static void main(String[] args) {
        int size = 9;
        try (FileOutputStream out = new FileOutputStream("matrix.txt")) {
            for (int i = 1; i <= size; i++) {
                for (int j = 1; j <= size; j++) {
                    out.write(Integer.toString(i * j).getBytes());
                    out.write(" ".getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
