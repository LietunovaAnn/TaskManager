package ua.edu.sumdu.j2se.lietunova.tasks.view;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.time.format.DateTimeParseException;

public class UserScannerTest extends TestCase {


    @Test
    public void testReadEnteredLTD() {
        ByteArrayInputStream in = new ByteArrayInputStream("2207 2022 22:50".getBytes());
        System.setIn(in);
        Assert.assertThrows(DateTimeParseException.class, () -> UserScanner.readEnteredLTD());
    }

    @Test
    public void testReadEnteredLTD2() {
        ByteArrayInputStream in = new ByteArrayInputStream("22-07-2022 22:50".getBytes());
        System.setIn(in);
        Assert.assertThrows(DateTimeParseException.class, () -> UserScanner.readEnteredLTD());
    }


}