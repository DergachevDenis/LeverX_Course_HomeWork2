package com.dergachev.homework2.dogfarm;


import com.dergachev.homework2.dogfarm.dog.Age;
import com.dergachev.homework2.dogfarm.dog.Dog;
import com.dergachev.homework2.dogfarm.dog.DogList;
import com.dergachev.homework2.dogfarm.dog.PlaceOFWork;
import com.dergachev.homework2.dogfarm.place.canteen.Canteen;
import com.dergachev.homework2.dogfarm.util.myexception.*;
import com.dergachev.homework2.dogfarm.place.*;
import com.dergachev.homework2.dogfarm.worker.*;


import java.math.BigDecimal;
import java.util.*;

public class FarmDog {
    private DogList dogList;

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

        canteen.feedingDogs(dogList.getDogList());
        veterinaryClinic.inspectionDogs(dogList.getDogList());
        aviaries.clearAviaries();
        dogWork.goToWork(dogList.getDogList(), trainingGround);
        canteen.feedingDogs(dogList.getDogList());
        aviaries.setClear(false);
        aviaries.putToSleep(dogList.getDogList());
        dogList.saveDogListInFile();
    }

    public DogList getDogList() {
        return dogList;
    }

    public void setDogList(DogList dogList) {
        this.dogList = dogList;
    }

    public List<Dog> getSortedDogListByName() {
        return dogList.getSortedDogListByName();
    }

    public List<Dog> getSortedDogListByAge() {
        return dogList.getSortedDogListByAge();
    }

    public List<Dog> getListAgeDog(Age age) throws DogException {
        return dogList.getListAgeDog(age);
    }

    public void printListDogInFile(String path) {
        dogList.printListDogInFile(path);
    }

    public Map<Age, List<Dog>> getMapDogByAge() {
        return dogList.getMapDogByAge();
    }

    public Map<PlaceOFWork, List<Dog>> getMapDogByWork() {
        return dogList.getMapDogByWork();
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
        dogList = new DogList();
        staff = new ArrayList<>();

        try {
            Worker cleaner = new Cleaner("Bob", 27, new BigDecimal("670.56"));
            staff.add(cleaner);
            this.aviaries = new Aviaries(cleaner);

            Worker trainer = new Trainer("John", 45, new BigDecimal("1150.78"));
            staff.add(trainer);
            this.trainingGround = new TrainingGround(trainer);

            Worker veterinary = new Veterinary("Bill", 35, new BigDecimal("1340.1"));
            staff.add(veterinary);
            this.veterinaryClinic = new VeterinaryClinic(veterinary);

            Worker cook = new Cook("Met", 44, new BigDecimal("1000.23"));
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
