package com.dergachev.homework2.dogfarm.place.placeofwork.work;

import com.dergachev.homework2.dogfarm.dog.Dog;

import java.util.Random;

public class Police implements WorkPlace{

    private static final Object lock = new Object();

    private static volatile Police police = null;

    private Police() {
    }

    public static Police getPolice() {
        if (police == null) {
            synchronized (lock) {
                if (police == null) {
                    police = new Police();
                }
            }
        }
        return police;
    }

    @Override
    public String work(Dog dog) {
        Random random = new Random();
        int colBoundaries = random.nextInt(10);
        String say = colBoundaries > 5 ? "Good boy!" : "Not bad!";

        return dog.getName() + " caught " + colBoundaries + " the bandits." + say;
    }
}
