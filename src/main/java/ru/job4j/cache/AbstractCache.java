package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
        SoftReference<V> softValue = new SoftReference<>(value);
        value = null;
        cache.put(key, softValue);
    }

    public V get(K key) throws Exception {
        V strongValue = cache.getOrDefault(key, new SoftReference<>(null)).get();
        if (strongValue == null) {
            strongValue = load(key);
        }
        return strongValue;
    }

    protected abstract V load(K key) throws Exception;
}
