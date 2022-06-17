package ua.edu.sumdu.j2se.lietunova.tasks;

import java.util.Objects;

public class Task implements Cloneable {
    private String title;
    private int time;
    private int startTime;
    private int endTime;
    private int repeatInterval;
    private boolean active;
    private boolean repeated;

    /**
     * Конструктор створює неактивне завдання, яке виконується один раз.
     *
     * @param title назва завдання
     * @param time  час виконання завдання
     */
    public Task(String title, int time) {
        if (title == null) {
            throw new NullPointerException("Parameter title cannot be null!");
        }
        if (time < 0) {
            throw new IllegalArgumentException("Time cannot be negative!");
        }
        this.title = title;
        this.time = time;
        repeated = false;
    }

    /**
     * Конструктор створює неактивне завдання, яке виконується
     * заданому проміжку часу із заданим інтервалом.
     *
     * @param title    назва завдання
     * @param start    час початку виконання завдання
     * @param end      час закінчення виконання завдання
     * @param interval інтервал виконання завдання
     */
    public Task(String title, int start, int end, int interval) {
        if (title == null) {
            throw new NullPointerException("Parameter title cannot be null!");
        }
        if (start < 0 || end < 0) {
            throw new IllegalArgumentException("Time (start, end) cannot be negative!");
        }
        if (interval <= 0) {
            throw new IllegalArgumentException("Interval have to be >= 0!");
        }
        this.title = title;
        this.startTime = start;
        this.endTime = end;
        this.repeatInterval = interval;
        repeated = true;
    }

    /**
     * Метод повертає назву завдання
     *
     * @return назва завдання
     */
    public String getTitle() {
        return title;
    }

    /**
     * Метод встановлює назву задачі
     *
     * @param title значення не повинно бути порожнім або null
     */
    public void setTitle(String title) {
        if (title == null) {
            throw new NullPointerException("Parameter title cannot be null!");
        }
        this.title = title;
    }

    /**
     * Метод для зчитування стану задачі
     *
     * @return повертае стан задачі
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Метод для встановлення стану задачі
     *
     * @param active булеве значення
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Метод для зчитування часу виконання задачи, що не повторюються
     *
     * @return повертае час виконання задачи; у разі, якщо задача повторюється
     * метод повертає час початку повторення
     */
    public int getTime() {
        if (repeated) {
            return startTime;
        }
        return time;
    }

    /**
     * Метод для зміни часу виконання для задач, що не повторюються
     *
     * @param time час виконання задачи: у разі, якщо задача повторювалась,
     *             вона має стати такою, що не повторюється
     */
    public void setTime(int time) {
        if (time < 0) {
            throw new IllegalArgumentException("Time cannot be negative!");
        }
        this.time = time;
        if (repeated) {
            repeated = false;
        }
    }

    /**
     * Метод для зчитування начала часу виконання задачи, що повторюються
     *
     * @return повертае час начала виконання задачи; у разі, якщо
     * задача не повторюється метод повертає час виконання задачі
     */
    public int getStartTime() {
        if (repeated) {
            return startTime;
        }
        return time;
    }

    /**
     * Метод для зчитування закінчення часу виконання задачи, що повторюються
     *
     * @return повертае час закінчення виконання задачи; у разі, якщо
     * задача не повторюється метод повертає час виконання задачі
     */
    public int getEndTime() {
        if (repeated) {
            return endTime;
        }
        return time;
    }

    /**
     * Метод для зчитування інтервалу
     *
     * @return повертае інтервал, у разі, якщо задача
     * не повторюється метод повертає 0
     */
    public int getRepeatInterval() {
        if (repeated) {
            return repeatInterval;
        }
        return 0;
    }

    /**
     * Метод для зміни часу виконання для задач, що повторюються
     *
     * @param start    час початку виконання завдання
     * @param end      час закінчення виконання завдання
     * @param interval інтервал виконання завдання
     */
    public void setTime(int start, int end, int interval) {
        if (start < 0 || end < 0) {
            throw new IllegalArgumentException("Time (start, end) cannot be negative!");
        }
        if (interval <= 0) {
            throw new IllegalArgumentException("Interval have to be >= 0!");
        }
        this.startTime = start;
        this.endTime = end;
        this.repeatInterval = interval;
        if (!repeated) {
            repeated = true;
        }
    }

    /**
     * Метод для перевірки повторюваності задачі
     *
     * @return повертає булеве значення
     */
    public boolean isRepeated() {
        return repeated;
    }

    /**
     * Метод знаходження наступного моменту виконання задачі
     *
     * @param current вказаний час
     * @return повертає час наступного виконання задачі;
     * якщо після вказаного часу задача не виконується, то
     * метод повертає -1
     */
    public int nextTimeAfter(int current) {
        if (current < 0) {
            throw new IllegalArgumentException("Parameter current cannot be negative!");
        }
        if (active) {
            if (repeated) {
                int nextStartTime = startTime;
                while (current >= nextStartTime) {
                    nextStartTime += repeatInterval;
                    if (nextStartTime > endTime) {
                        return -1;
                    }
                }
                return nextStartTime;

            } else {
                if (current >= time) {
                    return -1;
                }
                return time;
            }
        }
        return -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        if (repeated) {
            return startTime == task.startTime && endTime == task.endTime &&
                    repeatInterval == task.repeatInterval && Objects.equals(title, task.title);
        }
        return time == task.time && Objects.equals(title, task.title);
    }

    @Override
    public int hashCode() {
        if (repeated) {
            return Objects.hash(title, startTime, endTime, repeatInterval);
        }
        return Objects.hash(title, time);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        if (repeated) {
            return "Task{" +
                    "title='" + title + '\'' +
                    ", startTime=" + startTime +
                    ", endTime=" + endTime +
                    ", repeatInterval=" + repeatInterval +
                    '}';
        }
        return "Task{" +
                "title='" + title + '\'' +
                ", time=" + time +
                '}';
    }

}
