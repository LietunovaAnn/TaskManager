package ua.edu.sumdu.j2se.lietunova.tasks.view;

import ua.edu.sumdu.j2se.lietunova.tasks.controller.Controller;
import ua.edu.sumdu.j2se.lietunova.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.lietunova.tasks.model.Task;

import java.time.LocalDateTime;
import java.util.Scanner;

public class EditTaskView implements View {
    private static final Scanner scanner = new Scanner(System.in);

    @Override
    public int printInfo(AbstractTaskList taskList) {
        if (taskList.size() == 0) {
            System.out.println("Task list is empty");
        } else {
            TaskListView.showAllTasks(taskList);
            editSpecificTask(taskList);
            System.out.println("Task was changed.\n");
        }
        return Controller.MAIN_MENU_ACTION;
    }

    public void editSpecificTask(AbstractTaskList taskList) {
        System.out.println("Enter the index of the task: ");
        int index = UserScanner.checkChoosingRightNumber(1, taskList.size());
        Task task = taskList.getTask(index - 1);
        changeNameTask(task);
        changeTimeTask(task);
    }

    public void changeNameTask(Task task) {
        System.out.println("Do you want to change name task? (yes, no): ");
        String changeName = UserScanner.checkChoosingYesNo();
        if (changeName.equals("yes")) {
            System.out.print("Enter new name task: ");
            String newTitle = scanner.nextLine();
            task.setTitle(newTitle);
        }
    }

    public void changeTimeTask(Task task) {
        System.out.println("Do you want to change time task? (yes, no): ");
        String changeTime = UserScanner.checkChoosingYesNo();
        if (changeTime.equals("yes")) {
            if (task.isRepeated()) {
                LocalDateTime[] ldt = UserScanner.checkEnteredStartEndLTD();
                System.out.print("Enter interval: ");
                int interval = UserScanner.checkChoosingRightNumber(1, Integer.MAX_VALUE);
                task.setTime(ldt[0], ldt[1], interval);
            } else {
                System.out.print("Enter data and time (dd MM yyyy HH:mm): ");
                LocalDateTime newTime = UserScanner.checkEnteredLTD();
                task.setTime(newTime);
            }
        }
    }
}
