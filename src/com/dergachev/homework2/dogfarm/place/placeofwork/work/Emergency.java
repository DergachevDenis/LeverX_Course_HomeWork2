package com.dergachev.homework2.dogfarm.place.placeofwork.work;

import com.dergachev.homework2.dogfarm.dog.Dog;

import java.util.Random;

public class Emergency implements WorkPlace{
    private static final Object lock = new Object();

    private static volatile Emergency emergency = null;

    private Emergency() {
    }

    public static Emergency getEmergency() {
        if (emergency == null) {
            synchronized (lock) {
                if (emergency == null) {
                    emergency = new Emergency();
                }
            }
        }
        return emergency;
    }


    @Override
    public String work(Dog dog) {

        Random random = new Random();
        int colBoundaries = random.nextInt(10);
        String say = colBoundaries > 5 ? "Good boy!" : "Not bad!";

        return dog.getName() + " save " + colBoundaries + " people." + say;
    }
}
