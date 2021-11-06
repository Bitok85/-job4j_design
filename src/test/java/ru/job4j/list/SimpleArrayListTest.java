package ru.job4j.list;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SimpleArrayListTest {

    List<Integer> list;

    @Before
    public void initData() {
        list = new SimpleArrayList<>(3);
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void whenAddSizeIncrease() {
        Assert.assertEquals(3, list.size());
    }

    @Test
    public void whenAddAndGetCorrectIndex() {
        Assert.assertEquals(Integer.valueOf(1), list.get(0));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenGetByIncorrectIndexThenGetException() {
        list.get(5);
    }

    @Test
    public void whenRemoveThenGetValueAndSizeDecrease() {
        Assert.assertEquals(3, list.size());
        Assert.assertEquals(Integer.valueOf(2), list.remove(1));
        Assert.assertEquals(2, list.size());
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenRemoveIncorrectIndexThenGetException() {
        list.remove(5);
    }

    @Test
    public void whenRemoveThenMustBeNotEmpty() {
        list.remove(1);
        Assert.assertEquals(Integer.valueOf(1), list.get(0));
        Assert.assertEquals(Integer.valueOf(3), list.get(1));
    }

    @Test
    public void whenAddNullMustBeSameBehavior() {
        list = new SimpleArrayList<>(3);
        list.add(null);
        list.add(null);
        Assert.assertEquals(2, list.size());
        Assert.assertNull(list.get(0));
        Assert.assertNull(list.get(1));
    }

    @Test
    public void whenSetThenGetOldValueAndSizeNotChanged() {
        Assert.assertEquals(Integer.valueOf(2), list.set(1, 22));
        Assert.assertEquals(3, list.size());
        Assert.assertEquals(Integer.valueOf(22), list.get(1));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenSetIncorrectIndexAndGetException() {
        list.set(5, 22);
    }

    @Test
    public void whenGetIteratorFromEmptyListThenReturnFalse() {
        list = new SimpleArrayList<>(5);
        assertFalse(list.iterator().hasNext());
    }

    @Test (expected = NoSuchElementException.class)
    public void whenGetIteratorFromEmptyListThenThrowException() {
        list = new SimpleArrayList<>(5);
        list.iterator().next();
    }

    @Test
    public void whenGetIteratorTwiceThenStartAlwaysFromBeginning() {
        Assert.assertEquals(Integer.valueOf(1), list.iterator().next());
        Assert.assertEquals(Integer.valueOf(1), list.iterator().next());
    }

    @Test
    public void whenCheckIterator() {
        Iterator<Integer> iterator = list.iterator();
        assertTrue(iterator.hasNext());
        Assert.assertEquals(Integer.valueOf(1), iterator.next());
        assertTrue(iterator.hasNext());
        Assert.assertEquals(Integer.valueOf(2), iterator.next());
        assertTrue(iterator.hasNext());
        Assert.assertEquals(Integer.valueOf(3), iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void whenNoPlaceThenMustIncreaseCapacity() {
        IntStream.range(3, 10).forEach(s -> list.add(s));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenAddAfterGetIteratorThenMustBeException() {
        Iterator<Integer> iterator = list.iterator();
        list.add(4);
        iterator.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenRemoveAfterGetIteratorThenMustBeException() {
        Iterator<Integer> iterator = list.iterator();
        list.remove(0);
        iterator.next();
    }
}
