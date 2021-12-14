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
        List<String[]> log = getUnavailableLog(source);
        List<String> unavailableLog = createIntervals(log);
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            for (String line : unavailableLog) {
                out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private List<String[]> getUnavailableLog(String file) {
        List<String[]> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                String[] tmpLine = line.split(" ");
                    rsl.add(tmpLine);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    private List<String> createIntervals(List<String[]> log) {
        List<String> rsl = new ArrayList<>();
        StringBuilder tmpStr = new StringBuilder();
        for (String[] line : log) {
            if (tmpStr.isEmpty() && (unavailable1.equals(line[0]) || unavailable2.equals(line[0]))) {
                tmpStr.append(line[1]);
                tmpStr.append(";");
            } else if (!tmpStr.isEmpty() && !(unavailable1.equals(line[0]) || unavailable2.equals(line[0]))) {
                tmpStr.append(line[1]);
                rsl.add(tmpStr.toString());
                tmpStr = new StringBuilder();
            }
        }
        return rsl;
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
