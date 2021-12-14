package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AnalysisTest {

    @Rule
    public TemporaryFolder tmpFolder = new TemporaryFolder();

    @Test
    public void unavailableWhenFewIntervals() throws IOException {
        Analysis analysis = new Analysis();
        File source = tmpFolder.newFile("source.txt");
        File target = tmpFolder.newFile("target.csv");
        try (PrintWriter out = new PrintWriter(source)) {
            try (BufferedReader data = new BufferedReader(new FileReader("./data/log1.txt"))) {
                for (String line = data.readLine(); line != null; line = data.readLine()) {
                    out.println(line);
                }
            }
        }
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader((new FileReader(target)))) {
            in.lines().forEach(rsl::add);
        }
        assertThat(rsl.get(0).toString(), is("10:57:01;10:59:01"));
        assertThat(rsl.get(1).toString(), is("11:01:02;11:02:02"));
    }

}