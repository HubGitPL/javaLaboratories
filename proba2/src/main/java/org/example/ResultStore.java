package org.example;

import java.util.ArrayList;
import java.util.List;

public class ResultStore {
    private final List<Boolean> results = new ArrayList<>();

    public synchronized void addResult(boolean result) {
        results.add(result);
    }

    public synchronized List<Boolean> getResults() {
        List<Boolean> resultsCopy = new ArrayList<>(results);
        return resultsCopy;
    }
}