package ua.edu.sumdu.j2se.lietunova.tasks.view;

import ua.edu.sumdu.j2se.lietunova.tasks.controller.Controller;
import ua.edu.sumdu.j2se.lietunova.tasks.model.AbstractTaskList;

import java.util.Scanner;

public class TaskListView implements View {
    private static final Scanner scanner = new Scanner(System.in);

    public static void showAllTasks(AbstractTaskList taskList) {
        System.out.println("View all tasks: ");
        for (int i = 1; i <= taskList.size(); i++) {
            System.out.println(i + ". " + taskList.getTask(i - 1));
        }
//        } else {
//            System.out.println("Task list is empty");
//        }
    }

    public static void showSelectedTask(AbstractTaskList taskList) {
        System.out.print("Do you want to see a specific task? (yes , no): ");
        String userChoice = UserScanner.checkChoosingYesNo();
        if (userChoice.equals("yes")) {
            System.out.println("Enter task index: ");
            int index = UserScanner.checkChoosingRightNumber(1, taskList.size());
            System.out.println(taskList.getTask(index - 1));
        }
    }

    @Override
    public int printInfo(AbstractTaskList taskList) {
        if (taskList.size() == 0) {
            System.out.println("Task list is empty");
        } else {
            showAllTasks(taskList);
            showSelectedTask(taskList);
            System.out.println("If you want to back in main menu, enter any key.");
            scanner.nextLine();
        }
        return Controller.MAIN_MENU_ACTION;
    }
}
