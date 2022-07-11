package ua.edu.sumdu.j2se.lietunova.tasks.view;

import ua.edu.sumdu.j2se.lietunova.tasks.model.AbstractTaskList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MainView implements View {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static LocalDateTime checkEnteredLTD(String dataTime) {
        DateTimeFormatter dTF = DateTimeFormatter.ofPattern("dd MM uuuu HH:mm");
        LocalDateTime correctLDT = null;
        try {
            correctLDT = LocalDateTime.parse(dataTime, dTF);
        } catch (Exception e) {
            System.out.println("Incorrect format. Please, enter the correct data and time (dd MM uuuu HH:mm)");
            checkEnteredLTD(dataTime);
        }
        return correctLDT;
    }

    public static LocalDateTime[] checkEnteredStartEndLTD() throws IOException {
        System.out.print("Enter start data and time (dd MM uuuu HH:mm): ");
        LocalDateTime startTimeLD = checkEnteredLTD(reader.readLine());
        System.out.print("Enter end data and time (dd MM uuuu HH:mm): ");
        LocalDateTime endTimeLD = checkEnteredLTD(reader.readLine());
        while (startTimeLD.isAfter(endTimeLD)) {
            System.out.print("Incorrect: endTimeLD > startTimeLD." +
                    " Please, enter the correct end data and time (dd MM uuuu HH:mm): ");
            endTimeLD = MainView.checkEnteredLTD(reader.readLine());
        }
        return new LocalDateTime[]{startTimeLD, endTimeLD};
    }

    @Override
    public int printInfo(AbstractTaskList taskList) {
        System.out.println("Choose activity (enter necessary number):");
        System.out.println("1. Check your tasks from your task list.");
        System.out.println("2. Add new task to your task list.");
        System.out.println("3. Edit tasks from your task list.");
        System.out.println("4. Remove tasks from your task list.");
        System.out.println("5. View a calendar for a period of time.");
        System.out.println("6. Exit.");
        int value = 0;
        try {
            value = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }

}
