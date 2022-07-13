package ua.edu.sumdu.j2se.lietunova.tasks.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.edu.sumdu.j2se.lietunova.tasks.model.AbstractTaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class MainView implements View {
    private final static Logger logger = LoggerFactory.getLogger(MainView.class);
    private static final Scanner scanner = new Scanner(System.in);

    public static LocalDateTime checkEnteredLTD() {
        DateTimeFormatter dTF = DateTimeFormatter.ofPattern("dd MM uuuu HH:mm");
        String ldt = scanner.nextLine();
        LocalDateTime correctLDT = null;
        try {
            correctLDT = LocalDateTime.parse(ldt, dTF);
        } catch (DateTimeParseException e) {
            System.out.println("Incorrect format. Please, enter the correct data and time (dd MM uuuu HH:mm)");
            logger.error(e.getParsedString());
            checkEnteredLTD();
        }

        return correctLDT;
    }

    public static LocalDateTime[] checkEnteredStartEndLTD() {
        System.out.print("Enter start data and time (dd MM uuuu HH:mm): ");
        LocalDateTime startTimeLD = checkEnteredLTD();
        System.out.print("Enter end data and time (dd MM uuuu HH:mm): ");
        LocalDateTime endTimeLD = checkEnteredLTD();
        while (startTimeLD.isAfter(endTimeLD)) {
            System.out.print("Incorrect: endTimeLD > startTimeLD." +
                    " Please, enter the correct end data and time (dd MM uuuu HH:mm): ");
            endTimeLD = MainView.checkEnteredLTD();
        }
        return new LocalDateTime[]{startTimeLD, endTimeLD};
    }

    public static int checkChoosingRightNumber(int first, int last, Scanner scanner) {
        while (!(scanner.hasNextInt())) {
            System.out.print("Your input is incorrect! Enter number: ");
            scanner.nextLine();
        }
        int enter = scanner.nextInt();
        if (enter < first || enter > last) {
            System.out.println("Incorrect format. Please, enter the correct number.");
            // throw new WrongArgumentException("Incorrect format. Please, enter the correct number.");
            checkChoosingRightNumber(first, last, scanner);
        }
        return enter;
    }

    public static String checkChoosingYesNo() {
        String enter = scanner.nextLine();
        if (!(enter.equals("yes") || enter.equals("no"))) {
            System.out.println("Incorrect format. Please, enter the correct answer.");
            checkChoosingYesNo();
        }
        return enter;
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
        int value = checkChoosingRightNumber(1, 6, scanner);
        return value;
    }
}
