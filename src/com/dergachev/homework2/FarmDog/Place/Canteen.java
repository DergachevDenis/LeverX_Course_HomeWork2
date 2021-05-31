package com.dergachev.homework2.FarmDog.Place;

import com.dergachev.homework2.FarmDog.Dog.Dog;
import com.dergachev.homework2.FarmDog.Exception.WorkerException;
import com.dergachev.homework2.FarmDog.Worker.Work;
import com.dergachev.homework2.FarmDog.Worker.Worker;

import java.util.List;

public class Canteen {

    private Worker cook;

    public Canteen(Worker cook) throws WorkerException {
        validTrainer(cook);
        this.cook = cook;
    }

    public Worker getCook() {
        return cook;
    }

    public void setCook(Worker cook) throws WorkerException {
        validTrainer(cook);
        this.cook = cook;
    }

    public void feedingDogs(List<Dog> dogList) {
        validDogList(dogList);
        for (Dog dog : dogList) {
            switch (dog.getAge()) {
                case PUPPY:
                    System.out.println("The cook " + cook.getName() + " feed " + dog.getName() + " puppy.");
                    dog.setHungry(false);
                    break;
                case ADULTDOG:
                    System.out.println("The cook " + cook.getName() + " feed " + dog.getName() + " adult dog.");
                    dog.setHungry(false);
                    break;
                case ELDERLYDOG:
                    System.out.println("The cook " + cook.getName() + " feed " + dog.getName() + " elderly dog.");
                    dog.setHungry(false);
                    break;
            }
        }
        System.out.println("The cook " + cook.getName() + " fed all dogs.\n");
    }

    private void validTrainer(Worker cook) throws WorkerException {
        if (cook == null) {
            throw new NullPointerException("There is a cook to work here.");
        } else if (cook.getWork() != Work.COOK) {
            throw new WorkerException("It is not the responsibility of the " + cook.getWork() + " to cook.");
        }
    }

    private void validDogList(List<Dog> dogList) {
        if (dogList == null) {
            throw new NullPointerException("Need someone to feed.");
        }
    }
}
