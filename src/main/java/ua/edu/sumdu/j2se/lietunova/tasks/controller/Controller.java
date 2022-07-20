package ua.edu.sumdu.j2se.lietunova.tasks.controller;

import ua.edu.sumdu.j2se.lietunova.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.lietunova.tasks.view.View;


public abstract class Controller {
    public static final int MAIN_MENU_ACTION = 0;
    public static final int LIST_ACTION = 1;
    public static final int ADD_TASK_ACTION = 2;
    public static final int EDIT_TASK_ACTION = 3;
    public static final int REMOVE_TASK_ACTION = 4;
    public static final int CALENDAR_ACTION = 5;
    public static final int FINISH_ACTION = 6;

    protected View view;
    protected int actionToPerform = 0;

    public Controller(View view, int actionToPerform) {
        this.view = view;
        this.actionToPerform = actionToPerform;
    }

    public boolean canProcess(int action) {
        return action == actionToPerform;
    }

    public int process(AbstractTaskList taskList) {
        return view.printInfo(taskList);
    }
}
