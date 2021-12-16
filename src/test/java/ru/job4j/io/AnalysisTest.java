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
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        }
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader((new FileReader(target)))) {
            in.lines().forEach(rsl::add);
        }
        assertThat(rsl.get(0).toString(), is("10:57:01;10:59:01"));
        assertThat(rsl.get(1).toString(), is("11:01:02;11:02:02"));
    }

    @Test
    public void unavailableWhenOneInterval() throws IOException {
        Analysis analysis = new Analysis();
        File source = tmpFolder.newFile("source.txt");
        File target = tmpFolder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("500 10:59:01");
            out.println("400 11:01:02");
            out.println("200 11:02:02");
        }
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::add);
        }
        assertThat(rsl.get(0), is("10:57:01;11:02:02"));
        assertThat(rsl.size(), is(1));
    }
}
