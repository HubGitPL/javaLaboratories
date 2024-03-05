package org.example;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Mage implements Comparable<Mage>{
    private String name;
    private int level;
    private double power;
    private Set<Mage> apprentices;

    Mage(String name, int level, double power){
        this.name = name;
        this.level = level;
        this.power = power;
        this.apprentices = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public Set<Mage> getApprentices() {
        return apprentices;
    }

    public void setApprentices(Set<Mage> apprentices) {
        this.apprentices = apprentices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mage mage = (Mage) o;
        return getLevel() == mage.getLevel() &&
                Double.compare(mage.getPower(), getPower()) == 0 &&
                getName().equals(mage.getName()) &&
                Objects.equals(getApprentices(), mage.getApprentices());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getLevel(), getPower(), getApprentices());
    }

    @Override
    public String toString() {
        return "Mage{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", power=" + power +
                '}';
    }

    @Override
    public int compareTo(Mage o) {
        if(getName() != o.getName()){
            return getName().compareTo(o.getName());
        } else if(getLevel() != o.getLevel()){
            return Integer.compare(getLevel(), o.getLevel());
        } else {
            return Double.compare(getPower(), o.getPower());
        }
    }

    public static void printSet(Set<Mage> set) {
        System.out.println("Poczatek");
        printSetRecursive(set, 1);
        System.out.println("Koniec");
    }

    private static void printSetRecursive(Set<Mage> set1, int indentLevel) {
        String indentation = "-".repeat(indentLevel);

        for (Mage mage : set1) {
            System.out.print(indentation);
            System.out.println(mage);

            if (mage.getApprentices().isEmpty()) {
                continue;
            }
            printSetRecursive(mage.getApprentices(), indentLevel + 1);
        }
    }
}
