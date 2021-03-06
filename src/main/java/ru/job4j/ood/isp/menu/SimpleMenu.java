package ru.job4j.ood.isp.menu;

import java.util.*;

public class SimpleMenu implements Menu {

    private final List<MenuItem> rootElements = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        if (findItem(childName).isPresent()) {
            return false;
        }
        if (Objects.equals(parentName, ROOT)) {
            rootElements.add(new SimpleMenuItem(childName, actionDelegate));
        } else {
            Optional<ItemInfo> parentItem = findItem(parentName);
            if (parentItem.isPresent()) {
                parentItem.get().getMenuItem().getChildren().add(new SimpleMenuItem(childName, actionDelegate));
            } else {
                return false;
            }
        }
        return true;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        return findItem(itemName).map(itemInfo -> {
            MenuItem menuItem = itemInfo.getMenuItem();
            return new MenuItemInfo(menuItem, itemInfo.getNumber());
        });
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        Iterator<ItemInfo> baseIterator = new DFSIterator();
        return new Iterator<MenuItemInfo>() {
            @Override
            public boolean hasNext() {
                return baseIterator.hasNext();
            }

            @Override
            public MenuItemInfo next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                ItemInfo itemInfo = baseIterator.next();
                MenuItem menuItem = itemInfo.getMenuItem();
                return new MenuItemInfo(menuItem, itemInfo.getNumber());
            }
        };
    }

    private Optional<ItemInfo> findItem(String name) {
        Iterator<ItemInfo> iterator = new DFSIterator();
        Optional<ItemInfo> result = Optional.empty();
        while (iterator.hasNext()) {
            ItemInfo tmp = iterator.next();
            if (tmp.getMenuItem().getName().equals(name)) {
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
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
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
