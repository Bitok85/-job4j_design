package ru.job4j.generics;

public class UserStore implements Store<User> {
    private final Store<User> store = new MemStore<>();

    @Override
    public void add(User model) {
        add(model);
    }

    @Override
    public boolean replace(String id, User model) {
        return replace(id, model);
    }

    @Override
    public boolean delete(String id) {
        return delete(id);
    }

    @Override
    public User findBy(String id) {
        return findBy(id);
    }
}
