package org.example;
// Main.java
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        int numberOfThreads = args.length > 0 ? Integer.parseInt(args[0]) : 3;
        TaskQueue taskQueue = new TaskQueue();
        ResultStore resultStore = new ResultStore();
        List<DivisorThread> threads = new ArrayList<>();

        for (int i = 0; i < numberOfThreads; i++) {
            DivisorThread thread = new DivisorThread(i, taskQueue, resultStore);
            threads.add(thread);
            thread.start();
        }

        try (Scanner scanner = new Scanner(new File("test1.txt"))) {
            while (scanner.hasNextLong()) {
                taskQueue.addTask(scanner.nextLong());
            }
        }

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String line = scanner.nextLine();
                if (line.equals("exit")) {
                    for (DivisorThread thread : threads) {
                        thread.exitAfterCurrentTask();
                    }
                    break;
                } else {
                    taskQueue.addTask(Long.parseLong(line));
                }
            }
        }

        try (PrintWriter writer = new PrintWriter("results.txt")) {
            for (String result : resultStore.getResults()) {
                writer.println(result);
            }
        }
    }
}