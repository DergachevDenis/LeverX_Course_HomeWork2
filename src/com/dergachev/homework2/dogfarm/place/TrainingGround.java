package com.dergachev.homework2.dogfarm.place;

import com.dergachev.homework2.dogfarm.dog.Age;
import com.dergachev.homework2.dogfarm.dog.Dog;
import com.dergachev.homework2.dogfarm.util.myexception.*;
import com.dergachev.homework2.dogfarm.worker.Trainer;
import com.dergachev.homework2.dogfarm.worker.Worker;

import java.util.List;

public class TrainingGround {

    private Trainer trainer;

    public TrainingGround() {
    }

    public TrainingGround(Worker trainer) throws WorkerException {
        validTrainer(trainer);
        this.trainer = (Trainer) trainer;
    }

    public Worker getTrainer() {
        return trainer;
    }

    public void setTrainer(Worker trainer) throws WorkerException {
        validTrainer(trainer);
        this.trainer = (Trainer) trainer;
    }

    public void trainDogs(List<Dog> dogList) {
        validDogList(dogList);
        long colTrainDogs = dogList.stream().filter(e -> e.getAge() == Age.PUPPY).filter(e -> !e.isTrained()).peek(e->e.setTrained(true)).count();

        if (colTrainDogs > 0) {
            System.out.println("The trainer " + trainer.getName() + " train all dogs.\n");
        }
        else {
            System.out.println(trainer.getName()+": Super, all dogs are trained and I can rest!");
        }
        System.out.println();

    }

    public void train(Dog puppy) throws DogException {
        validDog(puppy);
        System.out.println("Trainer " + trainer.getName() + " trains a " + puppy.getName() + " puppy.");
        puppy.setTrained(true);
    }

    private void validTrainer(Worker trainer) throws WorkerException {
        if (trainer == null) {
            throw new NullPointerException("There is a trainer to work here.");
        } else if (trainer.getClass() != Trainer.class) {
            throw new WorkerException("It is not the responsibility of the " + trainer.getClass() + " to train puppies.");
        }
    }

    private void validDog(Dog puppy) throws DogException {
        if (puppy == null) {
            throw new NullPointerException("Need someone to train.");
        }
        if (puppy.getAge() != Age.PUPPY) {
            throw new DogException("Only puppies are trained here.");
        }
    }

    private void validDogList(List<Dog> dogList) {
        if (dogList == null || dogList.isEmpty()) {
            throw new NullPointerException("Need someone to train.");
        }
    }
}
