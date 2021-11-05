package ru.job4j.set;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(1));
    }

    @Test
    public void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        /*
        assertTrue(set.add(null));
        */
        assertTrue(set.contains(null));
        /*
        assertFalse(set.add(null));
        */
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenAddAfterIterInitialisationThenThrowException() {
        SimpleSet<Integer> set = new SimpleSet<>();
        Iterator<Integer> it = set.iterator();
        set.add(1);
        it.next();
    }
    @Test(expected = NoSuchElementException.class)
    public void whenNoHasNextThenThrowException() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(1);
        Iterator<Integer> it = set.iterator();
        it.next();
        it.next();
    }

    @Test
    public void whenNoHasNextThenFalse() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(2);
        Iterator<Integer> it = set.iterator();
        it.next();
        it.next();
        assertFalse(it.hasNext());
    }

    @Test
    public void whenSetHaveToGrow() {
        SimpleSet<Integer> set = new SimpleSet<>();
        for (int i = 0; i <= 20; i++) {
            set.add(i);
        }
        assertTrue(set.contains(20));
    }

}