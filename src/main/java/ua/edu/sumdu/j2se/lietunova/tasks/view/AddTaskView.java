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
        printRepeatNewTask(taskList);
        System.out.println("New task was added.\n");
        return Controller.MAIN_MENU_ACTION;

    }

    public String printNameNewTask() {
        System.out.print("Enter name task: ");
        return scanner.nextLine();
    }

    public void printRepeatNewTask(AbstractTaskList taskList) {
        System.out.print("Task is repeated? (yes , no): ");
        String repeatedChoice = UserScanner.checkChoosingYesNo();
        if (repeatedChoice.equals("yes")) {
            taskList.add(printStartEndTimeNewTask());
        } else {
            taskList.add(printTimeNewTask());
        }
    }

    public Task printStartEndTimeNewTask() {
        LocalDateTime[] ldt = UserScanner.checkEnteredStartEndLTD();
        System.out.print("Enter interval: ");
        int interval = UserScanner.checkChoosingRightNumber(1, Integer.MAX_VALUE);
        Task newTaskRepeat = new Task(printNameNewTask(), ldt[0], ldt[1], interval);
        newTaskRepeat.setActive(printActiveNewTask());
        return newTaskRepeat;
    }

    public Task printTimeNewTask() {
        System.out.print("Enter data and time (dd MM uuuu HH:mm): ");
        LocalDateTime timeLD = UserScanner.checkEnteredLTD();
        Task newTask = new Task(printNameNewTask(), timeLD);
        newTask.setActive(printActiveNewTask());
        return newTask;
    }

    public boolean printActiveNewTask() {
        boolean active = false;
        System.out.print("Task is active? (yes , no): ");
        String activeChoice = UserScanner.checkChoosingYesNo();
        if (activeChoice.equals("yes")) {
            active = true;
        }
        return active;
    }
}
