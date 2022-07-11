package ua.edu.sumdu.j2se.lietunova.tasks.view;

import ua.edu.sumdu.j2se.lietunova.tasks.controller.Controller;
import ua.edu.sumdu.j2se.lietunova.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.lietunova.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.lietunova.tasks.model.Tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.SortedMap;

public class CalenderView implements View {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public int printInfo(AbstractTaskList taskList) {
        System.out.println("Chose necessary time interval: ");
        LocalDateTime[] ldt = new LocalDateTime[2];
        try {
            ldt = MainView.checkEnteredStartEndLTD();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SortedMap<LocalDateTime, ArrayTaskList> calendarTaskView = Tasks.calendar(taskList, ldt[0], ldt[1]);
        for (SortedMap.Entry<LocalDateTime, ArrayTaskList> entry : calendarTaskView.entrySet()) {
            System.out.println(entry.getKey().format(DateTimeFormatter.ofPattern("dd MM uuuu HH:mm")) + ": " +
                    entry.getValue().toString());
        }
        System.out.println("If you want to back in main menu, enter any key.");
        try {
            reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Controller.MAIN_MENU_ACTION;
    }


}
