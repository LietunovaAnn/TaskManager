package ua.edu.sumdu.j2se.lietunova.tasks;

import java.time.LocalDateTime;
import java.util.*;


public class Tasks {
    public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {
        if (start == null || end == null) {
            throw new NullPointerException("Time (start, end) cannot be null!");
        }
        if (start.compareTo(end) > 0) {
            throw new IllegalArgumentException(start + " after " + end);
        }

        Collection<Task> taskCollection = new LinkedList();
        for (Task t : tasks) {
            if (t.nextTimeAfter(start) != null && !t.nextTimeAfter(start).isAfter(end)) {
                taskCollection.add(t);
            }
        }
        return taskCollection;
    }

    public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {
        if (start == null || end == null) {
            throw new NullPointerException("Time (start, end) cannot be null!");
        }
        if (start.compareTo(end) > 0) {
            throw new IllegalArgumentException(start + " after " + end);
        }
        SortedMap<LocalDateTime, Set<Task>> sortedMap = new TreeMap<>();
        for (Task t : tasks) {
            LocalDateTime nextTime = t.nextTimeAfter(start);
            while (nextTime != null && !nextTime.isAfter(end)) {
                if (!sortedMap.containsKey(nextTime)) {
                    Set<Task> taskSet = new HashSet<>();
                    taskSet.add(t);
                    sortedMap.put(nextTime, taskSet);
                } else {
                    sortedMap.get(nextTime).add(t);
                }
                nextTime = nextTime.plusSeconds(t.getRepeatInterval());
            }
        }
        return sortedMap;
    }
}