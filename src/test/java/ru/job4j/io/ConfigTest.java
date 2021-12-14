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
    public void whenLineContainsMoreThenOneEqualSymbolAndEnsWithIt() {
        String path = "./data/pairsWithFewEquals.properties";
        Config config = new Config(path);
        config.load();

    }

    @Test(expected =  UnsupportedOperationException.class)
    public void whenLineContainsMoreThenOneEqualsSymbol() {
        String path = "./data/pairWithFewEquals2.properties";
        Config config = new Config(path);
        config.load();
    }
}
