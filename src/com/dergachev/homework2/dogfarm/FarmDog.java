package com.dergachev.homework2.dogfarm;


import com.dergachev.homework2.dogfarm.dog.Dog;
import com.dergachev.homework2.dogfarm.exception.DogException;
import com.dergachev.homework2.dogfarm.exception.WorkerException;
import com.dergachev.homework2.dogfarm.place.Aviaries;
import com.dergachev.homework2.dogfarm.place.Canteen;
import com.dergachev.homework2.dogfarm.place.TrainingGround;
import com.dergachev.homework2.dogfarm.place.VeterinaryClinic;
import com.dergachev.homework2.dogfarm.worker.Work;
import com.dergachev.homework2.dogfarm.worker.Worker;


import java.math.BigDecimal;
import java.util.ArrayList;

public class FarmDog {
    private ArrayList<Dog> dogs;

    private Aviaries aviaries;
    private TrainingGround trainingGround;
    private VeterinaryClinic veterinaryClinic;
    private Canteen canteen;

    private ArrayList<Worker> staff;

    public FarmDog() {
    }

    public void doOneDay() {
        initFarmDog();
        initDogList();

        canteen.feedingDogs(dogs);
        veterinaryClinic.inspectionDogs(dogs);
        aviaries.clearAviaries();
        goToWork();
        canteen.feedingDogs(dogs);
        aviaries.setClear(false);

    }

    public void addDog(Dog dog){
        if(dog==null){
            throw new NullPointerException();
        }
        dogs.add(dog);
    }

    public void changeVeterinary(Worker veterinary){
        Worker veterinaryOld = veterinaryClinic.getVeterinary();
        try {
            veterinaryClinic.setVeterinary(veterinary);
            staff.remove(veterinaryOld);
            staff.add(veterinary);
        } catch (WorkerException e) {
            System.out.println(e.getMessage());
        }
    }

    public void changeCook(Worker cook){
        Worker cookOld = canteen.getCook();
        try {
            canteen.setCook(cook);
            staff.remove(cookOld);
            staff.add(cook);
        } catch (WorkerException e) {
            System.out.println(e.getMessage());
        }
    }

    public void changeCleaner(Worker cleaner){
        Worker cleanerOld = aviaries.getCleaner();
        try {
            aviaries.setCleaner(cleaner);
            staff.remove(cleanerOld);
            staff.add(cleaner);
        } catch (WorkerException e) {
            System.out.println(e.getMessage());
        }
    }

    public void changeTrainer(Worker trainer){
        Worker trainerOld = trainingGround.getTrainer();
        try {
            trainingGround.setTrainer(trainer);
            staff.remove(trainerOld);
            staff.add(trainer);
        } catch (WorkerException e) {
            System.out.println(e.getMessage());
        }
    }


    private void initFarmDog() {
        staff = new ArrayList<>();

        try {
            Worker cleaner = new Worker("Bob", 27, Work.CLEANER, new BigDecimal(670.56));
            staff.add(cleaner);
            this.aviaries = new Aviaries(cleaner);

            Worker trainer = new Worker("John", 45, Work.TRAINER, new BigDecimal(1150.78));
            staff.add(trainer);
            this.trainingGround = new TrainingGround(trainer);

            Worker veterinary = new Worker("Bill", 35, Work.VETERINARY, new BigDecimal(1340.1));
            staff.add(veterinary);
            this.veterinaryClinic = new VeterinaryClinic(veterinary);

            Worker cook = new Worker("Met", 44, Work.COOK, new BigDecimal(1000.23));
            staff.add(cook);
            this.canteen = new Canteen(cook);

        } catch (NullPointerException | WorkerException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initDogList() {
        dogs = new ArrayList<>();
        try {
            Dog dog1 = new Dog("Barbos", 7, "Police", false, true, true);
            Dog dog2 = new Dog("Sharik", 9, true, true, false);
            Dog dog3 = new Dog("Gav", 1, true, false, true);
            Dog dog4 = new Dog("Bim", 1, false, false, false);
            Dog dog5 = new Dog("Palkan", 4, "101", true, true, false);
            Dog dog6 = new Dog("Pes", 10, false, true, true);

            dogs.add(dog1);
            dogs.add(dog2);
            dogs.add(dog3);
            dogs.add(dog4);
            dogs.add(dog5);
            dogs.add(dog6);

        } catch (DogException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void goToWork() {
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
