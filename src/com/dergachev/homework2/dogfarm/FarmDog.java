package com.dergachev.homework2.dogfarm;


import com.dergachev.homework2.dogfarm.dog.Age;
import com.dergachev.homework2.dogfarm.dog.Dog;
import com.dergachev.homework2.dogfarm.dog.DogList;
import com.dergachev.homework2.dogfarm.dog.PlaceOFWorkDog;
import com.dergachev.homework2.dogfarm.place.canteen.Canteen;
import com.dergachev.homework2.dogfarm.place.placeofwork.DogWork;
import com.dergachev.homework2.dogfarm.place.placeofwork.work.TrainingGround;
import com.dergachev.homework2.dogfarm.util.myexception.*;
import com.dergachev.homework2.dogfarm.place.*;
import com.dergachev.homework2.dogfarm.worker.*;


import java.math.BigDecimal;
import java.util.*;

public class FarmDog {
    private DogList dogs;

    private Aviaries aviaries;
    private VeterinaryClinic veterinaryClinic;
    private Canteen canteen;
    private DogWork dogWork;

    private List<Worker> staff;

    public FarmDog() {
        initFarmDog();
    }

    public void doOneDay() {

        canteen.feedingDogs(dogs.getDogList());
        veterinaryClinic.inspectionDogs(dogs.getDogList());
        aviaries.clearAviaries();
        dogWork.goToWork(dogs.getDogList());
        canteen.feedingDogs(dogs.getDogList());
        aviaries.setClear(false);
        aviaries.putToSleep(dogs.getDogList());
        dogs.saveDogListInFile();
    }

    public void addDog(Dog newDog) throws DogException {
        dogRegistration(newDog);
        dogs.addDog(newDog);
        System.out.println(newDog.getName() + " added!");
    }


    public void removeDogByName(String nameRemoveDog) throws DogException {
        dogs.removeDogByName(nameRemoveDog);
        System.out.println(nameRemoveDog+" remove!");
    }

    public void getDogByName(String dogName) throws DogException {
        dogs.getDogByName(dogName);
    }

    public void updateDog(Dog updateDog) throws DogException {
       if(updateDog==null){
           throw new DogException("Need dog");
       }
        dogs.updateDog(updateDog);
    }

    public void setDogs(List<Dog> dogList) {
        dogs.setDogList(dogList);
    }

    public List<Dog> getSortedDogListByName() {
        return dogs.getSortedDogListByName();
    }

    public List<Dog> getSortedDogListByAge() {
        return dogs.getSortedDogListByAge();
    }

    public List<Dog> getListAgeDog(Age age) throws DogException {
        return dogs.getListAgeDog(age);
    }

    public void printListDogInFile(String path) {
        dogs.printListDogInFile(path);
    }

    public Map<Age, List<Dog>> getMapDogByAge() {
        return dogs.getMapDogByAge();
    }

    public Map<PlaceOFWorkDog, List<Dog>> getMapDogByWork() {
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
        Worker trainerOld = dogWork.getTrainer();
        try {
            dogWork.setTrainer(trainer);
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
            Worker cleaner = new Cleaner("Bob", 27, new BigDecimal("670.56"));
            staff.add(cleaner);
            this.aviaries = new Aviaries(cleaner);

            Worker trainer = new Trainer("John", 45, new BigDecimal("1150.78"));
            staff.add(trainer);
            this.dogWork = new DogWork(trainer);

            Worker veterinary = new Veterinary("Bill", 35, new BigDecimal("1340.1"));
            staff.add(veterinary);
            this.veterinaryClinic = new VeterinaryClinic(veterinary);

            Worker cook = new Cook("Met", 44, new BigDecimal("1000.23"));
            staff.add(cook);
            this.canteen = new Canteen(cook);



        } catch (NullPointerException | WorkerException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private Dog dogRegistration(Dog newDog) throws DogException {
        if (newDog == null) {
            throw new NullPointerException("Need dog");
        }
        if (isUniqueName(newDog)) {
            inspection(newDog);
        } else {
            throw new DogException("The dog should have a unique name.");
        }
        return newDog;
    }

    private boolean isUniqueName(Dog newDog) {
        String newNameDog = newDog.getName();
        return dogs.getDogList().stream().noneMatch(e -> e.getName().equalsIgnoreCase(newNameDog));
    }

    private void inspection(Dog newDog) {
        if (!newDog.isHealthy()) {
            veterinaryClinic.getVeterinary().work(newDog);
        }
        if (newDog.isHungry()) {
            canteen.getCook().work(newDog);
        }
    }
}
