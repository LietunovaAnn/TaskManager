package ua.edu.sumdu.j2se.lietunova.tasks.view;

import ua.edu.sumdu.j2se.lietunova.tasks.controller.Controller;
import ua.edu.sumdu.j2se.lietunova.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.lietunova.tasks.model.Task;

import java.time.LocalDateTime;
import java.util.Scanner;

public class AddTaskView implements View {
    private static final Scanner scanner = new Scanner(System.in);
    @Override
    public int printInfo(AbstractTaskList taskList) {
        printValueAddTask(taskList);
        System.out.println("New task was added.");
        return Controller.MAIN_MENU_ACTION;
    }

    public void printValueAddTask(AbstractTaskList taskList) {
        System.out.print("Enter name task: ");
        String title = scanner.nextLine();
        boolean active = false;
        System.out.println("Task is active? (yes , no):");
        String activeChoice = MainView.checkChoosingYesNo();
        if (activeChoice.equals("yes")) {
            active = true;
        }
        System.out.println("Task is repeated? (yes , no):");
        String repeatedChoice = MainView.checkChoosingYesNo();
        if (repeatedChoice.equals("yes")) {
            LocalDateTime[] ldt = MainView.checkEnteredStartEndLTD();
            System.out.print("Enter interval: ");
            int interval = Integer.parseInt(scanner.nextLine());
            Task newTask = new Task(title, ldt[0], ldt[1], interval);
            newTask.setActive(active);
            taskList.add(newTask);
        } else if (repeatedChoice.equals("no")) {
            System.out.print("Enter data and time (dd MM uuuu HH:mm): ");
            LocalDateTime timeLD = MainView.checkEnteredLTD();
            Task newTask = new Task(title, timeLD);
            newTask.setActive(active);
            taskList.add(newTask);
        }
    }
}
