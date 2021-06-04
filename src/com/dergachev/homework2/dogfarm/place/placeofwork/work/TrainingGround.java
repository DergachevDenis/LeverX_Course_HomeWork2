package com.dergachev.homework2.dogfarm.place.placeofwork.work;

import com.dergachev.homework2.dogfarm.dog.Age;
import com.dergachev.homework2.dogfarm.dog.Dog;
import com.dergachev.homework2.dogfarm.util.myexception.*;
import com.dergachev.homework2.dogfarm.worker.Trainer;
import com.dergachev.homework2.dogfarm.worker.Worker;

import java.util.List;

public class TrainingGround implements WorkPlace {

    private Trainer trainer;

    private static final Object lock = new Object();

    private static volatile TrainingGround trainingGround = null;

    private TrainingGround(Worker trainer) {
        this.trainer = (Trainer) trainer;
    }

    public static TrainingGround getTrainingGround(Trainer trainer) {
        if (trainingGround == null) {
            synchronized (lock) {
                if (trainingGround == null) {
                    trainingGround = new TrainingGround(trainer);
                }
            }
        }
        return trainingGround;
    }

    public Worker getTrainer() {
        return trainer;
    }

    public void setTrainer(Worker trainer) {
        this.trainer = (Trainer) trainer;
    }

    @Override
    public String work(Dog dog) {
        return trainer.getName() + ": " + trainer.work(dog);
    }


}
