package ru.job4j.hash;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void whenIntegerPutAndGetValue() {
        SimpleMap<Integer, String> sm = new SimpleMap<>();
        sm.put(1, "abc");
        String rsl = sm.get(1);
        assertThat(rsl, is("abc"));
    }

    @Test
    public void whenStringPutAndGetValueAndSizeCheck() {
        SimpleMap<String, String> sm = new SimpleMap<>();
        sm.put("Ivanov", "Moscow");
        sm.put("Petrov", "London");
        assertThat(sm.get("Petrov"), is("London"));
        assertThat(sm.get("Ivanov"), is("Moscow"));
        assertThat(sm.size(), is(2));
    }

    @Test
    public void whenPutAndExpand() {
        SimpleMap<Integer, String> sm = new SimpleMap<>();
        assertThat(sm.getLength(), is(8));
        IntStream.range(0, 7).forEach(s -> sm.put(s, "abc"));
        assertThat(sm.getLength(), is(16));
    }

    @Test
    public void whenPutSameIndexThenFalseAndAnotherIndexThenTrue() {
        SimpleMap<Integer, String> sm = new SimpleMap<>();
        sm.put(123, "abc");
        assertFalse(sm.put(123, "bcd"));
        assertTrue(sm.put(12, "cbd"));
    }

    @Test
    public void whenRemoveExistIndexThenTrueAndNullIndexValueAndSizeChange() {
        SimpleMap<Integer, String> sm = new SimpleMap<>();
        sm.put(123, "abc");
        sm.put(12, "bcd");
        assertThat(sm.size(), is(2));
        assertTrue(sm.remove(123));
        assertNull(sm.get(123));
        assertThat(sm.size(), is(1));
    }

    @Test
    public void whenRemoveNotExistIndexThanFalseAndSizeNotChanges() {
        SimpleMap<Integer, String> sm = new SimpleMap<>();
        sm.put(123, "abc");
        sm.put(12, "bcd");
        assertThat(sm.size(), is(2));
        assertFalse(sm.remove(345));
        assertThat(sm.size(), is(2));
    }

    @Test
    public void whenPutAndIteratorAndGetValueThenNotNull() {
        SimpleMap<String, String> sm = new SimpleMap<>();
        sm.put("Kot Matroskin", "Paws and tail");
        sm.put("Sharik the Dog", "Teeth and wool");
        Iterator<String> it = sm.iterator();
        it.next();
        assertNotNull(sm.get(it.next()));
    }

    @Test
    public void whenPutSimpleIntegerKeyThenGetValueByIndexOrder() {
        SimpleMap<Integer, String> sm = new SimpleMap<>();
        sm.put(1, "Vanya");
        sm.put(2, "Tanya");
        Iterator<Integer> it = sm.iterator();
        assertThat(sm.get(it.next()), is("Vanya"));
        assertThat(sm.get(it.next()), is("Tanya"));
    }

    @Test
    public void whenItHasNextTrueAndHasNoNextFalse() {
        SimpleMap<Integer, String> sm = new SimpleMap<>();
        sm.put(1, "Vanya");
        sm.put(2, "Tanya");
        Iterator<Integer> it = sm.iterator();
        it.next();
        assertTrue(it.hasNext());
        it.next();
        assertFalse(it.hasNext());
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenPutWhileAfterIteratorInitilizedThenThrowException() {
        SimpleMap<Integer, String> sm = new SimpleMap<>();
        sm.put(1, "Vanya");
        sm.put(2, "Tanya");
        Iterator<Integer> it = sm.iterator();
        sm.put(3, "Fedya");
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenRemoveAfterIteratorInitilizedThenThrowException() {
        SimpleMap<Integer, String> sm = new SimpleMap<>();
        sm.put(1, "Vanya");
        sm.put(2, "Tanya");
        Iterator<Integer> it = sm.iterator();
        sm.remove(1);
        it.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenEmptyMapIteratorNextThenThrowException() {
        SimpleMap<Integer, String> sm = new SimpleMap<>();
        Iterator<Integer> it = sm.iterator();
        it.next();
        }

    @Test(expected = NoSuchElementException.class)
    public void whenIteratorHasNotNextAndTryNextThenThrowException() {
        SimpleMap<Integer, String> sm = new SimpleMap<>();
        sm.put(1, "Vanya");
        Iterator<Integer> it = sm.iterator();
        it.next();
        it.next();
    }
}