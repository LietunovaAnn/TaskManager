package ua.edu.sumdu.j2se.lietunova.tasks.controller;

import ua.edu.sumdu.j2se.lietunova.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.lietunova.tasks.view.*;

import java.util.ArrayList;
import java.util.List;

public class MainController extends Controller {
    private final AbstractTaskList taskList;
    private final List<Controller> controllerList = new ArrayList<>();

    public MainController(AbstractTaskList taskList, View mainView) {
        super(mainView, Controller.MAIN_MENU_ACTION);
        this.taskList = taskList;

        controllerList.add(this);
        controllerList.add(new TaskListController(new TaskListView(), Controller.LIST_ACTION));
        controllerList.add(new AddTaskController(new AddTaskView(), Controller.ADD_TASK_ACTION));
        controllerList.add(new EditTaskController(new EditTaskView(), Controller.EDIT_TASK_ACTION));
        controllerList.add(new RemoveTaskController(new RemoveTaskView(), Controller.REMOVE_TASK_ACTION));
        controllerList.add(new CalenderController(new CalenderView(), Controller.CALENDAR_ACTION));
    }


    @Override
    public int process(AbstractTaskList taskList) {
        int action = view.printInfo(taskList);
        while (action != FINISH_ACTION) {
            for (Controller controller : controllerList) {
                if (controller.canProcess(action)) {
                    action = controller.process(this.taskList);
                }
            }
        }
        return FINISH_ACTION;
    }
}
