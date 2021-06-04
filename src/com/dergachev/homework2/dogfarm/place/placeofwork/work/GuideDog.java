package com.dergachev.homework2.dogfarm.place.placeofwork.work;

import com.dergachev.homework2.dogfarm.dog.Dog;

import java.util.Random;

public class GuideDog implements WorkPlace {
    private static final Object lock = new Object();

    private static volatile GuideDog guideDog = null;

    private GuideDog() {
    }

    public static GuideDog getGuideDog() {
        if (guideDog == null) {
            synchronized (lock) {
                if (guideDog == null) {
                    guideDog = new GuideDog();
                }
            }
        }
        return guideDog;
    }


    @Override
    public String work(Dog dog) {
        Random random = new Random();
        int colBoundaries = random.nextInt(10);
        String say = colBoundaries > 5 ? "Good boy!" : "Not bad!";

        return dog.getName() + " help " + colBoundaries + " people." + say;
    }
}
