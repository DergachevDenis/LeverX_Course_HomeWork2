package com.dergachev.homework2.dogfarm.place.placeofwork.work;

import com.dergachev.homework2.dogfarm.dog.Dog;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Army implements WorkPlace {
    private static final ReentrantLock locker = new ReentrantLock();
    private static volatile Army army = null;

    private Army() {
    }

    public static Army getArmy() {
        if (army == null) {
            locker.lock();
            try{
                if (army == null) {
                    army = new Army();
                }
            }
            finally{
                locker.unlock();
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
