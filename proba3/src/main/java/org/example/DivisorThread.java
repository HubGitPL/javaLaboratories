package org.example;

import java.util.ArrayList;
import java.util.List;

public class DivisorThread extends Thread {
    private final int number;
    private final TaskQueue taskQueue;
    private final ResultStore resultStore;
    private boolean shouldExit = false;

    DivisorThread(int number, TaskQueue taskQueue, ResultStore resultStore) {
        this.number = number;
        this.taskQueue = taskQueue;
        this.resultStore = resultStore;
    }

    @Override
    public void run() {
        try {
            while (!shouldExit) {
                long task = taskQueue.getTask();
                System.out.println("Thread nr: " + number + " started working on number " + task);
                List<Long> divisors = findDivisors(task);
                sleep(10000);
                String result = "Liczba " + task + ": " + divisors.toString();
                resultStore.addResult(result);
                System.out.println("Thread nr: " + number + " finished working on number " + task);
            }
        }
        catch (InterruptedException ignored) {
        }
    }

    public void exitAfterCurrentTask() {
        shouldExit = true;
    }
    private List<Long> findDivisors(long number) {
        List<Long> divisors = new ArrayList<>();
        for (long i = 1; i <= number; i++) {
            if (number % i == 0) {
                divisors.add(i);
            }
        }
        return divisors;
    }
}