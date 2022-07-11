package ua.edu.sumdu.j2se.lietunova.tasks.view;

import ua.edu.sumdu.j2se.lietunova.tasks.controller.Controller;
import ua.edu.sumdu.j2se.lietunova.tasks.model.AbstractTaskList;

public class TaskListView implements View {
    @Override
    public int printInfo(AbstractTaskList taskList) {
        System.out.println(taskList.toString());
        return Controller.MAIN_MENU_ACTION;
    }
}
