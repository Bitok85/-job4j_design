package ru.job4j.generics;

public class RoleStore implements Store<Role> {
    private final Store<Role> store = new MemStore<>();

    @Override
    public void add(Role model) {
        add(model);
    }

    @Override
    public boolean replace(String id, Role model) {
        return replace(id, model);
    }

    @Override
    public boolean delete(String id) {
        return delete(id);
    }

    @Override
    public Role findBy(String id) {
        return findBy(id);
    }
}
