package ru.job4j.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;
    private static final Logger LOG = LoggerFactory.getLogger(DirFileCache.class.getName());

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) throws IOException {
        Path path = Paths.get(String.join("/", cachingDir, key));
        String value = Files.readString(path);
        put(key, value);
        return value;
    }
}

