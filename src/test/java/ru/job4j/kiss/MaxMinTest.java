package ru.job4j.kiss;

import org.junit.Test;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MaxMinTest {

    @Test
    public void max() {
        MaxMin maxMin = new MaxMin();
        Comparator<Integer> comp1 = Integer::compare;
        Comparator<String> comp2 = Comparator.naturalOrder();
        List<Integer> list1 = List.of(5, 4, 5, 7, 2);
        List<String> list2 = List.of("ab", "dc", "aa", "yz");
        assertThat(maxMin.max(list1, comp1), is(7));
        assertThat(maxMin.max(list2, comp2), is("yz"));
    }

    @Test
    public void min() {
        MaxMin maxMin = new MaxMin();
        Comparator<Integer> comp1 = Integer::compare;
        Comparator<String> comp2 = Comparator.naturalOrder();
        List<Integer> list1 = List.of(5, 4, 5, 7, 2);
        List<String> list2 = List.of("ab", "dc", "aa", "yz");
        assertThat(maxMin.min(list1, comp1), is(2));
        assertThat(maxMin.min(list2, comp2), is("aa"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenListHasLessThenTwoObjects() {
        MaxMin maxMin = new MaxMin();
        Comparator<Integer> comp1 = Integer::compare;
        List<Integer> list1 = List.of(5);
        maxMin.max(list1, comp1);
    }
}