package org.example;
// TaskQueue.java
import java.util.LinkedList;
import java.util.Queue;

public class TaskQueue {
    private Queue<Long> tasks = new LinkedList<>();

    public synchronized void addTask(long number) {
        tasks.add(number);
        notifyAll();
    }

    public synchronized long getTask() throws InterruptedException {
        while (tasks.isEmpty()) {
            wait();
        }
        return tasks.poll();
    }
}