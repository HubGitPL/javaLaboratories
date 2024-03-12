package org.example;

import java.util.LinkedList;
import java.util.Queue;

public class TaskQueue {
    private Queue<Integer> tasks = new LinkedList<>();

    public synchronized void addTask(int number) {
        tasks.add(number);
        notify();
    }

    public synchronized int getTask() throws InterruptedException {
        while (tasks.isEmpty()) {
            wait();
        }
        return tasks.poll();
    }
}