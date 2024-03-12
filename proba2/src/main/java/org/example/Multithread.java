// Multithread.java
package org.example;

public class Multithread extends Thread{
    private int number;
    private TaskQueue taskQueue;
    private ResultStore resultStore;

    Multithread(int i, TaskQueue taskQueue, ResultStore resultStore){
        this.number = i;
        this.taskQueue = taskQueue;
        this.resultStore = resultStore;
    }

    @Override
    public void run(){
        try {
            while(true){
                int task = taskQueue.getTask();
                System.out.println("Thread nr: " + number + " started working on number " + task);
                boolean isPrime = checkPrime(task);
                resultStore.addResult(isPrime);
                sleep(1000);
                System.out.println("Thread nr: " + number + " checked number: " + task + " and result is: " + (isPrime ? "prime" : "not prime"));
            }
        } catch (InterruptedException e) {
            System.out.println("Thread nr: " + number + " was interrupted");
        }
    }

    private boolean checkPrime(int number){
        if(number < 2) return false;
        for(int i = 2; i < number; i++){
            if(number % i == 0){
                return false;
            }
        }
        return true;
    }
}