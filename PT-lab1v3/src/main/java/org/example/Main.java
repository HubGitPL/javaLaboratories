package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Mage jeden = new Mage("jeden", 10, 100);
        Mage dwa = new Mage("dwa", 20, 200);
        Mage trzy = new Mage("trzy", 30, 300);
        Mage cztery = new Mage("cztery", 40, 400);
        Mage piec = new Mage("cztery", 40, 400);
        Mage szesc = new Mage("szesc", 60, 600);
        Mage siedem = new Mage("siedem", 70, 700);
        Mage osiem = new Mage("osiem", 80, 800);
        Mage dziewiec = new Mage("dziewiec", 90, 900);
        Mage dziesiec = new Mage("dziesiec", 100, 1000);

        //implementacja hashCode
        System.out.println(cztery.hashCode());
        System.out.println(piec.hashCode());

        //implementacja equals
        System.out.println(jeden.equals(dwa));
        System.out.println(cztery.equals(piec));
        System.out.println(cztery.equals(piec));

        //implementacja sortowania (compareTo)
        List<Mage> lista = new ArrayList<>();
        lista.add(jeden);
        lista.add(dwa);
        lista.add(trzy);
        lista.add(osiem);
        lista.add(cztery);
        lista.add(piec);
        lista.add(szesc);
        lista.add(siedem);
        lista.add(osiem);
        lista.add(dziewiec);
        lista.add(dziesiec);
       /* Collections.sort(lista);
        System.out.println(lista);

        //implementacja sortowania (Comparator)
        BetterSort betterSort = new BetterSort();
        Collections.sort(lista, betterSort);
        System.out.println(lista);*/

        //implementacja Set
        Set<Mage> mageSet = null;

        if(args.length > 0){
            String option = args[0];
            if(option.equals("brak")) {
                System.out.println("brak");
                mageSet = new HashSet<>();
            }
            else if(option.equals("naturalny")) {
                System.out.println("naturalny");
                mageSet = new TreeSet<>();
            }
            else if(option.equals("kryterium")){
                System.out.println("kryterium");
                mageSet = new TreeSet<>(new BetterSort());
            }
            else{
                System.out.println("Niepoprawny argument");
            }
            mageSet.add(jeden);
            mageSet.add(dwa);
            mageSet.add(trzy);
            mageSet.add(cztery);
            mageSet.add(piec);
            mageSet.add(szesc);
            mageSet.add(siedem);
            mageSet.add(osiem);
            mageSet.add(dziewiec);
            mageSet.add(dziesiec);
            //dodwanie potomkow
            jeden.setApprentices(new HashSet<>(Set.of(dwa, trzy)));
            dwa.setApprentices(new HashSet<>(Set.of(cztery)));
            cztery.setApprentices(new HashSet<>(Set.of(piec, szesc)));
            piec.setApprentices(new HashSet<>(Set.of(siedem)));
            siedem.setApprentices(new HashSet<>(Set.of(osiem)));
            osiem.setApprentices(new HashSet<>(Set.of(dziewiec, dziesiec)));

            System.out.println(mageSet);
            //podpunkt 4
            Mage.printSet(mageSet);
            //podpunkt 5
            Map<Mage, Integer> statisticsMap = Statistica.generateStatistics(mageSet, option);
            Statistica.printStatistics(statisticsMap);
        }
        else{
            System.out.println("Brak argumentów");
        }

    }
}