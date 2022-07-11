package ua.edu.sumdu.j2se.lietunova.tasks.view;

import ua.edu.sumdu.j2se.lietunova.tasks.controller.Controller;
import ua.edu.sumdu.j2se.lietunova.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.lietunova.tasks.model.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

public class AddTaskView implements View {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public int printInfo(AbstractTaskList taskList) {
        try {
            printValueAddTask(taskList);
        } catch (IOException e) {
            System.out.println("Wrong input");
        }

        System.out.println("New task was added.");
        return Controller.MAIN_MENU_ACTION;
    }

    public void printValueAddTask(AbstractTaskList taskList) throws IOException {
        System.out.print("Enter name task: ");
        String title = reader.readLine();
        boolean active = false;
        for (; ; ) {
            System.out.println("Task is active? (yes , no):");
            String activeChoice = reader.readLine();
            if (activeChoice.equals("yes")) {
                active = true;
                break;
            } else if (activeChoice.equals("no")) {
                break;
            }
            System.out.println("Incorrect format. Please, enter the correct answer.");
        }
        for (; ; ) {
            System.out.println("Task is repeated? (yes , no):");
            String repeatedChoice = reader.readLine();
            if (repeatedChoice.equals("yes")) {
                LocalDateTime[] ldt = MainView.checkEnteredStartEndLTD();
                System.out.print("Enter interval: ");
                int interval = Integer.parseInt(reader.readLine());
                Task newTask = new Task(title, ldt[0], ldt[1], interval);
                newTask.setActive(active);
                taskList.add(newTask);
                break;
            } else if (repeatedChoice.equals("no")) {
                System.out.print("Enter data and time (dd MM uuuu HH:mm): ");
                LocalDateTime timeLD = MainView.checkEnteredLTD(reader.readLine());
                Task newTask = new Task(title, timeLD);
                newTask.setActive(active);
                taskList.add(newTask);
                break;
            }
            System.out.println("Incorrect format. Please, enter the correct answer.");
        }

    }
}
