package ua.edu.sumdu.j2se.lietunova.tasks.view;

import ua.edu.sumdu.j2se.lietunova.tasks.controller.Controller;
import ua.edu.sumdu.j2se.lietunova.tasks.model.AbstractTaskList;

import java.util.Scanner;

public class RemoveTaskView implements View {
    private static final Scanner scanner = new Scanner(System.in);
    @Override
    public int printInfo(AbstractTaskList taskList) {
        removeTask(taskList);
        System.out.println("Task was removed");
        return Controller.MAIN_MENU_ACTION;
    }

    public void removeTask(AbstractTaskList taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + " " + taskList.getTask(i));
        }
        System.out.println("Enter the index of the task to delete: ");

        int index = MainView.checkChoosingRightNumber(0, taskList.size() - 1, scanner);
        taskList.remove(taskList.getTask(index));
    }
}
