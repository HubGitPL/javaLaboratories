package org.example;

import java.util.Comparator;

public class BetterSort implements Comparator<Mage> {
    @Override
    public int compare(Mage o1, Mage o2) {
        if(o1.getLevel() != o2.getLevel()){
            return Integer.compare(o1.getLevel(), o2.getLevel());
        } else if(o1.getName() != o2.getName()){
            return o1.getName().compareTo(o2.getName());
        } else {
            return Double.compare(o1.getPower(), o2.getPower());
        }
    }
}
