package ua.edu.sumdu.j2se.lietunova.tasks.notifications;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.edu.sumdu.j2se.lietunova.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.lietunova.tasks.model.Task;
import ua.edu.sumdu.j2se.lietunova.tasks.model.Tasks;
import ua.edu.sumdu.j2se.lietunova.tasks.view.UserScanner;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.SortedMap;
import java.util.concurrent.TimeUnit;

public class Notificator extends Thread {
    private static final Logger logger = LoggerFactory.getLogger(Notificator.class);
    AbstractTaskList taskList;

    public Notificator(AbstractTaskList taskList) {
        this.taskList = taskList;
    }

    public static void displayTray(String task) {
        try {
            SystemTray tray = SystemTray.getSystemTray();

            Image image = Toolkit.getDefaultToolkit().createImage("some-icon.png");
            TrayIcon trayIcon = new TrayIcon(image);
            tray.add(trayIcon);

            trayIcon.displayMessage("Attention", task, TrayIcon.MessageType.INFO);
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    public void checkCalendar(AbstractTaskList taskList) {
        SortedMap<LocalDateTime, Set<Task>> temp = Tasks.calendar(taskList, LocalDateTime.now(), LocalDateTime.now().plusMinutes(1));
        for (SortedMap.Entry<LocalDateTime, Set<Task>> entry : temp.entrySet()) {
            for (Task task : entry.getValue()) {
                String message = "Tasks: '" + task.getTitle() + "' should start running at " + task.getTime().format(UserScanner.dTF);
                displayTray(message);
            }
        }
        try {
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void run() {
        for (; ; ) {
            checkCalendar(taskList);
        }
    }

}
