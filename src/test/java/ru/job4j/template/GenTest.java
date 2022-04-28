package ru.job4j.template;

import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class GenTest {

    @Ignore
    @Test
    public void whenGenerateThenString() {
        Gen gen = new Gen();
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Alex");
        args.put("subject", "you");
        assertThat(gen.produce(template, args), is("I am Alex, Who are you? "));
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenInstanceNotExistInMap() {
        Gen gen = new Gen();
        String template = "I am ${name}, Who are ${subject}? ";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Alex");
        gen.produce(template, args);
    }

    @Ignore
    @Test(expected = NullPointerException.class)
    public void whenValueIsNull() {
        Gen gen = new Gen();
        String template = "I am ${name}, Who are ${subject}? ";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Alex");
        args.put("subject", null);
        gen.produce(template, args);
    }
}