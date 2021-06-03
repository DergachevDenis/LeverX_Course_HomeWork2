package com.dergachev.homework2;

import com.dergachev.homework2.dogfarm.FarmDog;
import com.dergachev.homework2.dogfarm.dog.Age;
import com.dergachev.homework2.dogfarm.dog.Dog;
import com.dergachev.homework2.dogfarm.dog.PlaceOFWork;
import com.dergachev.homework2.dogfarm.util.myexception.DateException;
import com.dergachev.homework2.dogfarm.util.myexception.DogException;
import com.dergachev.homework2.dogfarm.worker.Cook;
import com.dergachev.homework2.dogfarm.worker.Worker;

import java.util.List;
import java.util.Map;

public class StartFarmDog {
    public static void main(String[] args)  {
    FarmDog farmDog = new FarmDog();
    farmDog.doOneDay();
    //farmDog.printListDogInFile("");
    //farmDog.getDogList().addDog(new Dog("Chik","12.12.2011", PlaceOFWork.POLICE,true,true,true));
    //farmDog.printListDogInFile("");
    //farmDog.getDogList().saveDogListInFile();
    //List<Dog> dogs = farmDog.getDogList().getDogListFromFile();
    //dogs.forEach(System.out::println);
    }
}
