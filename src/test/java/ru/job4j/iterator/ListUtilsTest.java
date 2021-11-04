package ru.job4j.iterator;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 1);
    }

    @Test
    public void whenAddAfter() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addAfter(input, 0, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddAfterWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addAfter(input, 3, 2);
    }

    @Test
    public void whenRemoveIfListContainsTruePredicate() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3));
        Predicate<Integer> pred = s -> s % 2 != 0;
        ListUtils.removeIf(input, pred);
        assertThat(input, is(Arrays.asList(2)));
    }

    @Test
    public void whenRemoveIfListNotContainsTruePredicate() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 1, 3));
        Predicate<Integer> pred = s -> s % 2 == 0;
        ListUtils.removeIf(input, pred);
        assertThat(input, is(Arrays.asList(1, 1, 3)));
    }

    @Test
    public void whenReplaceIfListContainsTruePredicate() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, -2, -3));
        Predicate<Integer> pred = s -> s < 0;
        ListUtils.replaceIf(input, pred, 0);
        assertThat(input, is(Arrays.asList(1, 0, 0)));
    }

    @Test
    public void whenReplaceIfListNonContainsTruePredicate() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3));
        Predicate<Integer> pred = s -> s < 0;
        ListUtils.replaceIf(input, pred, 0);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test
    public void whenRemoveAllFewElements() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3));
        ListUtils.removeAll(input, List.of(1, 3));
        assertThat(input, is(Arrays.asList(2)));
    }

    @Test
    public void whenRemoveAllNoElements() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3));
        ListUtils.removeAll(input, List.of(0, 4));
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }
}
