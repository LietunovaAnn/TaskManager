package ua.edu.sumdu.j2se.lietunova.tasks;


import ua.edu.sumdu.j2se.lietunova.tasks.controller.Controller;
import ua.edu.sumdu.j2se.lietunova.tasks.controller.MainController;
import ua.edu.sumdu.j2se.lietunova.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.lietunova.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.lietunova.tasks.model.TaskIO;
import ua.edu.sumdu.j2se.lietunova.tasks.view.MainView;
import ua.edu.sumdu.j2se.lietunova.tasks.view.View;

import java.io.File;


public class Main {

	public static void main(String[] args) {
		System.out.println("Task Manager was started ...");
		AbstractTaskList taskList = new ArrayTaskList();
		TaskIO.readText(taskList, new File("SaveTaskManager.txt"));
//		Notificator notificator = new Notificator(taskList);
//		notificator.setDaemon(true);
//		notificator.start();
		View mainView = new MainView();
		Controller mainController = new MainController(taskList, mainView);
		mainController.process(null);
		System.out.println("Task Manager was closed ...");
		TaskIO.writeText(taskList, new File("SaveTaskManager.txt"));
	}
}
