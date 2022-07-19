package ua.edu.sumdu.j2se.lietunova.tasks.view;

import ua.edu.sumdu.j2se.lietunova.tasks.controller.Controller;
import ua.edu.sumdu.j2se.lietunova.tasks.model.AbstractTaskList;

public class RemoveTaskView implements View {
    @Override
    public int printInfo(AbstractTaskList taskList) {
        if (taskList.size() == 0) {
            System.out.println("Task list is empty");
        } else {
            removeTask(taskList);
            System.out.println("Task was removed.\n");
        }
        return Controller.MAIN_MENU_ACTION;
    }

    public void removeTask(AbstractTaskList taskList) {
        TaskListView.showAllTasks(taskList);
        System.out.println("Enter the index of the task to delete: ");
        int index = UserScanner.checkChoosingRightNumber(1, taskList.size());
        taskList.remove(taskList.getTask(index - 1));
    }

}
