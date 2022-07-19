package ua.edu.sumdu.j2se.lietunova.tasks.model;

import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Stream;

public class LinkedTaskList extends AbstractTaskList {
    private Node first;
    private Node last;
    private int sizeList = 0;

    @Override
    public Stream<Task> getStream() {
        Stream.Builder<Task> builder = Stream.builder();
        for (int i = 0; i < size(); i++) {
            builder.add(getTask(i));
        }
        return builder.build();
    }

    @Override
    public ListTypes.types getType() {
        return ListTypes.types.LINKED;
    }

    @Override
    public void add(Task task) {
        Node newNode = new Node(task);
        if (sizeList == 0) {
            first = newNode;
        } else if (sizeList == 1) {
            last = newNode;
            last.previous = first;
            first.next = last;
        } else {
            newNode.next = null;
            last.next = newNode;
            newNode.previous = last;
            Node n = last;
            last = newNode;
        }
        sizeList++;
    }

    @Override
    public boolean remove(Task task) {
        if (sizeList == 0) {
            return false;
        }
        Node current = first;
        for (int i = 0; i < sizeList; i++) {
            if (current.task == task) {
                sizeList--;
                if (i == 0) {
                    current.next.previous = null;
                    first = current.next;
                    return true;
                }
                if (i == sizeList) {
                    current.previous.next = null;
                    last = current.previous;
                    return true;
                }
                current.previous.next = current.next;

                current.next.previous = current.previous;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public int size() {
        return sizeList;
    }

    @Override
    public Task getTask(int index) {
        if (index >= sizeList || index < 0) {
            throw new IndexOutOfBoundsException("Index cannot be >= size array or negative!!");
        }
        Node current;
        if ((sizeList / 2) >= index) {
            current = first;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = last;
            for (int i = sizeList - 1; i > index; i--) {
                current = current.previous;
            }
        }
        return current.task;
    }

    @Override
    public Iterator<Task> iterator() {
        return new ListIterator(first);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node a = first;
        Node b = ((LinkedTaskList) o).first;
        if (this.size() != ((LinkedTaskList) o).size()) {
            return false;
        }
        for (int i = 0; i < size(); i++) {
            if (!(Objects.equals(a, b))) {
                return false;
            }
            a = a.next;
            b = b.next;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), first, last, sizeList);
    }

    @Override
    public LinkedTaskList clone() throws CloneNotSupportedException {
        LinkedTaskList result = (LinkedTaskList) super.clone();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("LinkedTaskList{");
        Node elem = first;

        for (int i = 0; i < size(); i++) {
            result.append(elem.toString());
            elem = elem.next;
        }
        return result.toString();
    }

    private static class Node {
        public Task task;
        public Node next;
        public Node previous;

        public Node(Task task) {
            this.task = task;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(task, node.task);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "task=" + task +
                    '}';
        }

        @Override
        public int hashCode() {
            return Objects.hash(task, next, previous);
        }
    }

    private class ListIterator implements Iterator<Task> {
        private Node nextNode;
        private int positionIterator = 0;

        public ListIterator(Node first) {
            nextNode = first;
        }

        @Override
        public boolean hasNext() {
            return positionIterator < sizeList && sizeList != 0;
        }

        @Override
        public Task next() {
            Task task = nextNode.task;
            nextNode = nextNode.next;
            positionIterator++;
            return task;
        }

        @Override
        public void remove() {
            if (positionIterator == 0) {
                throw new IllegalStateException();
            }
            LinkedTaskList.this.remove(nextNode.previous.task);
            positionIterator--;
        }
    }
}
