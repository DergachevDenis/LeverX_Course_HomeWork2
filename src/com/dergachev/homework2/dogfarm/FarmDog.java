package com.dergachev.homework2.dogfarm;


import com.dergachev.homework2.dogfarm.dog.Dog;
import com.dergachev.homework2.dogfarm.dog.PlaceOFWork;
import com.dergachev.homework2.dogfarm.util.myexception.*;
import com.dergachev.homework2.dogfarm.place.*;
import com.dergachev.homework2.dogfarm.worker.Work;
import com.dergachev.homework2.dogfarm.worker.Worker;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FarmDog {
    private List<Dog> dogs;

    private Aviaries aviaries;
    private TrainingGround trainingGround;
    private VeterinaryClinic veterinaryClinic;
    private Canteen canteen;
    private DogWork dogWork;

    private List<Worker> staff;

    public FarmDog() {
    }

    public void doOneDay() {
        initFarmDog();
        initDogList();

        canteen.feedingDogs(dogs);
        veterinaryClinic.inspectionDogs(dogs);
        aviaries.clearAviaries();
        dogWork.goToWork(dogs, trainingGround);
        canteen.feedingDogs(dogs);
        aviaries.setClear(false);

    }

    public void addDog(Dog dog) {
        if (dog == null) {
            throw new NullPointerException();
        }
        dogs.add(dog);
    }

    public void changeVeterinary(Worker veterinary) {
        Worker veterinaryOld = veterinaryClinic.getVeterinary();
        try {
            veterinaryClinic.setVeterinary(veterinary);
            staff.remove(veterinaryOld);
            staff.add(veterinary);
        } catch (WorkerException e) {
            System.out.println(e.getMessage());
        }
    }

    public void changeCook(Worker cook) {
        Worker cookOld = canteen.getCook();
        try {
            canteen.setCook(cook);
            staff.remove(cookOld);
            staff.add(cook);
        } catch (WorkerException e) {
            System.out.println(e.getMessage());
        }
    }

    public void changeCleaner(Worker cleaner) {
        Worker cleanerOld = aviaries.getCleaner();
        try {
            aviaries.setCleaner(cleaner);
            staff.remove(cleanerOld);
            staff.add(cleaner);
        } catch (WorkerException e) {
            System.out.println(e.getMessage());
        }
    }

    public void changeTrainer(Worker trainer) {
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

            this.dogWork = new DogWork();
        } catch (NullPointerException | WorkerException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initDogList() {
        dogs = new ArrayList<>();

        try {
            Dog dog1 = new Dog("Barbos", "12.01.2017", PlaceOFWork.POLICE, false, true, true);
            Dog dog2 = new Dog("Sharik", "19.07.2014", true, true, false);
            Dog dog3 = new Dog("Gav", "12.04.2020", true, false, true);
            Dog dog4 = new Dog("Bim", "03.08.2020", false, false, false);
            Dog dog5 = new Dog("Palkan", "31.08.2018", PlaceOFWork.EMERGENCY, true, true, false);
            Dog dog6 = new Dog("Pes", "25.07.2010", false, true, true);

            dogs.add(dog1);
            dogs.add(dog2);
            dogs.add(dog3);
            dogs.add(dog4);
            dogs.add(dog5);
            dogs.add(dog6);

        } catch (DateException e) {
            System.out.println(e.getMessage());
        }
    }
}
