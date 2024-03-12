// Main.java
package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int numberOfThreads = args.length > 0 ? Integer.parseInt(args[0]) : 3;
        TaskQueue taskQueue = new TaskQueue();
        ResultStore resultStore = new ResultStore();
        List<Multithread> threads = new ArrayList<>();

        for(int i = 0; i < numberOfThreads; i++){
            Multithread thread = new Multithread(i, taskQueue, resultStore);
            threads.add(thread);
            thread.start();
        }

        for(int i = 0; i < 100; i++){
            taskQueue.addTask(i);
        }

        Scanner scanner = new Scanner(System.in);
        while(true){
            String line = scanner.nextLine();
            if(line.equals("e")){
                for(Multithread thread : threads){
                    thread.interrupt();
                }
                for(Multithread thread : threads){
                    try {
                        thread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.exit(0);
            }
            else if(line.equals("r")){
                List<Boolean> results = resultStore.getResults();
                for(int i = 0; i < results.size(); i++){
                    System.out.println("Result " + (i) + ": " + (results.get(i) ? "prime" : "not prime"));
                }
            }else{
                //add number to the task queue
                taskQueue.addTask(Integer.parseInt(line));
            }
        }
    }
}