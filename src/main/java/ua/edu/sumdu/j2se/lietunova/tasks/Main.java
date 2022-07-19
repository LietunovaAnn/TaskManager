package ua.edu.sumdu.j2se.lietunova.tasks;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.edu.sumdu.j2se.lietunova.tasks.controller.Controller;
import ua.edu.sumdu.j2se.lietunova.tasks.controller.MainController;
import ua.edu.sumdu.j2se.lietunova.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.lietunova.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.lietunova.tasks.model.TaskIO;
import ua.edu.sumdu.j2se.lietunova.tasks.notifications.Notificator;
import ua.edu.sumdu.j2se.lietunova.tasks.view.MainView;
import ua.edu.sumdu.j2se.lietunova.tasks.view.View;

import java.io.File;



public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        System.out.println("--Welcome to the Task Manager--");
        logger.info("Start Task Manager");
        AbstractTaskList taskList = new ArrayTaskList();
        TaskIO.readText(taskList, new File("SaveTaskManager.txt"));
        Notificator notificator = new Notificator(taskList);
        notificator.setDaemon(true);
        notificator.start();
        View mainView = new MainView();
        Controller mainController = new MainController(taskList, mainView);
        mainController.process(null);
        System.out.println("Changes saved. Task Manager was closed.");
        TaskIO.writeText(taskList, new File("SaveTaskManager.txt"));
        logger.info("Close Task Manager and save file");

    }


}
