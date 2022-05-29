package ua.edu.sumdu.j2se.lietunova.tasks;


public class LinkedTaskList {
    private Node first;
    private Node last;
    private int sizeList = 0;

    public void add(Task task) {
        if (task == null) {
            throw new NullPointerException("Task can't be null!!!");
        }
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
            newNode = n;

//            Node prelast = new Node(last.task, last.previous);
//            last = newNode;
//            prelast.next = newNode;
//            last.previous = prelast;
        }
        sizeList++;
    }

    public boolean remove(Task task) {
        if (task == null) {
            throw new NullPointerException("Task can't be null!!!");
        }
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

    public int size() {
        return sizeList;
    }

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

    public LinkedTaskList incoming(int from, int to) {
        if (from < 0 || to < 0) {
            throw new IllegalArgumentException("Time (from, to) cannot be negative!");
        }
        LinkedTaskList linkedTaskIncoming = new LinkedTaskList();
        Node current = first;
        for (int i = 0; i < sizeList; i++) {
            if (current.task.getTime() > from && current.task.getTime() < to) {
                linkedTaskIncoming.add(current.task);
            }
            current = current.next;
        }
        return linkedTaskIncoming;
    }

    private static class Node {
        public Task task;
        public Node next;
        public Node previous;

        public Node(Task task) {
            this.task = task;
        }

        public Node(Task task, Node previous) {
            this.task = task;
            this.previous = previous;
        }
    }

}