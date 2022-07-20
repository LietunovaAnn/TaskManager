package ua.edu.sumdu.j2se.lietunova.tasks.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.edu.sumdu.j2se.lietunova.tasks.model.exception.WrongArgumentException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class UserScanner {
    public static final DateTimeFormatter dTF = DateTimeFormatter.ofPattern("dd MM yyyy HH:mm");
    private static final Scanner scanner = new Scanner(System.in);
    private static final Logger logger = LoggerFactory.getLogger(UserScanner.class);

    private static LocalDateTime readEnteredLTD() throws DateTimeParseException {
        String ldt = scanner.nextLine();
        return LocalDateTime.parse(ldt, dTF);
    }

    public static LocalDateTime checkEnteredLTD() {
        LocalDateTime correctLDT;
        try {
            correctLDT = readEnteredLTD();
        } catch (DateTimeParseException e) {
            System.out.println("Incorrect format. Please, enter the correct data and time (dd MM yyyy HH:mm)");
            logger.error("Incorrect format LocalDateTime ", e);
            correctLDT = checkEnteredLTD();
        }
        return correctLDT;
    }


    public static LocalDateTime[] checkEnteredStartEndLTD() {
        System.out.print("Enter start data and time (dd MM yyyy HH:mm): ");
        LocalDateTime startTimeLD = checkEnteredLTD();
        System.out.print("Enter end data and time (dd MM yyyy HH:mm): ");
        LocalDateTime endTimeLD = checkEnteredLTD();
        while (startTimeLD.isAfter(endTimeLD)) {
            System.out.print("Incorrect: endTimeLD > startTimeLD." +
                    " Please, enter the correct end data and time (dd MM yyyy HH:mm): ");
            endTimeLD = checkEnteredLTD();
        }
        return new LocalDateTime[]{startTimeLD, endTimeLD};
    }

    private static int readUserChoiceNumber(int first, int last) throws WrongArgumentException, NumberFormatException {
        int enter = Integer.parseInt(scanner.nextLine());
        if (enter < first || enter > last) {
            throw new WrongArgumentException();
        }
        return enter;
    }

    public static int checkChoosingRightNumber(int first, int last) {
        int value;
        try {
            value = readUserChoiceNumber(first, last);
        } catch (WrongArgumentException | NumberFormatException e) {
            System.out.println("Incorrect format. Please, enter the correct number.");
            logger.error("Incorrect format ", e);
            value = checkChoosingRightNumber(first, last);
        }
        return value;
    }

    private static String readUserChoiceYesNo() throws WrongArgumentException {
        String enter = scanner.nextLine();
        if (!(enter.equals("yes") || enter.equals("no"))) {
            throw new WrongArgumentException();
        }
        return enter;
    }

    public static String checkChoosingYesNo() {
        String userChoice;
        try {
            userChoice = readUserChoiceYesNo();
        } catch (WrongArgumentException | NumberFormatException e) {
            System.out.println("Incorrect format. Please, enter the correct number.");
            logger.error("Incorrect format ", e);
            userChoice = checkChoosingYesNo();
        }
        return userChoice;
    }
}
