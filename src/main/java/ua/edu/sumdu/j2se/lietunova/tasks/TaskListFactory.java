package ua.edu.sumdu.j2se.lietunova.tasks;

public class TaskListFactory {

    public static AbstractTaskList createTaskList(ListTypes.types type) throws Exception {

        switch (type) {
            case ARRAY:
                return new ArrayTaskList();
            case LINKED:
                return new LinkedTaskList();
            default:
                throw new Exception("Unknown type");
        }
    }
}
