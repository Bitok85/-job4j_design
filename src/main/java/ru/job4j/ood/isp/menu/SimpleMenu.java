package ru.job4j.ood.isp.menu;

import java.util.*;

public class SimpleMenu implements Menu {

    private final List<MenuItem> rootElements = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        boolean result = false;
        if (findItem(parentName).isPresent()) {
            System.out.println("Какая-то логика ...");
        }
        return result;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        return Optional.empty();
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        return null;
    }

    private Optional<ItemInfo> findItem(String name) {
        Iterator<ItemInfo> iterator = new DFSIterator();
        Optional<ItemInfo> result = Optional.empty();
        while (iterator.hasNext()) {
            ItemInfo tmp = iterator.next();
            if (name.equals(tmp.getMenuItem().getName())) {
                result = Optional.of(tmp);
                break;
            }
        }
        return result;
    }

    private static class SimpleMenuItem implements MenuItem {

        private String name;
        private List<MenuItem> children = new ArrayList<>();
        private ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return null;
        }

        @Override
        public List<MenuItem> getChildren() {
            return null;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return null;
        }
    }

    private class DFSIterator implements Iterator<ItemInfo> {

        Deque<MenuItem> stack = new LinkedList<>();

        Deque<String> numbers = new LinkedList<>();

        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return (!stack.isEmpty());
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (ListIterator<MenuItem> i = children.listIterator(children.size()); i.hasPrevious();) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--).concat(".")));
            }
            return new ItemInfo(current, lastNumber);
        }
    }

    private class ItemInfo {

        MenuItem menuItem;
        String number;

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }

        public MenuItem getMenuItem() {
            return menuItem;
        }

        public String getNumber() {
            return number;
        }
    }
}
