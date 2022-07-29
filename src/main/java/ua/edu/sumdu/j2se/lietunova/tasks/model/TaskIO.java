package ua.edu.sumdu.j2se.lietunova.tasks.model;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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
            logger.error("Problem with OutputStream {}", out, e);
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
            logger.error("Problem with InputStream {}", in, e);
        }
    }

    /**
     * Метод записує задачі із списку у файл.
     */
    public static void writeBinary(AbstractTaskList tasks, File file) {
        try (OutputStream fos = Files.newOutputStream(Paths.get(String.valueOf(file)))) {
            write(tasks, fos);
        } catch (IOException e) {
            logger.error("Problem with file {}", file, e);
        }
    }

    /**
     * Метод зчитує задачі із файлу у список задач.
     */
    public static void readBinary(AbstractTaskList tasks, File file) {
        try (InputStream fis = Files.newInputStream(Paths.get(String.valueOf(file)))) {
            read(tasks, fis);
        } catch (IOException e) {
            logger.error("Problem with file {}", file, e);
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
            logger.error("Problem with Writer {}", out, e);
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
            logger.error("Problem with Reader {}", in, e);
        }
    }

    /**
     * Метод записує задачі у файл у форматі JSON
     */
    public static void writeText(AbstractTaskList tasks, File file) {
        try (BufferedWriter fw = Files.newBufferedWriter(Paths.get(String.valueOf(file)), StandardCharsets.UTF_8)) {
            write(tasks, fw);
        } catch (IOException e) {
            logger.error("Problem with file {}", file, e);
        }
    }

    /**
     * Метод зчитує задачі із файлу.
     */
    public static void readText(AbstractTaskList tasks, File file) {
        try (BufferedReader fr = Files.newBufferedReader(Paths.get(String.valueOf(file)), StandardCharsets.UTF_8)) {
            read(tasks, fr);
        } catch (IOException e) {
            logger.error("Problem with file {} ", file, e);
        }
    }
}
