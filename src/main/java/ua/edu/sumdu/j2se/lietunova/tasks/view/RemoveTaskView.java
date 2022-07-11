package ua.edu.sumdu.j2se.lietunova.tasks.view;

import ua.edu.sumdu.j2se.lietunova.tasks.controller.Controller;
import ua.edu.sumdu.j2se.lietunova.tasks.model.AbstractTaskList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RemoveTaskView implements View {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public int printInfo(AbstractTaskList taskList) {
        try {
            removeTask(taskList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Task was Removed");
        return Controller.MAIN_MENU_ACTION;
    }

    public void removeTask(AbstractTaskList taskList) throws IOException {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + " " + taskList.getTask(i));
        }
        System.out.println();
        int index = Integer.parseInt(reader.readLine());
        taskList.remove(taskList.getTask(index));
    }
}
