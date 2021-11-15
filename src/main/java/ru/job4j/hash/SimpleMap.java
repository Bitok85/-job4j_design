package ru.job4j.hash;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {
    private static final float  LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int modCount = 0;
    private int size = 0;
    int point;
    private MapEntry<K, V>[] table = new MapEntry[capacity];
    
    @Override
    public boolean put(K key, V value) {
        boolean rsl;
        if (size >= capacity * LOAD_FACTOR) {
            expand();
        }
        if (table[indexFor(key)] != null) {
            rsl = false;
        } else {
            table[indexFor(key)] = new MapEntry<>(key, value);
            size++;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    /**
     * @param key - ключ значения для которого вычисляется индекс для table
     * @return модуль остатка деления суммы чисел значения хэшкода ключа на длину table
     */
    private int indexFor(K key) {
        int rsl = 0;
        int tmp = key.hashCode();
        while (tmp != 0) {
            rsl += tmp % 10;
            tmp /= 10;
        }
        return Math.abs(rsl % capacity);
    }

    private void expand() {
            capacity *= 2;
            MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                newTable[indexFor(table[i].key)] = table[i];
            }
        }
        table = newTable;
    }

    public int getLength() {
        return table.length;
    }

    public int size() {
        return size;
    }

    @Override
    public V get(K key) {
        return table[indexFor(key)] != null
                && key.equals(table[indexFor(key)].key)
                ? table[indexFor(key)].value : null;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        if (table[indexFor(key)] != null && key.equals(table[indexFor(key)].key)) {
            table[indexFor(key)] = null;
            size--;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        int expectedModCount = modCount;
        point = 0;
        return new Iterator<K>() {
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                boolean rsl = false;
                for (int i = point; i < table.length; i++) {
                    if (table[point] != null) {
                        rsl = true;
                        break;
                    }
                    point++;
                }
                return rsl;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[point++].key;
            }
        };
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
