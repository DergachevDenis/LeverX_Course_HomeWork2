package com.dergachev.homework2;

import com.dergachev.homework2.dogfarm.FarmDog;
import com.dergachev.homework2.dogfarm.dog.Age;
import com.dergachev.homework2.dogfarm.dog.Dog;

import java.util.List;
import java.util.Map;

public class StartFarmDog {
    public static void main(String[] args) {
    FarmDog farmDog = new FarmDog();
    farmDog.doOneDay();

        List<Dog> sortList = farmDog.getSortedDogListByName();
        Map<Age, List<Dog>> map = farmDog.getMapDogByAge();

        sortList.forEach(System.out::println);
        System.out.println();
        for(Map.Entry<Age, List<Dog>> item : map.entrySet()){
            System.out.println(item.getKey());
            item.getValue().forEach(System.out::println);
            System.out.println();
        }
    }
}
