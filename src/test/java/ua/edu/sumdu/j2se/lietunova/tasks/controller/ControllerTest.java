package ua.edu.sumdu.j2se.lietunova.tasks.controller;

import org.junit.jupiter.api.Test;
import ua.edu.sumdu.j2se.lietunova.tasks.view.TaskListView;

import static org.junit.jupiter.api.Assertions.assertFalse;

class ControllerTest {

    @Test
    void canProcessFalse() {
        Controller controller = new TaskListController(new TaskListView(), Controller.LIST_ACTION);
        assertFalse(controller.canProcess(-1));
    }
}