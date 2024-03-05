package org.example;

import java.util.*;

public class Statistica {
    public static Map<Mage, Integer> generateStatistics(Set<Mage> mageSet, String option) {
        Map<Mage, Integer> statisticsMap = null;

        if (option.equals("brak")) {
            statisticsMap = new HashMap<>();
        } else if (option.equals("naturalny")) {
            statisticsMap = new TreeMap<>();
        } else if (option.equals("kryterium")) {
            statisticsMap = new TreeMap<>(new BetterSort());
        } else {
            System.out.println("Niepoprawny argument");
        }

        for (Mage mage : mageSet) {
            int descendantsCount = countDescendants(mage);
            statisticsMap.put(mage, descendantsCount);
        }

        return statisticsMap;
    }

    private static int countDescendants(Mage mage) {
        int descendantsCount = 0;

        Set<Mage> apprentices = mage.getApprentices();
        if (apprentices != null) {
            descendantsCount += apprentices.size();
            for (Mage apprentice : apprentices) {
                descendantsCount += countDescendants(apprentice);
            }
        }

        return descendantsCount;
    }

    public static void printStatistics(Map<Mage, Integer> statisticsMap) {
        for (Map.Entry<Mage, Integer> entry : statisticsMap.entrySet()) {
            System.out.println("Mage: " + entry.getKey().getName() + ", Potomkowie: " + entry.getValue());
        }
    }
}
