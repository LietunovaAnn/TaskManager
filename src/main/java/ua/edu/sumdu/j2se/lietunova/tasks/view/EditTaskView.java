package ua.edu.sumdu.j2se.lietunova.tasks.view;

import ua.edu.sumdu.j2se.lietunova.tasks.controller.Controller;
import ua.edu.sumdu.j2se.lietunova.tasks.model.AbstractTaskList;

public class EditTaskView implements View {
    @Override
    public int printInfo(AbstractTaskList taskList) {
        System.out.println("Task was changed.");
        return Controller.MAIN_MENU_ACTION;
    }

}
