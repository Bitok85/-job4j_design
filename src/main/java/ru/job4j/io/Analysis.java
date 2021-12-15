package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Analysis {

    final String unavailable1 = "400";
    final String unavailable2 = "500";

    public void unavailable(String source, String target) {
        List<String> rslIntervals = new ArrayList<>();
        StringBuilder tmpStr = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                String[] tmpLine = line.split(" ");
                if (tmpStr.isEmpty() && (unavailable1.equals(tmpLine[0]) || unavailable2.equals(tmpLine[0]))) {
                    tmpStr.append(tmpLine[1]);
                    tmpStr.append(";");
                } else if (!tmpStr.isEmpty() && !(unavailable1.equals(tmpLine[0]) || unavailable2.equals(tmpLine[0]))) {
                    tmpStr.append(tmpLine[1]);
                    rslIntervals.add(tmpStr.toString());
                    tmpStr = new StringBuilder();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            for (String line : rslIntervals) {
                out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("./data/unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Analysis analysis = new Analysis();
        String source1 = "./data/log1.txt";
        String source2 = "./data/log2.txt";
        String target1 = "./data/unavailable1.csv";
        String target2 = "./data/unavailable2.csv";
        analysis.unavailable(source1, target1);
        analysis.unavailable(source2, target2);
    }
}
