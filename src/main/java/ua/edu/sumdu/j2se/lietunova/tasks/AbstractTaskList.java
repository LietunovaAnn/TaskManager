package ua.edu.sumdu.j2se.lietunova.tasks;

public abstract class AbstractTaskList implements Iterable<Task>, Cloneable {
    public abstract ListTypes.types getType();

    public abstract void add(Task task);

    public abstract boolean remove(Task task);

    public abstract int size();

    public abstract Task getTask(int index);

    public AbstractTaskList incoming(int from, int to) throws IllegalArgumentException {
        if (from < 0 || to < 0) {
            throw new IllegalArgumentException("Time (from, to) cannot be negative!");
        }
        AbstractTaskList taskList = TaskListFactory.createTaskList(getType());
        for (int i = 0; i < size(); i++) {
            if ((getTask(i) != null) && (getTask(i).nextTimeAfter(from) != -1) && (getTask(i).nextTimeAfter(from) < to)) {
                taskList.add(getTask(i));
            }
        }
        return taskList;
    }
}