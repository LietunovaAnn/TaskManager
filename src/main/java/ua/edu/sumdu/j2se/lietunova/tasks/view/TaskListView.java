package ua.edu.sumdu.j2se.lietunova.tasks.view;

import ua.edu.sumdu.j2se.lietunova.tasks.controller.Controller;
import ua.edu.sumdu.j2se.lietunova.tasks.model.AbstractTaskList;

import java.util.Scanner;

public class TaskListView implements View {
    private static final Scanner scanner = new Scanner(System.in);

    public static void showAllTasks(AbstractTaskList taskList) {
        System.out.println("View all tasks: ");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + " " + taskList.getTask(i));
        }
    }

    @Override
    public int printInfo(AbstractTaskList taskList) {
        showAllTasks(taskList);
        System.out.println();
        return Controller.MAIN_MENU_ACTION;
    }

    public void showSelectedTask(AbstractTaskList taskList) {
        System.out.println("Enter task index: ");
        int index = Integer.parseInt(scanner.nextLine());
        System.out.println(taskList.getTask(index));
    }
}
