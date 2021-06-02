package com.dergachev.homework2.dogfarm.dog;

import com.dergachev.homework2.dogfarm.util.myexception.DateException;

import java.util.*;
import java.util.stream.Collectors;

public class DogList {
    private List<Dog> dogList;

    public DogList() {
        initDogList();
    }

    public DogList(List<Dog> dogList) {
        this.dogList = Optional.of(new ArrayList<>(dogList)).orElse(new ArrayList<>());
    }


    public List<Dog> getDogList() {
        return dogList;
    }

    public void setDogList(List<Dog> dogList) {
        this.dogList = Optional.of(new ArrayList<>(dogList)).orElse(new ArrayList<>());
    }

    public void addDog(Dog dog) {
        this.dogList.add(dog);
    }

    public List<Dog> getSortedDogListByName() {
        return Optional.of(dogList.stream().sorted(Comparator.comparing(Dog::getName)).collect(Collectors.toList())).orElse(new ArrayList<>());
    }

    public List<Dog> getSortedDogListByAge() {
        return Optional.of(dogList.stream().sorted
                ((e1, e2) -> e1.getAge() != (e2.getAge()) ? e1.getAge().compareTo(e2.getAge()) : e1.getName().compareTo(e2.getName()))
                .collect(Collectors.toList())).orElse(new ArrayList<>());
    }

    public List<Dog> getListAgeDog(Age age) {
        switch (age) {
            case PUPPY:
                return Optional.of(dogList.stream().filter(e -> e.getAge() == Age.PUPPY).sorted(Comparator.comparing(Dog::getName)).collect(Collectors.toList()))
                        .orElse(new ArrayList<>());
            case ADULTDOG:
                return Optional.of(dogList.stream().filter(e -> e.getAge() == Age.ADULTDOG).sorted(Comparator.comparing(Dog::getName)).collect(Collectors.toList()))
                        .orElse(new ArrayList<>());
            case ELDERLYDOG:
                return Optional.of(dogList.stream().filter(e -> e.getAge() == Age.ELDERLYDOG).sorted(Comparator.comparing(Dog::getName)).collect(Collectors.toList()))
                        .orElse(new ArrayList<>());
        }
        return new ArrayList<>();
    }

    public Map<Age,List<Dog>> getMapDogByAge(){
        return Optional.of(dogList.stream().collect(Collectors.groupingBy(Dog::getAge))).orElse(new HashMap<>());
    }

    public Map<PlaceOFWork,List<Dog>> getMapDogByWork(){
        return Optional.of(dogList.stream().collect(Collectors.groupingBy(Dog::getWork))).orElse(new HashMap<>());
    }


    private void initDogList() {
        this.dogList = new ArrayList<>();

        try {
            Dog dog1 = new Dog("Barbos", "12.01.2017", PlaceOFWork.POLICE, false, true, true);
            Dog dog2 = new Dog("Sharik", "19.07.2014", true, true, false);
            Dog dog3 = new Dog("Gav", "12.04.2020", true, false, true);
            Dog dog4 = new Dog("Bim", "03.08.2020", false, false, false);
            Dog dog5 = new Dog("Palkan", "31.08.2018", PlaceOFWork.EMERGENCY, true, true, false);
            Dog dog6 = new Dog("Pes", "25.07.2010", false, true, true);

            dogList.add(dog1);
            dogList.add(dog2);
            dogList.add(dog3);
            dogList.add(dog4);
            dogList.add(dog5);
            dogList.add(dog6);

        } catch (DateException e) {
            System.out.println(e.getMessage());
        }
    }
}
