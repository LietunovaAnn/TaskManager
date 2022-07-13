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
        editTask(taskList);
        System.out.println("Task was changed.");
        return Controller.MAIN_MENU_ACTION;
    }

    public void editTask(AbstractTaskList taskList) {
        TaskListView.showAllTasks(taskList);
        System.out.println("Enter the index of the task: ");
        int index = Integer.parseInt(scanner.nextLine());
        Task changeableTask = taskList.getTask(index);
        System.out.println("Do you want to change name task? (yes, no): ");
        String changeName = MainView.checkChoosingYesNo();
        if (changeName.equals("yes")) {
            System.out.print("Enter new name task: ");
            String newTitle = scanner.nextLine();
            changeableTask.setTitle(newTitle);
        }

        System.out.println("Do you want to change time task? (yes, no): ");
        String changeTime = MainView.checkChoosingYesNo();
        if (changeTime.equals("yes")) {
            if (changeableTask.isRepeated()) {
                LocalDateTime[] ldt = MainView.checkEnteredStartEndLTD();
                System.out.print("Enter interval: ");
                int interval = Integer.parseInt(scanner.nextLine());
                changeableTask.setTime(ldt[0], ldt[1], interval);
            } else {
                System.out.print("Enter data and time (dd MM uuuu HH:mm): ");
                LocalDateTime newTime = MainView.checkEnteredLTD();
                changeableTask.setTime(newTime);
            }
        }

    }
}
