package ua.edu.sumdu.j2se.lietunova.tasks.model;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TaskIO {
    private final static Logger logger = LoggerFactory.getLogger(TaskIO.class);
    /**
     * Метод записує задачі із списку у потік у бінарному форматі.
     */
    public static void write(AbstractTaskList tasks, OutputStream out) {
        try (DataOutputStream dos = new DataOutputStream(out)) {
            dos.writeInt(tasks.size());
            for (Task task : tasks) {
                dos.writeInt(task.getTitle().length());
                dos.writeUTF(task.getTitle());
                dos.writeBoolean(task.isActive());
                dos.writeInt(task.getRepeatInterval());
                if (task.isRepeated()) {
                    dos.writeLong(task.getStartTime().toEpochSecond(ZoneOffset.UTC));
                    dos.writeInt(task.getStartTime().getNano());

                    dos.writeLong(task.getEndTime().toEpochSecond(ZoneOffset.UTC));
                    dos.writeInt(task.getEndTime().getNano());
                } else {
                    dos.writeLong(task.getTime().toEpochSecond(ZoneOffset.UTC));
                    dos.writeInt(task.getTime().getNano());
                }
            }
        } catch (IOException e) {
            logger.error("{}", e.getStackTrace());
        }


    }

    /**
     * Метод зчитує задачі із потоку у даний список задач.
     */
    public static void read(AbstractTaskList tasks, InputStream in) {
        try (DataInputStream dis = new DataInputStream(in)) {
            Task task;
            dis.readInt();
            while (dis.available() > 0) {
                dis.readInt();
                String title = dis.readUTF();
                boolean active = dis.readBoolean();
                int interval = dis.readInt();
                if (interval != 0) {
                    LocalDateTime start = LocalDateTime.ofEpochSecond(dis.readLong(), dis.readInt(), ZoneOffset.UTC);
                    LocalDateTime end = LocalDateTime.ofEpochSecond(dis.readLong(), dis.readInt(), ZoneOffset.UTC);
                    task = new Task(title, start, end, interval);
                } else {
                    LocalDateTime time = LocalDateTime.ofEpochSecond(dis.readLong(), dis.readInt(), ZoneOffset.UTC);
                    task = new Task(title, time);
                }
                task.setActive(active);
                tasks.add(task);
            }
        } catch (IOException e) {
            logger.error("{}", e.getStackTrace());
        }
    }

    /**
     * Метод записує задачі із списку у файл.
     */
    public static void writeBinary(AbstractTaskList tasks, File file) {
        try (FileOutputStream fos = new FileOutputStream(file)) {
            write(tasks, fos);
        } catch (IOException e) {
            logger.error("{}", e.getStackTrace());
        }
    }

    /**
     * Метод зчитує задачі із файлу у список задач.
     */
    public static void readBinary(AbstractTaskList tasks, File file) {
        try (FileInputStream fis = new FileInputStream(file)) {
            read(tasks, fis);
        } catch (IOException e) {
            logger.error("{}", e.getStackTrace());
        }
    }


    /**
     * Метод записує задачі зі списку у потік в форматі JSON.
     */
    public static void write(AbstractTaskList tasks, Writer out) {
        try (BufferedWriter bw = new BufferedWriter(out)) {
            bw.write(new Gson().toJson(tasks));
            bw.flush();
        } catch (IOException e) {
            logger.error("{}", e.getStackTrace());
        }
    }

    /**
     * Метод зчитує задачі із потоку у список.
     */
    public static void read(AbstractTaskList tasks, Reader in) {
        try (BufferedReader br = new BufferedReader(in)) {
            String jsonData = br.readLine();
            if (jsonData != null) {
                Gson gson = new Gson();
                AbstractTaskList tempList = gson.fromJson(jsonData, tasks.getClass());
                for (Task task : tempList) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            logger.error("{}", e.getStackTrace());
        }
    }

    /**
     * Метод записує задачі у файл у форматі JSON
     */
    public static void writeText(AbstractTaskList tasks, File file) {
        try (FileWriter fw = new FileWriter(file)) {
            write(tasks, fw);
        } catch (IOException e) {
            logger.error("{}", e.getStackTrace());
        }
    }

    /**
     * Метод зчитує задачі із файлу.
     */
    public static void readText(AbstractTaskList tasks, File file) {
        try (FileReader fr = new FileReader(file)) {
            read(tasks, fr);
        } catch (IOException e) {
            logger.error("not find file {} {}", file, e);
        }
    }
}
