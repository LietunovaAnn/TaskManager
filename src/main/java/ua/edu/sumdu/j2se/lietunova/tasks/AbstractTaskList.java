package ua.edu.sumdu.j2se.lietunova.tasks;

import java.io.Serializable;
import java.util.stream.Stream;

public abstract class AbstractTaskList implements Iterable<Task>, Serializable, Cloneable {

    public abstract Stream<Task> getStream();

    public abstract ListTypes.types getType();

    public abstract void add(Task task);

    public abstract boolean remove(Task task);

    public abstract int size();

    public abstract Task getTask(int index);

}