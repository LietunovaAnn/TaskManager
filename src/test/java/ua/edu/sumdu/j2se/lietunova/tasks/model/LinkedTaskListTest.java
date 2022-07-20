package ua.edu.sumdu.j2se.lietunova.tasks.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class LinkedTaskListTest {

    @Test
    void checkGetType() {
        LinkedTaskList list = new LinkedTaskList();
        assertEquals(list.getType(), ListTypes.types.LINKED);
        assertNotEquals(list.getType(), ListTypes.types.ARRAY);
    }
}