package ua.edu.sumdu.j2se.lietunova.tasks.view;

import ua.edu.sumdu.j2se.lietunova.tasks.controller.Controller;
import ua.edu.sumdu.j2se.lietunova.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.lietunova.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.lietunova.tasks.model.Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.SortedMap;

public class CalenderView implements View {
    private static final Scanner scanner = new Scanner(System.in);

    @Override
    public int printInfo(AbstractTaskList taskList) {
        choiceCalender(taskList);
        System.out.println("If you want to back in main menu, enter any key.");
        scanner.nextLine();
        return Controller.MAIN_MENU_ACTION;
    }

    public void choiceCalender(AbstractTaskList taskList) {
        System.out.println("Chose necessary time interval: ");

        LocalDateTime[] ldt;
        ldt = UserScanner.checkEnteredStartEndLTD();

        SortedMap<LocalDateTime, ArrayTaskList> calendarTaskView = Tasks.calendar(taskList, ldt[0], ldt[1]);
        for (SortedMap.Entry<LocalDateTime, ArrayTaskList> entry : calendarTaskView.entrySet()) {
            System.out.println(entry.getKey().format(DateTimeFormatter.ofPattern("dd MM YYYY HH:mm:ss")) + ": " +
                    entry.getValue().toString());
        }
    }

}
