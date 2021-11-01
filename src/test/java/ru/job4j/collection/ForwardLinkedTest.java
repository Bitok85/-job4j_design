package ru.job4j.collection;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertThrows;

public class ForwardLinkedTest {

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteFirst() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.deleteFirst();
        Iterator<Integer> it = linked.iterator();
        it.next();

    }

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteEmptyLinked() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.deleteFirst();
    }

    @Test
    public void whenMultiDelete() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        linked.add(3);
        assertThat(linked.deleteFirst(), is(1));
        assertThat(linked.deleteFirst(), is(2));
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(3));
    }

    @Test
    public void whenEmptyAddFirst() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.addFirst(1);
        assertThat(linked.deleteFirst(), is(1));
    }

    @Test
    public void whenAddFirstFewTimes() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.addFirst(1);
        linked.addFirst(2);
        linked.addFirst(3);
        Iterator<Integer> it = linked.iterator();
        assertThat(linked.deleteFirst(), is(3));
        assertThat(linked.deleteFirst(), is(2));
        assertThat(linked.deleteFirst(), is(1));
    }
}