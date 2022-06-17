package ua.edu.sumdu.j2se.lietunova.tasks;

import java.util.stream.Stream;

public abstract class AbstractTaskList implements Iterable<Task>, Cloneable {
    public abstract Stream<Task> getStream();

    public abstract ListTypes.types getType();

    public abstract void add(Task task);

    public abstract boolean remove(Task task);

    public abstract int size();

    public abstract Task getTask(int index);

    public final AbstractTaskList incoming(int from, int to) throws IllegalArgumentException {
        if (from < 0 || to < 0) {
            throw new IllegalArgumentException("Time (from, to) cannot be negative!");
        }
        AbstractTaskList taskList = TaskListFactory.createTaskList(getType());
        getStream()
                .filter(task -> (task != null) && (task.nextTimeAfter(from) != -1) && (task.nextTimeAfter(from) < to))
                .forEach(taskList::add);

        return taskList;
    }
}