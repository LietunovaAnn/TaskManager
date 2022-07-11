package ua.edu.sumdu.j2se.lietunova.tasks.model;

import ua.edu.sumdu.j2se.lietunova.tasks.model.exception.WrongArgumentException;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.SortedMap;
import java.util.TreeMap;


public class Tasks {
    public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {
        if (start == null || end == null) {
            throw new NullPointerException("Time (start, end) cannot be null!");
        }
        if (start.compareTo(end) > 0) {
            throw new WrongArgumentException();
        }

        Collection<Task> taskCollection = new LinkedList();
        for (Task t : tasks) {
            if (t.nextTimeAfter(start) != null && !t.nextTimeAfter(start).isAfter(end)) {
                taskCollection.add(t);
            }
        }
        return taskCollection;
    }

    public static SortedMap<LocalDateTime, ArrayTaskList> calendar(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {
        if (start == null || end == null) {
            throw new NullPointerException("Time (start, end) cannot be null!");
        }
        if (start.compareTo(end) > 0) {
            throw new IllegalArgumentException(start + " after " + end);
        }

        SortedMap<LocalDateTime, ArrayTaskList> sortedMap = new TreeMap<>();
        for (Task t : tasks) {
            LocalDateTime nextTime = t.nextTimeAfter(start);
            while (nextTime != null && !nextTime.isAfter(end)) {
                if (!sortedMap.containsKey(nextTime)) {
                    ArrayTaskList taskList = new ArrayTaskList();
                    taskList.add(t);
                    sortedMap.put(nextTime, taskList);
                } else {
                    sortedMap.get(nextTime).add(t);
                }
                nextTime = nextTime.plusSeconds(t.getRepeatInterval());
            }
        }
        return sortedMap;
    }
}