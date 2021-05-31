package com.dergachev.homework2.farmdog.place;

import com.dergachev.homework2.farmdog.dog.Age;
import com.dergachev.homework2.farmdog.dog.Dog;
import com.dergachev.homework2.farmdog.exception.DogException;
import com.dergachev.homework2.farmdog.exception.WorkerException;
import com.dergachev.homework2.farmdog.worker.Work;
import com.dergachev.homework2.farmdog.worker.Worker;

public class TrainingGround {

    private Worker trainer;

    public TrainingGround(Worker trainer) throws WorkerException {
        validTrainer(trainer);
        this.trainer = trainer;
    }

    public Worker getTrainer() {
        return trainer;
    }

    public void setTrainer(Worker trainer) throws WorkerException {
        validTrainer(trainer);
        this.trainer = trainer;
    }

    public void train(Dog puppy) throws DogException {
        validDog(puppy);
        System.out.println("Trainer " + trainer.getName() + " trains a " + puppy.getName() + " puppy.");
        puppy.setHungry(true);
    }

    private void validTrainer(Worker trainer) throws WorkerException {
        if (trainer == null) {
            throw new NullPointerException("There is a trainer to work here.");
        } else if (trainer.getWork() != Work.TRAINERS) {
            throw new WorkerException("It is not the responsibility of the " + trainer.getWork() + " to train puppies.");
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
}
