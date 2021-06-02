package com.dergachev.homework2.dogfarm;


import com.dergachev.homework2.dogfarm.dog.Age;
import com.dergachev.homework2.dogfarm.dog.Dog;
import com.dergachev.homework2.dogfarm.dog.DogList;
import com.dergachev.homework2.dogfarm.dog.PlaceOFWork;
import com.dergachev.homework2.dogfarm.util.myexception.*;
import com.dergachev.homework2.dogfarm.place.*;
import com.dergachev.homework2.dogfarm.worker.Work;
import com.dergachev.homework2.dogfarm.worker.Worker;


import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class FarmDog {
    private DogList dogs;

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

        canteen.feedingDogs(dogs.getDogList());
        veterinaryClinic.inspectionDogs(dogs.getDogList());
        aviaries.clearAviaries();
        dogWork.goToWork(dogs.getDogList(), trainingGround);
        canteen.feedingDogs(dogs.getDogList());
        aviaries.setClear(false);

    }

    public void addDogInList(Dog dog) {
        if (dog == null) {
            throw new NullPointerException();
        }
        dogs.addDog(dog);
    }

    public List<Dog> getSortedDogListByName() {
        return dogs.getSortedDogListByName();
    }

    public List<Dog> getSortedDogListByAge() {
        return dogs.getSortedDogListByAge();
    }

    public List<Dog> getListAgeDog(Age age) throws DogException {
        if (age == null) {
            throw new DogException("please indicate age: " +Age.PUPPY+", "+Age.ADULTDOG+", "+Age.ELDERLYDOG+".");
        }
        return dogs.getListAgeDog(age);
    }

    public Map<Age,List<Dog>> getMapDogByAge(){
        return dogs.getMapDogByAge();
    }

    public Map<PlaceOFWork,List<Dog>> getMapDogByWork(){
        return dogs.getMapDogByWork();
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
        dogs = new DogList();
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
}
