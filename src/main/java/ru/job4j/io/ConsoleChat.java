package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {

    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final List<String> botList = new ArrayList<>();
    private static List<String> log = new ArrayList<>();
    boolean switcher = true;


    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        readPhrases(botAnswers);
        while (sc.hasNext()) {
            String in = sc.next();
            log.add(in);
            if (in.equals(OUT)) {
                sc.close();
                break;
            } else if (in.equals(STOP)) {
                switcher = false;
            } else if (in.equals(CONTINUE)) {
                String phrase = randomPhrase(botList);
                log.add(phrase);
                System.out.println(phrase);
                switcher = true;
            } else if (switcher) {
                String phrase = randomPhrase(botList);
                log.add(phrase);
                System.out.println(phrase);
            }
        }
    }

    private void readPhrases(String botAnswers) {
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers))) {
            br.lines().forEach(botList::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(path))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String randomPhrase(List<String> botList) {
        Random random = new Random();
        return botList.get(random.nextInt(botList.size()));
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("C:\\projects\\botLog.txt", "C:\\projects\\botAnswers.txt");
        cc.readPhrases(cc.botAnswers);
        cc.run();
        cc.saveLog(log);
    }
}
