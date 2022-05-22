package ua.edu.sumdu.j2se.lietunova.tasks;

import java.util.ArrayList;

public class ArrayTaskList {
    ArrayList<Task> arrayTaskLists = new ArrayList<Task>();


    public void add(Task task) {
        arrayTaskLists.add(task);
    }

    public boolean remove(Task task) {
        return arrayTaskLists.remove(task);


/*        for (int i = 0; i < arrayTaskLists.size(); i++) {
            if (arrayTaskLists.get(i).equals(task)) {
                arrayTaskLists.remove(i);
                return true;
            }
        }
        return false;*/
    }

    public int size() {
        return arrayTaskLists.size();
    }

    public Task getTask(int index) {
        return arrayTaskLists.get(index);
    }

    public ArrayTaskList incoming(int from, int to) {
        ArrayTaskList arrayIncoming = new ArrayTaskList();
        for (int i = 0; i < arrayTaskLists.size(); i++) {
            if (arrayTaskLists.get(i).getTime() > from && arrayTaskLists.get(i).getTime() < to) {
                arrayIncoming.add(arrayTaskLists.get(i));
            }
        }
        return arrayIncoming;
    }


}
