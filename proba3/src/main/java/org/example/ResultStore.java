package org.example;
// ResultStore.java
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class ResultStore {
    private final List<String> results = new ArrayList<>();
    private final Semaphore semaphore = new Semaphore(1);

    public void addResult(String result) throws InterruptedException {
        semaphore.acquire();
        try {
            results.add(result);
        } finally {
            semaphore.release();
        }
    }

    public List<String> getResults() {
        return new ArrayList<>(results);
    }
}