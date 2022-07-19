package ua.edu.sumdu.j2se.lietunova.tasks.view;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;


public class AddTaskViewTest extends TestCase {
    private static AddTaskView view;
//    @Before
//    public static void init() {
//        view = new AddTaskView();
//    }

    @Test
    public void testPrintActiveNewTaskYes() {
        InputStream inputStream = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("yes".getBytes());

        System.setIn(in);
        view = new AddTaskView();
        assertTrue(view.printActiveNewTask());
        System.setIn(inputStream);
    }

    @Test
    public void testPrintActiveNewTaskNo() {
        InputStream inputStream = System.in;
        ByteArrayInputStream in1 = new ByteArrayInputStream("no".getBytes());
        System.setIn(in1);
        view = new AddTaskView();
        assertFalse(view.printActiveNewTask());
        System.setIn(inputStream);
    }

}