package com.dergachev.homework2.dogfarm.place.placeofwork.work;

import com.dergachev.homework2.dogfarm.dog.Dog;

import java.util.Random;

public class Army implements WorkPlace {
    private static final Object lock = new Object();

    private static volatile Army army = null;

    private Army() {
    }

    public static Army getArmy() {
        if (army == null) {
            synchronized (lock) {
                if (army == null) {
                    army = new Army();
                }
            }
        }
        return army;
    }

    @Override
    public String work(Dog dog) {
        Random random = new Random();
        int colBoundaries = random.nextInt(10);
        String say = colBoundaries > 5 ? "Good boy!" : "Not bad!";

        return dog.getName() + " delayed " + colBoundaries + " boundaries of border." + say;
    }
}
