package ua.edu.sumdu.j2se.lietunova.tasks;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

public class ArrayTaskList {
    private Task[] arrayTaskLists;
    private int sizeArray;


    public void add(Task task) {
        if (sizeArray == 0) {
            arrayTaskLists = new Task[sizeArray + 1];
            arrayTaskLists[0] = task;
        } else {
            arrayTaskLists = Arrays.copyOf(arrayTaskLists, arrayTaskLists.length + 1);
            arrayTaskLists[arrayTaskLists.length - 1] = task;
        }
        sizeArray++;
    }

    public boolean remove(Task task) {
        for (int i = 0; i < arrayTaskLists.length; i++) {
            if (arrayTaskLists[i].equals(task)) {
                arrayTaskLists = ArrayUtils.removeElement(arrayTaskLists, arrayTaskLists[i]);
                sizeArray--;
                return true;
            }
        }
        return false;
    }

    public int size() {
        return sizeArray;
    }

    public Task getTask(int index) {
        return arrayTaskLists[index];
    }

    public ArrayTaskList incoming(int from, int to) {
        ArrayTaskList arrayIncoming = new ArrayTaskList();
        for (int i = 0; i < arrayTaskLists.length; i++) {
            if (arrayTaskLists[i].getTime() > from && arrayTaskLists[i].getTime() < to) {
                arrayIncoming.add(arrayTaskLists[i]);
            }
        }
        return arrayIncoming;
    }


}
