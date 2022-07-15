package ua.edu.sumdu.j2se.lietunova.tasks.view;

import ua.edu.sumdu.j2se.lietunova.tasks.model.AbstractTaskList;

public class MainView implements View {
    @Override
    public int printInfo(AbstractTaskList taskList) {
        System.out.println("Activity:");
        System.out.println("1. Check your tasks from your task list.");
        System.out.println("2. Add new task to your task list.");
        System.out.println("3. Edit tasks from your task list.");
        System.out.println("4. Remove tasks from your task list.");
        System.out.println("5. View a calendar for a period of time.");
        System.out.println("6. Exit.");
        System.out.print("Choose activity (enter necessary number): ");
        int value = UserScanner.readUserChoiceNumber(1, 6);
        return value;
    }

}
