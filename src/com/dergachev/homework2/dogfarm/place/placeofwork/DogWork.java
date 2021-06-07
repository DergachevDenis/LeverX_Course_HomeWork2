package com.dergachev.homework2.dogfarm.place.placeofwork;

import com.dergachev.homework2.dogfarm.dog.Dog;
import com.dergachev.homework2.dogfarm.dog.PlaceOFWorkDog;
import com.dergachev.homework2.dogfarm.place.placeofwork.work.TrainingGround;
import com.dergachev.homework2.dogfarm.place.placeofwork.work.*;
import com.dergachev.homework2.dogfarm.util.myexception.*;
import com.dergachev.homework2.dogfarm.worker.Trainer;
import com.dergachev.homework2.dogfarm.worker.Worker;

import java.util.List;

public class DogWork {
    private Trainer trainer;

    public DogWork(Worker trainer) throws WorkerException {
        validTrainer(trainer);
        this.trainer =(Trainer) trainer;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Worker trainer) throws WorkerException {
        validTrainer(trainer);
        this.trainer = (Trainer) trainer;
    }

    public WorkPlace getWorkPlace(PlaceOFWorkDog place)  {
        WorkPlace workPlace = switch (place) {
            case ARMY -> Army.getArmy();
            case POLICE -> Police.getPolice();
            case EMERGENCY -> Emergency.getEmergency();
            case GUIDE_DOG -> GuideDog.getGuideDog();
            case UNEMPLOYED -> Relaxation.getRelaxation();
            case TRAINING -> TrainingGround.getTrainingGround(trainer);
        };
        return workPlace;
    }

    public void goToWork(List<Dog> dogs) {
        validList(dogs);
        if(dogs.size()>10000) {
            dogs.parallelStream().filter(e -> e.getWork() == PlaceOFWorkDog.ARMY).peek(e -> e.setHungry(true)).forEach(e -> System.out.println(getWorkPlace(e.getWork()).work(e)));
            dogs.parallelStream().filter(e -> e.getWork() == PlaceOFWorkDog.POLICE).peek(e -> e.setHungry(true)).forEach(e -> System.out.println(getWorkPlace(e.getWork()).work(e)));
            dogs.parallelStream().filter(e -> e.getWork() == PlaceOFWorkDog.GUIDE_DOG).peek(e -> e.setHungry(true)).forEach(e -> System.out.println(getWorkPlace(e.getWork()).work(e)));
            dogs.parallelStream().filter(e -> e.getWork() == PlaceOFWorkDog.UNEMPLOYED).peek(e -> e.setHungry(true)).forEach(e -> System.out.println(getWorkPlace(e.getWork()).work(e)));
            dogs.parallelStream().filter(e -> e.getWork() == PlaceOFWorkDog.TRAINING).peek(e -> e.setHungry(true)).forEach(e -> System.out.println(getWorkPlace(e.getWork()).work(e)));
        }
        else {
            dogs.stream().filter(e -> e.getWork() == PlaceOFWorkDog.ARMY).peek(e -> e.setHungry(true)).forEach(e -> System.out.println(getWorkPlace(e.getWork()).work(e)));
            dogs.stream().filter(e -> e.getWork() == PlaceOFWorkDog.POLICE).peek(e -> e.setHungry(true)).forEach(e -> System.out.println(getWorkPlace(e.getWork()).work(e)));
            dogs.stream().filter(e -> e.getWork() == PlaceOFWorkDog.GUIDE_DOG).peek(e -> e.setHungry(true)).forEach(e -> System.out.println(getWorkPlace(e.getWork()).work(e)));
            dogs.stream().filter(e -> e.getWork() == PlaceOFWorkDog.UNEMPLOYED).peek(e -> e.setHungry(true)).forEach(e -> System.out.println(getWorkPlace(e.getWork()).work(e)));
            dogs.stream().filter(e -> e.getWork() == PlaceOFWorkDog.TRAINING).peek(e -> e.setHungry(true)).forEach(e -> System.out.println(getWorkPlace(e.getWork()).work(e)));
        }
        System.out.println();
    }

    private void validList(List<Dog> dogs) {
        if (dogs == null  || dogs.isEmpty()) {
            throw new NullPointerException("Need someone to work.");
        }
    }

    private void validTrainer(Worker trainer) throws WorkerException {
        if (trainer == null) {
            throw new NullPointerException("There is a trainer to work here.");
        } else if (trainer.getClass() != Trainer.class) {
            throw new WorkerException("It is not the responsibility of the " + trainer.getClass() + " to train puppies.");
        }
    }

}
