package ua.edu.sumdu.j2se.lietunova.tasks.model;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class TaskIOTest {

    @Test
    void readText_whenNotFile_NotException() {
        assertDoesNotThrow(() -> TaskIO.readText(new ArrayTaskList(), new File("notFile")));
    }
}