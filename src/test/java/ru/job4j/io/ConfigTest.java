package ru.job4j.io;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairsWithoutCommentsEmptyLinesAndIncorrectKeys() {
        String path = "./data/correctPairs.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("Admin"), is("Alex"));
        assertThat(config.value("Password"), is("SwordFish"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenIncorrectKey() {
        String path = "./data/pairWithIncorrectKey.properties";
        Config config = new Config(path);
        config.load();
        config.value(" ");
    }

    @Test
    public void whenPairsWithCommentsAndEmptyLines() {
        String path = "./data/pairsWithCommentsAndEmptyLines.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("Admin"), is("Alex"));
        assertThat(config.value("Password"), is("SwordFish"));
        assertThat(config.size(), is(2));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void whenLineContainsMoreThenOneEqualSymbol() {
        String path1 = "./data/pairsWithFewEquals.properties";
        String path2 = "./data/pairWithFewEquals2.properties";
        Config config1 = new Config(path1);
        Config config2 = new Config(path2);
        config1.load();
        config2.load();

    }
}
