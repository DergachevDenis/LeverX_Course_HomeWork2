package com.dergachev.homework2.dogfarm.place;

import com.dergachev.homework2.dogfarm.dog.Dog;
import com.dergachev.homework2.dogfarm.util.myexception.*;

import java.util.List;

public class DogWork {

    public DogWork() {
    }

    public void goToWork(List<Dog> dogs, TrainingGround trainingGround) {
        if(dogs == null || trainingGround == null){
            throw new NullPointerException();
        }
        for (Dog dog : dogs) {
            switch (dog.getAge()) {
                case PUPPY:
                    try {
                        trainingGround.train(dog);
                        dog.setHungry(true);
                    } catch (DogException e) {
                        System.out.println(e.getMessage());;
                    }
                    break;
                case ADULTDOG:
                    System.out.println("The " + dog.getName() + " dog went to work for the " + dog.getWork() + ".");
                    dog.setHungry(true);
                    break;
                case ELDERLYDOG:
                    System.out.println("Dog " + dog.getName() + " resting.");
                    dog.setHungry(true);
                    break;
            }
        }
        System.out.println();
    }

}
