package ua.edu.sumdu.j2se.lietunova.tasks;

public class Task {
    private String title;
    private int time;
    private int startTime;
    private int endTime;
    private int repeatInterval;
    private  boolean active;
    private  boolean repeated;

    /**
     * Конструктор создает неактивную задачу, которая выполняется один раз.
     *
     * @param title название задачи
     * @param time время выполнения задачи
     */
    public Task(String title, int time) {
        this.title = title;
        this.time = time;
        repeated = false;
    }

    /**
     * Конструктор создает неактивную задачу, которая выполняется в
     * заданном промежутке времени с заданным интервалом.
     *
     * @param title название задачи
     * @param start время начала выполнения задачи
     * @param end время окончания выполнения задачи
     * @param interval интервал выполнения задачи
     */
    public Task(String title, int start, int end, int interval) {
        this.title = title;
        this.startTime = start;
        this.endTime = end;
        this.repeatInterval = interval;
        repeated = true;
    }

    /**
     * Метод возвращает название задачи
     *
     * @return название задачи
     */
    public String getTitle() {
        return title;
    }

    /**
     * Метод устанавливает название задачи
     *
     * @param title значение не должно быть пустым или null
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Методи для зчитування стану задачі
     *
     * @return стан задачі
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Методи для встановлення стану задачі
     *
     * @param active бул знач
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Методи для зчитування часу виконання задачи, що не повторюються
     *
     * @return повертае час виконання задачи; у разі, якщо задача повторюється метод має повертати час початку
     * повторення

     */
    public int getTime() {
        if (repeated) {
            return startTime;
        }
        return time;
    }

    /**
     * Методи для зміни часу виконання для задач, що не повторюються
     *
     *  @param time час виконання задачи
     */
    public void setTime(int time) {
        this.time = time;
        if (repeated) {
            repeated = false;
        }
    }

    /**
     *Методи для зчитування часу виконання задачи, що повторюються
     *
     * @return повертае час виконання задачи
     */
    public int getStartTime() {
        if (repeated) {
            return startTime;
        }
        return time;
    }

    /**
     *
     * @return
     */
    public int getEndTime() {
        if (repeated) {
            return endTime;
        }
        return time;
    }

    /**
     *
     * @return
     */
    public int getRepeatInterval() {
        if (repeated) {
            return repeatInterval;
        }
        return 0;
    }

    /**
     *
     * @param time
     * @param end
     * @param interval
     */
    public void setTime(int time, int end, int interval) {
        this.time = time;
        this.endTime = end;
        this.repeatInterval = interval;
        if (!repeated) {
            repeated = true;
        }
    }

    /**
     * Метод для перевірки повторюваності задачі
     *
     * @return
     */
    public boolean isRepeated() {
        return repeated;
    }

    /**
     *
     * @param current
     * @return
     */
    public int nextTimeAfter(int current){
        if (active) {
            if (repeated) {
                    int nextStartTime = startTime;
                    while (current >= nextStartTime) {
                        nextStartTime += repeatInterval;
                        if (nextStartTime > endTime){
                            return -1;
                        }
                    }
                    return nextStartTime;

            } else {
                if (current >= time){
                    return -1;
                }
               return time;
            }
        }
        return -1;
    }
}
