package com.dergachev.homework2.dogfarm.place.placeofwork.work;

import com.dergachev.homework2.dogfarm.dog.Dog;

import java.util.Random;

public class Relaxation implements WorkPlace{
    private static final Object lock = new Object();

    private static volatile Relaxation relaxation = null;

    private Relaxation() {
    }

    public static Relaxation getRelaxation() {
        if (relaxation == null) {
            synchronized (lock) {
                if (relaxation == null) {
                    relaxation = new Relaxation();
                }
            }
        }
        return relaxation;
    }

    @Override
    public String work(Dog dog) {
                return dog.getName() + " relax))";
    }
}
