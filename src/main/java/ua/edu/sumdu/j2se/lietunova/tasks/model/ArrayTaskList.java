package ua.edu.sumdu.j2se.lietunova.tasks.model;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Stream;


public class ArrayTaskList extends AbstractTaskList {
    private Task[] arrayTaskLists;
    private int sizeArray;

    @Override
    public ListTypes.types getType() {
        return ListTypes.types.ARRAY;
    }

    @Override
    public void add(Task task) {
        if (task == null) {
            throw new NullPointerException("Task can't be null!!!");
        }
        if (sizeArray == 0) {
            arrayTaskLists = new Task[sizeArray + 1];
            arrayTaskLists[0] = task;
        } else {
            arrayTaskLists = Arrays.copyOf(arrayTaskLists, arrayTaskLists.length + 1);
            arrayTaskLists[arrayTaskLists.length - 1] = task;
        }
        sizeArray++;
    }

    @Override
    public boolean remove(Task task) {
        if (task == null) {
            throw new NullPointerException("Task can't be null!!!");
        }
        for (int i = 0; i < arrayTaskLists.length; i++) {
            if (arrayTaskLists[i].equals(task)) {
                arrayTaskLists = ArrayUtils.removeElement(arrayTaskLists, arrayTaskLists[i]);
                sizeArray--;
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return sizeArray;
    }

    @Override
    public Task getTask(int index) {
        if (index >= sizeArray || index < 0) {
            throw new IndexOutOfBoundsException("Index cannot be >= size array or negative!!");
        }
        return arrayTaskLists[index];
    }

    @Override
    public Iterator<Task> iterator() {
        Iterator<Task> taskIterator = new Iterator<Task>() {

            private int position = 0;
            private int current = -1;

            @Override
            public boolean hasNext() {
                return position < sizeArray;
            }

            @Override
            public void remove() {
                if (current < 0) {
                    throw new IllegalStateException();
                }
                ArrayTaskList.this.remove(getTask(current));
                position--;
                current = -1;
            }

            @Override
            public Task next() throws IndexOutOfBoundsException {
                current = position;
                return arrayTaskLists[position++];

            }
        };
        return taskIterator;
    }

    @Override
    public Stream<Task> getStream() {
        return Arrays.stream(arrayTaskLists);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrayTaskList tasks = (ArrayTaskList) o;
        return sizeArray == tasks.sizeArray && Arrays.equals(arrayTaskLists, tasks.arrayTaskLists);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(sizeArray);
        result = 31 * result + Arrays.hashCode(arrayTaskLists);
        return result;
    }

    @Override
    public ArrayTaskList clone() {
        try {
            ArrayTaskList result = (ArrayTaskList) super.clone();
            result.arrayTaskLists = arrayTaskLists.clone();
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public String toString() {
        return "ArrayTaskList{" +
                "arrayTaskLists=" + Arrays.toString(arrayTaskLists) +
                ", sizeArray=" + sizeArray +
                '}';
    }
}
