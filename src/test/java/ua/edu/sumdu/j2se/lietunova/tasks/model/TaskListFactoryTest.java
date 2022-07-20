package ua.edu.sumdu.j2se.lietunova.tasks.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskListFactoryTest {

    @Test
    void checkCreateTaskListArray() {
        AbstractTaskList array = TaskListFactory.createTaskList(ListTypes.types.ARRAY);
        assertEquals(array.getType(), ListTypes.types.ARRAY);
    }

    @Test
    void checkCreateTaskListLinked() {
        AbstractTaskList linked = TaskListFactory.createTaskList(ListTypes.types.LINKED);
        assertEquals(linked.getType(), ListTypes.types.LINKED);
    }
}