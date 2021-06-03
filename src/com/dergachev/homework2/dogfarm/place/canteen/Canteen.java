package com.dergachev.homework2.dogfarm.place.canteen;

import com.dergachev.homework2.dogfarm.dog.Age;
import com.dergachev.homework2.dogfarm.dog.Dog;
import com.dergachev.homework2.dogfarm.dog.DogList;
import com.dergachev.homework2.dogfarm.util.myexception.*;
import com.dergachev.homework2.dogfarm.worker.Cook;
import com.dergachev.homework2.dogfarm.worker.Worker;

import java.util.List;
import java.util.stream.Stream;

public class Canteen {

    private Cook cook;
    private Kitchen kitchen;

    public Canteen() {
        this.kitchen = new Kitchen();
    }

    public Canteen(Worker cook) throws WorkerException {
        validCook(cook);
        this.cook = (Cook) cook;
        this.kitchen = new Kitchen();
    }

    public Worker getCook() {
        return cook;
    }

    public void setCook(Worker cook) throws WorkerException {
        validCook(cook);
        this.cook = (Cook) cook;
    }

    public void changeFeedFor(Age ageDog, String nameFeed) throws CanteenException {
        if (ageDog == null) {
            throw new NullPointerException("Please set age dog");
        } else if (nameFeed == null || nameFeed.trim().equals("")) {
            throw new CanteenException("Please set nameFeed");
        }
        switch (ageDog) {
            case PUPPY -> kitchen.setNamePuppyFeed(nameFeed);
            case ADULTDOG -> kitchen.setNameAdultFeed(nameFeed);
            case ELDERLYDOG -> kitchen.setNameElderlyFeed(nameFeed);
        }
    }

    public void feedingDogs(List<Dog> dogList) {
        validDogList(dogList);
        long colHungry = 0;
        colHungry = dogList.stream().filter(Dog::isHungry).filter(e -> e.getAge() == Age.PUPPY).peek(e -> e.setHungry(false)).peek(e ->
                System.out.println(cook.getName() + ": " + cook.work(e) + ". " + e.getName() + ": " + kitchen.createFeed(Age.PUPPY).eatFood())).count();
        colHungry = colHungry + dogList.stream().filter(Dog::isHungry).filter(e -> e.getAge() == Age.ADULTDOG).peek(e -> e.setHungry(false)).peek(e ->
                System.out.println(cook.getName() + ": " + cook.work(e) + ". " + e.getName() + ": " + kitchen.createFeed(Age.ADULTDOG).eatFood())).count();
        colHungry = colHungry + dogList.stream().filter(Dog::isHungry).filter(e -> e.getAge() == Age.ELDERLYDOG).peek(e -> e.setHungry(false)).peek(e ->
                System.out.println(cook.getName() + ": " + cook.work(e) + ". " + e.getName() + ": " + kitchen.createFeed(Age.ELDERLYDOG).eatFood())).count();

        if (colHungry > 0) {
            System.out.println("The cook " + cook.getName() + " fed all dogs.\n");
        }
        else {
            System.out.println(cook.getName()+": Strange, all the dogs are full");
        }
        System.out.println();
    }

    private void validCook(Worker cook) throws WorkerException {
        if (cook == null) {
            throw new NullPointerException("There is a cook to work here.");
        } else if (cook.getClass() != Cook.class) {
            throw new WorkerException("It is not the responsibility of the " + cook.getClass() + " to cook.");
        }
    }

    private void validDogList(List<Dog> dogList) {
        if (dogList == null || dogList.isEmpty()) {
            throw new NullPointerException("Need someone to feed.");
        }
    }
}
